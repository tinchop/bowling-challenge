import com.tinchop.bowling.model.frame.TraditionalScoringFrameFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.INPUT_STRIKE;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.MAX_FRAME_SCORE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraditionalFrameTest {

    private static TraditionalScoringFrameFactory frameFactory;

    @BeforeAll
    public static void init() {
        frameFactory = new TraditionalScoringFrameFactory();
    }

    @Test
    public void getScoreAllStrikesTest() {
        var chances = List.of(
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE,
                INPUT_STRIKE, INPUT_STRIKE);
        var frames = frameFactory.createFrames(chances);

        for (int i = 0; i < frames.size(); i++) {
            var frame = frames.get(i);
            assertEquals((i + 1) * MAX_FRAME_SCORE, frame.getScore());
        }
    }

    @Test
    public void getScoreAllParsTest() {
        var chances = List.of(
                "8", "2",
                "1", "9",
                "5", "5",
                "2", "8",
                "9", "1",
                "1", "9",
                "5", "5",
                "2", "8",
                "9", "1",
                "1", "9", "2");
        var expectedFrameScores = List.of(11, 26, 38, 57, 68, 83, 95, 114, 125, 137);
        assertScores(chances, expectedFrameScores);
    }

    @Test
    public void getScoreTest() {
        var chances = List.of(
                "3", "7",
                "6", "3",
                INPUT_STRIKE,
                "8", "1",
                INPUT_STRIKE,
                INPUT_STRIKE,
                "9", "0",
                "7", "3",
                "4", "4",
                INPUT_STRIKE, "9", "0");
        var expectedFrameScores = List.of(16, 25, 44, 53, 82, 101, 110, 124, 132, 151);
        assertScores(chances, expectedFrameScores);
    }

    private void assertScores(List<String> chances, List<Integer> expectedFrameScores) {
        var frames = frameFactory.createFrames(chances);

        for (int i = 0; i < frames.size(); i++) {
            var frame = frames.get(i);
            assertEquals(expectedFrameScores.get(i), frame.getScore());
        }
    }


}
