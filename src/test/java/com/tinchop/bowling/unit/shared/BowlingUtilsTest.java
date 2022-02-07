package com.tinchop.bowling.unit.shared;

import org.junit.jupiter.api.Test;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.FOUL;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.INPUT_STRIKE;
import static com.tinchop.bowling.shared.BowlingUtils.isInputSpare;
import static com.tinchop.bowling.shared.BowlingUtils.isInputStrike;
import static org.junit.jupiter.api.Assertions.*;

public class BowlingUtilsTest {

    @Test
    public void isInputStrikeTest() {
        assertAll(
                () -> assertTrue(isInputStrike(INPUT_STRIKE)),
                () -> assertFalse(isInputStrike("11")),
                () -> assertFalse(isInputStrike("-10")),
                () -> assertFalse(isInputStrike("abcd"))
        );
    }

    @Test
    public void isInputSpareTest() {
        assertAll(
                () -> assertTrue(isInputSpare("0", "10")),
                () -> assertTrue(isInputSpare(FOUL, "10")),
                () -> assertTrue(isInputSpare("2", "8")),
                () -> assertTrue(isInputSpare("5", "5")),
                () -> assertFalse(isInputSpare(INPUT_STRIKE, INPUT_STRIKE)),
                () -> assertFalse(isInputSpare("11", null)),
                () -> assertFalse(isInputSpare("-10", "")),
                () -> assertFalse(isInputSpare("abcd", "abcd"))
        );
    }

}
