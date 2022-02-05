package com.tinchop.bowling.parser;

import com.tinchop.bowling.common.InvalidInputException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG;

public class LineValidator {

    private final List<LineValidationRule> rules;

    public LineValidator() {
        rules = List.of(new NumericOrFoulScoreValue());
    }

    public String validateLine(String line) {
        rules.forEach(rule -> {
            if (rule.brokenBy(line)) {
                throw new InvalidInputException(fullRuleMessage(rule.getMessage(), line));
            }
        });
        return line;
    }

    private String fullRuleMessage(String ruleMessage, String line) {
        return "Invalid line: " + line + OUTPUT_NEW_LINE + ruleMessage;
    }

    private interface LineValidationRule {

        boolean brokenBy(String line);
        String getMessage();

    }

    private static class NumericOrFoulScoreValue implements LineValidationRule {

        @Override
        public boolean brokenBy(String line) {
            var splitLine = line.split(COLUMN_DELIMITER);
            return splitLine.length > 1 && !StringUtils.isNumeric(splitLine[1].trim()) && !FOUL.equals(splitLine[1].trim());
        }

        @Override
        public String getMessage() {
            return NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG;
        }
    }
}
