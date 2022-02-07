package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.frame.traditional.OpenFrame;
import com.tinchop.bowling.model.frame.traditional.TenthFrame;
import org.junit.jupiter.api.Test;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_STRIKE;
import static org.junit.jupiter.api.Assertions.*;

public class TenthFrameTest {

    @Test
    public void getPrintableTextTest() {
        var frame = TenthFrame.builder().firstChance(OUTPUT_STRIKE).secondChance(OUTPUT_STRIKE).thirdChance("6").build();
        frame.setPreviousFrame(OpenFrame.builder().firstChance("3").secondChance("4").build());

        var printableText = frame.get();
        assertAll(
                () -> assertTrue(printableText.contains(OUTPUT_STRIKE)),
                () -> assertFalse(printableText.contains("7")),
                () -> assertFalse(printableText.contains("4")),
                () -> assertTrue(printableText.contains("6")),
                () -> assertTrue(printableText.contains("33"))
        );
    }

}
