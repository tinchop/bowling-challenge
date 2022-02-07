package com.tinchop.bowling.unit.parser;

import com.tinchop.bowling.parser.validation.InvalidInputException;
import com.tinchop.bowling.parser.validation.TraditionalScoringLineValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tinchop.bowling.constant.BowlingChallengeMessages.NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.TWO_COLUMNS_RULE_MSG;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TraditionalScoringLineValidatorTest {

    private static TraditionalScoringLineValidator lineValidator;

    @BeforeAll
    public static void init() {
        lineValidator = new TraditionalScoringLineValidator();
    }

    @Test
    public void twoColumnRuleTest() {
        testLine("Luke\t10\t10", TWO_COLUMNS_RULE_MSG);
    }

    @Test
    public void scoreFormatRuleTest() {
        testLine("Luke\t-99", NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG);
    }

    public void testLine(String line, String expectedMessage) {
        var thrown = assertThrows(
                InvalidInputException.class,
                () -> lineValidator.validateLine(line)
        );
        assertTrue(thrown.getMessage().contains(expectedMessage));
    }

}
