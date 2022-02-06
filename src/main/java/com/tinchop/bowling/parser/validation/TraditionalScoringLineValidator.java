package com.tinchop.bowling.parser.validation;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.*;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class TraditionalScoringLineValidator implements LineValidator {

    private final List<LineValidationRule> lineValidationRules;

    public TraditionalScoringLineValidator() {
        lineValidationRules = List.of(new TwoColumnLineRule(), new ScoreFormatRule());
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

    private interface LineValidationRule extends InputValidationRule {

        boolean brokenBy(String line);

    }

    private static class ScoreFormatRule implements LineValidationRule {

        @Override
        public boolean brokenBy(String line) {
            var splitLine = line.split(COLUMN_DELIMITER);
            return splitLine.length > 1 && ((!isNumeric(splitLine[1].trim()) && !FOUL.equals(splitLine[1].trim())) || numberOutsideRange(splitLine[1]));
        }

        private boolean numberOutsideRange(String score) {
            if (!isNumeric(score.trim())) return false;
            int scoreInt = parseInt(score.trim());
            return scoreInt < FLOOR_SCORE_RANGE || scoreInt > CEILING_SCORE_RANGE;
        }

        @Override
        public String getMessage() {
            return NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG;
        }
    }

    private static class TwoColumnLineRule implements LineValidationRule {

        @Override
        public boolean brokenBy(String line) {
            var splitLine = line.split(COLUMN_DELIMITER);
            return splitLine.length != INPUT_VALID_COLUMN_COUNT;
        }

        @Override
        public String getMessage() {
            return TWO_COLUMNS_RULE_MSG;
        }
    }
}
