import com.tinchop.bowling.factory.ChancesValidator;
import com.tinchop.bowling.factory.FrameFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public class FrameTest {

    private FrameFactory frameFactory;

    @BeforeEach
    public void init() {
        if (frameFactory == null) {
            frameFactory = FrameFactory.builder().chancesValidator(new ChancesValidator()).build();
        }
    }

    @Test
    public void getScoreAllStrikesTest() {
        var chances = List.of(
                STRIKE, STRIKE,
                STRIKE, STRIKE,
                STRIKE, STRIKE,
                STRIKE, STRIKE,
                STRIKE, STRIKE,
                STRIKE, STRIKE);
        var frames = frameFactory.createFrames(chances);

        for (int i = 0; i < frames.size(); i++) {
            var frame = frames.get(i);
            Assertions.assertEquals((i + 1) * MAX_FRAME_SCORE, frame.getScore());
        }
    }

    @Test
    public void getScoreAllParsTest() {
        var chances = List.of(
                "0", PAR,
                "1", PAR,
                "5", PAR,
                "2", PAR,
                "9", PAR,
                "1", PAR,
                "5", PAR,
                "2", PAR,
                "9", PAR,
                "1", PAR, "2");
        var expectedFrameScores = List.of(11, 26, 38, 57, 68, 83, 95, 114, 125, 137);
        assertScores(chances, expectedFrameScores);
    }

    @Test
    public void getScore1Test() {
        var chances = List.of(
                "3", PAR,
                "6", "3",
                STRIKE,
                "8", "1",
                STRIKE,
                STRIKE,
                "9", "0",
                "7", PAR,
                "4", "4",
                STRIKE, "9", "0");
        var expectedFrameScores = List.of(16, 25, 44, 53, 82, 101, 110, 124, 132, 151);
        assertScores(chances, expectedFrameScores);
    }

    private void assertScores(List<String> chances, List<Integer> expectedFrameScores) {
        var frames = frameFactory.createFrames(chances);

        for (int i = 0; i < frames.size(); i++) {
            var frame = frames.get(i);
            Assertions.assertEquals(expectedFrameScores.get(i), frame.getScore());
        }
    }


}
