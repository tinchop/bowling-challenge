package com.tinchop.bowling.unit.model;

import com.tinchop.bowling.model.frame.traditional.TraditionalScoringFrameFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.FRAMES_PER_GAME;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.INPUT_STRIKE;

public class TraditionalScoringFrameFactoryTest {

    private static TraditionalScoringFrameFactory frameFactory;

    @BeforeAll
    public static void init() {
        frameFactory = new TraditionalScoringFrameFactory();
    }

    @Test
    public void createFramesAllOpenFramesTest() {
        var chances = List.of(
                "2", "4",
                "0", "5",
                "6", "1",
                "6", "2",
                "3", "0",
                "7", "0",
                "8", "1",
                "1", "1",
                "2", "2",
                "5", "3");
        var frames = frameFactory.createFrames(chances);
        Assertions.assertEquals(FRAMES_PER_GAME, frames.size());
    }

    @Test
    public void createFramesAllStrikesTest() {
        var chances = List.of(
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE);
        var frames = frameFactory.createFrames(chances);
        Assertions.assertEquals(FRAMES_PER_GAME, frames.size());
    }

    @Test
    public void createFramesAllSparesTest() {
        var chances = List.of(
                "0", "10",
                "1", "9",
                "5", "5",
                "2", "8",
                "9", "1",
                "1", "9",
                "5", "5",
                "2", "8",
                "9", "1",
                "1", "9", "2");
        var frames = frameFactory.createFrames(chances);
        Assertions.assertEquals(FRAMES_PER_GAME, frames.size());
    }

    @Test
    public void createFramesMixedTest() {
        var chances = List.of(
                "0", "10",
                "1", "1",
                "10",
                "2", "8",
                "9", "0",
                "1", "1",
                "5", "4",
                "10",
                "9", "F",
                "10", "F", "2");
        var frames = frameFactory.createFrames(chances);
        Assertions.assertEquals(FRAMES_PER_GAME, frames.size());
    }


}
