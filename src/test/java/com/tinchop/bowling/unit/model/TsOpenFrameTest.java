package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.frame.traditional.TsOpenFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TsOpenFrameTest {

    @Test
    public void getPrintableTextTest() {
        var frame = TsOpenFrame.builder().firstChance("2").secondChance("4").build();

        var printableText = frame.get();
        assertAll(
                () -> assertTrue(printableText.contains("2")),
                () -> assertTrue(printableText.contains("4")),
                () -> assertTrue(printableText.contains("6"))
        );
    }

}
