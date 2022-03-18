package com.tinchop.bowling.integration;

import com.tinchop.bowling.BowlingChallenge;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.*;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {

    @Test
    public void emptyTest() {
        runUsingFile(EMPTY_FILEPATH, List.of(NON_EMPTY_FILE_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void freeTextTest() {
        runUsingFile(FREE_TEXT_FILEPATH, List.of(TWO_COLUMNS_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void extraScoreTest() {
        runUsingFile(EXTRA_SCORE_FILEPATH, List.of(VALID_CHANCES_COUNT_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void extraScore2Test() {
        runUsingFile(EXTRA_SCORE_2_FILEPATH, List.of(VALID_CHANCES_COUNT_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void incompleteScoreTest() {
        runUsingFile(INCOMPLETE_SCORE_FILEPATH, List.of(VALID_CHANCES_COUNT_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void incompleteScore2Test() {
        runUsingFile(INCOMPLETE_SCORE_2_FILEPATH, List.of(VALID_CHANCES_COUNT_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void incompleteScore3Test() {
        runUsingFile(INCOMPLETE_SCORE_3_FILEPATH, List.of(VALID_CHANCES_COUNT_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void incompleteScore4Test() {
        runUsingFile(INCOMPLETE_SCORE_4_FILEPATH, List.of(VALID_CHANCES_COUNT_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void invalidScoreTest() {
        runUsingFile(INVALID_SCORE_FILEPATH, List.of(NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void invalidScore2Test() {
        runUsingFile(INVALID_SCORE_2_FILEPATH, List.of(VALID_PINFALL_COUNT_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void negativeTest() {
        runUsingFile(NEGATIVE_FILEPATH, List.of(NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG), List.of(OUTPUT_HEADER));
    }

    @Test
    public void perfectScoreTest() {
        runUsingFile(PERFECT_FILEPATH, List.of(OUTPUT_HEADER, "Carl", OUTPUT_STRIKE, "300"), List.of("Anakin", "Obi-Wan", "Qui-Gon", "Binks"));
    }

    @Test
    public void zeroScoreTest() {
        runUsingFile(ZERO_FILEPATH, List.of(OUTPUT_HEADER, "Carl", "0"), List.of("Anakin", "Obi-Wan", "Qui-Gon", "Binks"));
    }

    @Test
    public void scoresTest() {
        runUsingFile(SCORES_FILEPATH, List.of(OUTPUT_HEADER, "Jeff", "John"), List.of("Anakin", "Obi-Wan", "Qui-Gon", "Binks"));
    }

    @Test
    public void threePlayerGameTest() {
        runUsingFile(THREE_PLAYER_GAME_FILEPATH, List.of(OUTPUT_HEADER, "Luke", "Leia", "Solo"), List.of("Anakin", "Obi-Wan", "Qui-Gon", "Binks"));
    }

    @Test
    public void fourPlayerGameTest() {
        runUsingFile(FOUR_PLAYER_GAME_FILEPATH, List.of(OUTPUT_HEADER, "Anakin", "Obi-Wan", "Qui-Gon", "Binks"), List.of("Luke", "Leia", "Solo"));
    }

    private void runUsingFile(String filePath, List<String> assertContains, List<String> assertNotContains) {
        try {
            var output = tapSystemOut(() -> {
                BowlingChallenge.main(new String[]{filePath});
            });
            if (isNotEmpty(assertContains)) {
                for (String value : assertContains) assertTrue(output.contains(value));
            }
            if (isNotEmpty(assertNotContains)) {
                for (String value : assertNotContains) assertFalse(output.contains(value));
            }
            System.out.println(output);
        } catch (Exception e) {
            fail(e);
        }
    }

}
