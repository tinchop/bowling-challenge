import com.tinchop.bowling.factory.ChancesValidator;
import com.tinchop.bowling.factory.FrameFactory;
import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.Player;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    public void getPrintableTextTest() {

        var frameFactory = FrameFactory.builder().chancesValidator(new ChancesValidator()).build();
        var lukesFrames = frameFactory.createFrames(List.of(
                "2", "4",
                "0", "5",
                "6", "1",
                "6", "2",
                "3", "0",
                "7", "0",
                "8", "1",
                "1", "1",
                "2", "2",
                "5", "3"));
        var leiasFrames = frameFactory.createFrames(List.of(
                "2", "8",
                "1", "9",
                "5", "1",
                "2", "2",
                "9", "F",
                "1", "0",
                "5", "0",
                "2", "0",
                "9", "0",
                "1", "9", "2"));
        var players = List.of(
                Player.builder().name("Luke").frames(lukesFrames).build(),
                Player.builder().name("Leia").frames(leiasFrames).build());
        var game = Game.builder().players(players).build();

        assertTrue(StringUtils.isNotEmpty(game.getPrintableText()));
    }

}
