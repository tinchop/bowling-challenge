package com.tinchop.bowling.integration;

import com.tinchop.bowling.BowlingChallenge;
import org.junit.jupiter.api.Test;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static org.junit.jupiter.api.Assertions.fail;

public class IntegrationTests {

    @Test
    public void emptyTest() {
        runUsingFile(EMPTY_FILEPATH);
    }

    @Test
    public void freeTextTest() {
        runUsingFile(FREE_TEXT_FILEPATH);
    }

    @Test
    public void extraScoreTest() {
        runUsingFile(EXTRA_SCORE_FILEPATH);
    }

    @Test
    public void extraScore2Test() {
        runUsingFile(EXTRA_SCORE_2_FILEPATH);
    }

    @Test
    public void incompleteScoreTest() {
        runUsingFile(INCOMPLETE_SCORE_FILEPATH);
    }

    @Test
    public void incompleteScore2Test() {
        runUsingFile(INCOMPLETE_SCORE_2_FILEPATH);
    }

    @Test
    public void incompleteScore3Test() {
        runUsingFile(INCOMPLETE_SCORE_3_FILEPATH);
    }

    @Test
    public void incompleteScore4Test() {
        runUsingFile(INCOMPLETE_SCORE_4_FILEPATH);
    }

    @Test
    public void invalidScoreTest() {
        runUsingFile(INVALID_SCORE_FILEPATH);
    }

    @Test
    public void invalidScore2Test() {
        runUsingFile(INVALID_SCORE_2_FILEPATH);
    }

    @Test
    public void negativeTest() {
        runUsingFile(NEGATIVE_FILEPATH);
    }

    @Test
    public void perfectScoreTest() {
        runUsingFile(PERFECT_FILEPATH);
    }

    @Test
    public void zeroScoreTest() {
        runUsingFile(ZERO_FILEPATH);
    }

    @Test
    public void scoresTest() {
        runUsingFile(SCORES_FILEPATH);
    }

    private void runUsingFile(String filePath) {
        try {
            BowlingChallenge.main(new String[]{filePath});
        } catch (Exception e) {
            fail(e);
        }
    }

}
