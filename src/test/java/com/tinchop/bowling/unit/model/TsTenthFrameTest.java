package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.frame.traditional.TsOpenFrame;
import com.tinchop.bowling.model.frame.traditional.TsTenthFrame;
import org.junit.jupiter.api.Test;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_STRIKE;
import static org.junit.jupiter.api.Assertions.*;

public class TsTenthFrameTest {

    @Test
    public void getPrintableTextTest() {
        var frame = TsTenthFrame.builder().firstChance(OUTPUT_STRIKE).secondChance(OUTPUT_STRIKE).thirdChance("6").build();
        frame.setPreviousFrame(TsOpenFrame.builder().firstChance("3").secondChance("4").build());

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
