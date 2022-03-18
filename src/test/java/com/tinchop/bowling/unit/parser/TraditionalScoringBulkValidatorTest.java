package com.tinchop.bowling.unit.parser;

import com.tinchop.bowling.parser.validation.InvalidInputException;
import com.tinchop.bowling.parser.validation.TraditionalScoringBulkValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.tinchop.bowling.constant.BowlingChallengeMessages.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TraditionalScoringBulkValidatorTest {

    private static TraditionalScoringBulkValidator bulkValidator;

    @BeforeAll
    public static void init() {
        bulkValidator = new TraditionalScoringBulkValidator();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void nonEmptyFileRuleTest() {
        Map<String, List<String>> bulk = Collections.EMPTY_MAP;
        testBulk(bulk, NON_EMPTY_FILE_RULE_MSG);
    }

    @Test
    public void validChancesCountRuleTest() {
        Map<String, List<String>> bulk = Collections.singletonMap("Luke", List.of("10", "10"));
        testBulk(bulk, VALID_CHANCES_COUNT_RULE_MSG);
    }

    @Test
    public void validPinfallPerFrameRuleTest() {
        Map<String, List<String>> bulk = Collections.singletonMap("Luke", List.of("8", "8", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"));
        testBulk(bulk, VALID_PINFALL_COUNT_RULE_MSG);
    }

    public void testBulk(Map<String, List<String>> bulk, String expectedMessage) {
        var thrown = assertThrows(
                InvalidInputException.class,
                () -> bulkValidator.validateBulk(bulk)
        );
        assertTrue(thrown.getMessage().contains(expectedMessage));
    }

}
