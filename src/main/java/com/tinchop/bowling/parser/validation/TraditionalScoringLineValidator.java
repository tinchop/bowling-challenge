package com.tinchop.bowling.parser.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Predicate;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.*;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class TraditionalScoringLineValidator implements LineValidator {

    private final List<LineValidationRule> lineValidationRules;

    public TraditionalScoringLineValidator() {
        lineValidationRules = createRules();
    }

    @Override
    public void validateLine(String line) {
        lineValidationRules.forEach(rule -> {
            if (rule.brokenBy(line)) throw new InvalidInputException(fullRuleMessage(rule.getMessage(), line));
        });
    }

    private String fullRuleMessage(String ruleMessage, String line) {
        return OUTPUT_INVALID_LINE + line + OUTPUT_NEW_LINE + ruleMessage;
    }

    @AllArgsConstructor
    private static class LineValidationRule implements InputValidationRule {

        @Getter
        private Predicate<String> predicate;
        @Getter
        private String message;

        boolean brokenBy(String line) {
            return getPredicate().test(line);
        }

    }

    private List<LineValidationRule> createRules() {
        return List.of(
                new LineValidationRule((line) -> {

                    Predicate<String> numberOutsideRange = (score) -> {
                        if (!isNumeric(score.trim())) return false;
                        int scoreInt = parseInt(score.trim());
                        return scoreInt < FLOOR_SCORE_RANGE || scoreInt > CEILING_SCORE_RANGE;
                    };

                    var splitLine = line.split(COLUMN_DELIMITER);
                    return splitLine.length > 1 && ((!isNumeric(splitLine[1].trim()) && !FOUL.equals(splitLine[1].trim())) || numberOutsideRange.test(splitLine[1]));

                }, NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG),
                new LineValidationRule((line) -> {

                    var splitLine = line.split(COLUMN_DELIMITER);
                    return splitLine.length != INPUT_VALID_COLUMN_COUNT;

                }, TWO_COLUMNS_RULE_MSG)
        );
    }

}
