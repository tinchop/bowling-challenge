package com.tinchop.bowling.model;

import com.tinchop.bowling.model.frame.OpenFrame;
import com.tinchop.bowling.model.frame.SpareFrame;
import org.junit.jupiter.api.Test;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_SPARE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpareFrameTest {

    @Test
    public void getPrintableTextTest() {
        var frame = SpareFrame.builder().firstChance("2").secondChance(OUTPUT_SPARE).build();
        frame.setNextFrame(OpenFrame.builder().firstChance("2").secondChance("4").build());

        var printableText = frame.get();
        assertAll(
                () -> assertTrue(printableText.contains(OUTPUT_SPARE)),
                () -> assertTrue(printableText.contains("12")));
    }

}
