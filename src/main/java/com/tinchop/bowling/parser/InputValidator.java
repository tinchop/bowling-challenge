package com.tinchop.bowling.parser;

import java.util.List;
import java.util.Map;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.*;
import static com.tinchop.bowling.shared.BowlingUtils.isStrike;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class InputValidator {

    private final List<LineValidationRule> lineValidationRules;
    private final List<BulkValidationRule> bulkValidationRules;

    public InputValidator() {
        lineValidationRules = List.of(new TwoColumnLineRule(), new ScoreFormatRule());
        bulkValidationRules = List.of(new NonEmptyFileRule(), new ValidChancesCountRule());
    }

    public void validateLine(String line) {
        lineValidationRules.forEach(rule -> {
            if (rule.brokenBy(line)) {
                throw new InvalidInputException(fullRuleMessage(rule.getMessage(), line));
            }
        });
    }

    public void validateBulk(Map<String, List<String>> bulk) {
        bulkValidationRules.forEach(rule -> {
            if (rule.brokenBy(bulk)) {
                throw new InvalidInputException(rule.getMessage());
            }
        });
    }

    private String fullRuleMessage(String ruleMessage, String line) {
        return OUTPUT_INVALID_LINE + line + OUTPUT_NEW_LINE + ruleMessage;
    }

    private interface InputValidationRule {

        String getMessage();

    }

    private interface LineValidationRule extends InputValidationRule {

        boolean brokenBy(String line);

    }

    private interface BulkValidationRule extends InputValidationRule {

        boolean brokenBy(Map<String, List<String>> bulk);

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
            return scoreInt < 0 || scoreInt > 10;
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
                int frameCount = 0;
                var chances = bulk.get(playerName);

                if (chances.size() < MIN_POSSIBLE_CHANCES) return true;

                for (int i = 0; i < chances.size(); i++) {
                    if (frameCount == FRAMES_PER_GAME && Math.abs(i - chances.size()) > 2) {
                        return true;
                    }
                    if (!isStrike(chances.get(i))) {
                        i++;
                    }
                    frameCount++;
                }
            }
            return false;
        }

        @Override
        public String getMessage() {
            return VALID_CHANCES_COUNT_RULE_MSG;
        }
    }
}
