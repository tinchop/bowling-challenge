import com.tinchop.bowling.factory.FrameFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public class FrameFactoryTest {

    private FrameFactory frameFactory;

    @BeforeEach
    public void init() {
        if (frameFactory == null) {
            frameFactory = new FrameFactory();
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
                STRIKE, STRIKE,
                STRIKE, STRIKE,
                STRIKE, STRIKE,
                STRIKE, STRIKE,
                STRIKE, STRIKE,
                STRIKE, STRIKE);
        var frames = frameFactory.createFrames(chances);
        Assertions.assertEquals(FRAMES_PER_GAME, frames.size());

    }

    @Test
    public void createFramesAllParsTest() {
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
        var frames = frameFactory.createFrames(chances);
        Assertions.assertEquals(FRAMES_PER_GAME, frames.size());

    }


}
