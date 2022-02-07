package com.tinchop.bowling.parser.validation;

import java.util.List;
import java.util.Map;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.*;
import static com.tinchop.bowling.shared.BowlingUtils.*;

public class TraditionalScoringBulkValidator implements BulkValidator {


    private final List<BulkValidationRule> bulkValidationRules;

    public TraditionalScoringBulkValidator() {
        bulkValidationRules = List.of(new NonEmptyFileRule(), new ValidChancesCountRule(), new ValidPinfallsPerFrameRule());
    }

    @Override
    public void validateBulk(Map<String, List<String>> bulk) {
        bulkValidationRules.forEach(rule -> {
            if (rule.brokenBy(bulk)) throw new InvalidInputException(rule.getMessage());
        });
    }

    private interface BulkValidationRule extends InputValidationRule {

        boolean brokenBy(Map<String, List<String>> bulk);

    }

    private static class NonEmptyFileRule implements BulkValidationRule {

        @Override
        public boolean brokenBy(Map<String, List<String>> bulk) {
            return bulk.isEmpty();
        }

        @Override
        public String getMessage() {
            return NON_EMPTY_FILE_RULE_MSG;
        }
    }

    private static class ValidChancesCountRule implements BulkValidationRule {

        @Override
        public boolean brokenBy(Map<String, List<String>> bulk) {

            for (String playerName : bulk.keySet()) {
                if (brokenBy(bulk.get(playerName))) return true;
            }
            return false;
        }

        private boolean brokenBy(List<String> chances) {

            if (chancesCountOutsidePossibleRange(chances.size())) return true;

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

            return invalidTenthFrame(chances.subList(tenthFrameStartIndex, chances.size()));
        }

        private boolean invalidTenthFrame(List<String> tenthFrameChances) {

            boolean invalidTenthFrameChancesCount = tenthFrameChances.size() > MAX_CHANCES_TENTH_FRAME || tenthFrameChances.size() < MIN_CHANCES_TENTH_FRAME;
            boolean bonusWithoutStrikeOrSpare = tenthFrameChances.size() != MIN_CHANCES_TENTH_FRAME && (!isInputStrike(tenthFrameChances.get(0)) && !isInputSpare(tenthFrameChances.get(0), tenthFrameChances.get(1)));
            boolean noBonusWithStrikeOrSpare = tenthFrameChances.size() == MIN_CHANCES_TENTH_FRAME && (isInputStrike(tenthFrameChances.get(0)) || isInputSpare(tenthFrameChances.get(0), tenthFrameChances.get(1)));

            return invalidTenthFrameChancesCount || bonusWithoutStrikeOrSpare || noBonusWithStrikeOrSpare;
        }

        private boolean chancesCountOutsidePossibleRange(Integer count) {
            return count < MIN_POSSIBLE_CHANCES || count > MAX_POSSIBLE_CHANCES;
        }

        @Override
        public String getMessage() {
            return VALID_CHANCES_COUNT_RULE_MSG;
        }
    }

    private static class ValidPinfallsPerFrameRule implements BulkValidationRule {

        @Override
        public boolean brokenBy(Map<String, List<String>> bulk) {

            for (String playerName : bulk.keySet()) {
                if (brokenBy(bulk.get(playerName))) return true;
            }
            return false;
        }

        private boolean brokenBy(List<String> chances) {

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
        }


        @Override
        public String getMessage() {
            return VALID_PINFALL_COUNT_RULE_MSG;
        }
    }
}
