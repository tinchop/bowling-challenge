package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.frame.Frame;
import com.tinchop.bowling.model.frame.OpenFrame;
import com.tinchop.bowling.model.frame.TraditionalScoringFrameFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GameTest {

    @Test
    public void getPrintableTextTest() {

        var frameFactory = Mockito.mock(TraditionalScoringFrameFactory.class);
        var firstChance = "2";
        var secondChance = "4";
        var playerName = "Luke";
        List<Frame> frames = List.of(OpenFrame.builder().firstChance(firstChance).secondChance(secondChance).build());
        when(frameFactory.createFrames(any())).thenReturn(frames);
        var parsedFile = Collections.singletonMap(playerName, List.of(firstChance, secondChance));
        var game = Game.builder().frameFactory(frameFactory).parsedFile(parsedFile).build();


        var printableText = game.get();
        assertAll(
                () -> assertTrue(printableText.contains(OUTPUT_HEADER)),
                () -> assertTrue(printableText.contains(playerName)),
                () -> assertTrue(printableText.contains(firstChance)),
                () -> assertTrue(printableText.contains(secondChance)),
                () -> assertTrue(printableText.contains(OUTPUT_PINFALLS)),
                () -> assertTrue(printableText.contains(OUTPUT_SCORE)));

    }
}
