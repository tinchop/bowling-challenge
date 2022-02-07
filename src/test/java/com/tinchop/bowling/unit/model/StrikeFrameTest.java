package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.frame.OpenFrame;
import com.tinchop.bowling.model.frame.StrikeFrame;
import org.junit.jupiter.api.Test;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_STRIKE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrikeFrameTest {

    @Test
    public void getPrintableTextTest() {
        var frame = StrikeFrame.builder().build();
        frame.setNextFrame(OpenFrame.builder().firstChance("2").secondChance("4").build());

        var printableText = frame.get();
        assertAll(
                () -> assertTrue(printableText.contains(OUTPUT_STRIKE)),
                () -> assertTrue(printableText.contains("16"))
        );
    }

}
