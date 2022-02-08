package com.tinchop.bowling.parser.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.*;
import static com.tinchop.bowling.shared.BowlingUtils.*;

public class TraditionalScoringBulkValidator implements BulkValidator {


    private final List<BulkValidationRule> bulkValidationRules;

    public TraditionalScoringBulkValidator() {
        bulkValidationRules = createRules();
    }

    @Override
    public void validateBulk(Map<String, List<String>> bulk) {
        bulkValidationRules.forEach(rule -> {
            if (rule.brokenBy(bulk)) throw new InvalidInputException(rule.getMessage());
        });
    }

    @AllArgsConstructor
    private static class BulkValidationRule implements InputValidationRule {

        @Getter
        private Predicate<Map<String, List<String>>> predicate;
        @Getter
        private String message;

        boolean brokenBy(Map<String, List<String>> bulk) {
            return predicate.test(bulk);
        }

    }

    private List<BulkValidationRule> createRules() {
        return List.of(
                new BulkValidationRule(Map::isEmpty, NON_EMPTY_FILE_RULE_MSG),
                new BulkValidationRule(bulk -> {

                    Predicate<List<String>> invalidTenthFrame = (tenthFrameChances) -> {
                        boolean invalidTenthFrameChancesCount = tenthFrameChances.size() > MAX_CHANCES_TENTH_FRAME || tenthFrameChances.size() < MIN_CHANCES_TENTH_FRAME;
                        boolean bonusWithoutStrikeOrSpare = tenthFrameChances.size() != MIN_CHANCES_TENTH_FRAME && (!isInputStrike(tenthFrameChances.get(0)) && !isInputSpare(tenthFrameChances.get(0), tenthFrameChances.get(1)));
                        boolean noBonusWithStrikeOrSpare = tenthFrameChances.size() == MIN_CHANCES_TENTH_FRAME && (isInputStrike(tenthFrameChances.get(0)) || isInputSpare(tenthFrameChances.get(0), tenthFrameChances.get(1)));

                        return invalidTenthFrameChancesCount || bonusWithoutStrikeOrSpare || noBonusWithStrikeOrSpare;
                    };
                    Predicate<Integer> chancesCountOutsidePossibleRange = (count) -> count < MIN_POSSIBLE_CHANCES || count > MAX_POSSIBLE_CHANCES;
                    Predicate<List<String>> brokenBy = (chances) -> {

                        if (chancesCountOutsidePossibleRange.test(chances.size())) return true;

                        int frameCount = 0;
                        int tenthFrameStartIndex = -1;

                        for (int i = 0; i < chances.size(); i++) {
                            frameCount++;
                            if (frameCount == FRAMES_PER_GAME) {
                                tenthFrameStartIndex = i;
                                break;
                            }
                            if (!isInputStrike(chances.get(i))) i++;
                        }

                        if (frameCount != 10) return true;

                        return invalidTenthFrame.test(chances.subList(tenthFrameStartIndex, chances.size()));
                    };

                    for (String playerName : bulk.keySet()) {
                        if (brokenBy.test(bulk.get(playerName))) return true;
                    }
                    return false;
                }, VALID_CHANCES_COUNT_RULE_MSG),
                new BulkValidationRule((bulk) -> {

                    for (String playerName : bulk.keySet()) {
                        Predicate<List<String>> brokenBy = (chances) -> {
                            int frameCount = 0;

                            for (int i = 0; i < chances.size(); i++) {
                                frameCount++;
                                if (frameCount == FRAMES_PER_GAME) break;

                                if (!isInputStrike(chances.get(i))) {
                                    if (sumChances(chances.get(i), chances.get(i + 1)) > MAX_CHANCE_SCORE) return true;
                                    i++;
                                }
                            }

                            return false;
                        };

                        if (brokenBy.test(bulk.get(playerName))) return true;
                    }
                    return false;
                }, VALID_PINFALL_COUNT_RULE_MSG)
        );
    }

}
