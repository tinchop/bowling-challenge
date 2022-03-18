package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.Player;
import com.tinchop.bowling.model.frame.Frame;
import com.tinchop.bowling.model.frame.traditional.OpenFrame;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_PINFALLS;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_SCORE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    public void getPrintableTextTest() {

        var firstChance = "2";
        var secondChance = "4";
        List<Frame> frames = List.of(OpenFrame.builder().firstChance(firstChance).secondChance(secondChance).build());
        var playerName = "Luke";
        var player = Player.builder().name(playerName).frames(frames).build();


        var printableText = player.get();
        assertAll(
                () -> assertTrue(printableText.contains(playerName)),
                () -> assertTrue(printableText.contains(firstChance)),
                () -> assertTrue(printableText.contains(secondChance)),
                () -> assertTrue(printableText.contains(OUTPUT_PINFALLS)),
                () -> assertTrue(printableText.contains(OUTPUT_SCORE))
        );

    }

}
