package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.frame.OpenFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenFrameTest {

    @Test
    public void getPrintableTextTest() {
        var frame = OpenFrame.builder().firstChance("2").secondChance("4").build();

        var printableText = frame.get();
        assertAll(
                () -> assertTrue(printableText.contains("2")),
                () -> assertTrue(printableText.contains("4")),
                () -> assertTrue(printableText.contains("6"))
        );
    }

}
