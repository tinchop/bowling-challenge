package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.frame.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_STRIKE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraditionalScoringFrameTest {

    @Test
    public void getScoreAllStrikesTest() {

        List<Frame> frames = List.of(
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                TenthFrame.builder().firstChance(OUTPUT_STRIKE).secondChance(OUTPUT_STRIKE).thirdChance(OUTPUT_STRIKE).build()
        );
        linkFrames(frames);

        var expectedScores = List.of(30, 60, 90, 120, 150, 180, 210, 240, 270, 300);
        assertScores(frames, expectedScores);
    }

    @Test
    public void getScoreAllSparesTest() {

        List<Frame> frames = List.of(
                SpareFrame.builder().firstChance("8").build(),
                SpareFrame.builder().firstChance("1").build(),
                SpareFrame.builder().firstChance("5").build(),
                SpareFrame.builder().firstChance("2").build(),
                SpareFrame.builder().firstChance("9").build(),
                SpareFrame.builder().firstChance("1").build(),
                SpareFrame.builder().firstChance("5").build(),
                SpareFrame.builder().firstChance("2").build(),
                SpareFrame.builder().firstChance("9").build(),
                TenthFrame.builder().firstChance("1").secondChance("9").thirdChance("2").build()
        );
        linkFrames(frames);

        var expectedScores = List.of(11, 26, 38, 57, 68, 83, 95, 114, 125, 137);
        assertScores(frames, expectedScores);
    }

    @Test
    public void getScoreTest() {

        List<Frame> frames = List.of(
                SpareFrame.builder().firstChance("3").build(),
                OpenFrame.builder().firstChance("6").secondChance("3").build(),
                StrikeFrame.builder().build(),
                OpenFrame.builder().firstChance("8").secondChance("1").build(),
                StrikeFrame.builder().build(),
                StrikeFrame.builder().build(),
                OpenFrame.builder().firstChance("9").secondChance("0").build(),
                SpareFrame.builder().firstChance("7").build(),
                OpenFrame.builder().firstChance("4").secondChance("4").build(),
                TenthFrame.builder().firstChance(OUTPUT_STRIKE).secondChance("9").thirdChance("0").build()
        );
        linkFrames(frames);

        var expectedScores = List.of(16, 25, 44, 53, 82, 101, 110, 124, 132, 151);
        assertScores(frames, expectedScores);
    }

    private void assertScores(List<Frame> frames, List<Integer> expectedFrameScores) {
        for (int i = 0; i < frames.size(); i++) assertEquals(expectedFrameScores.get(i), frames.get(i).getScore());
    }

    private void linkFrames(List<Frame> frames) {
        for (int i = 1; i < frames.size(); i++) frames.get(i).setPreviousFrame(frames.get(i - 1));
    }

}
