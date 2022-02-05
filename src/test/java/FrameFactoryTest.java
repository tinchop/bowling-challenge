import com.tinchop.bowling.factory.ChancesValidator;
import com.tinchop.bowling.factory.FrameFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.FRAMES_PER_GAME;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.INPUT_STRIKE;

public class FrameFactoryTest {

    private FrameFactory frameFactory;

    @BeforeEach
    public void init() {
        if (frameFactory == null) {
            frameFactory = FrameFactory.builder().chancesValidator(new ChancesValidator()).build();
        }
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
    public void createFramesAllParsTest() {
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


}
