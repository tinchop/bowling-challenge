import com.tinchop.bowling.factory.ChancesValidator;
import com.tinchop.bowling.factory.FrameFactory;
import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.PAR;

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
                "0", PAR,
                "1", PAR,
                "5", PAR,
                "2", PAR,
                "9", PAR,
                "1", PAR,
                "5", PAR,
                "2", PAR,
                "9", PAR,
                "1", PAR, "2"));
        var players = List.of(
                Player.builder().name("Luke").frames(lukesFrames).build(),
                Player.builder().name("Leia").frames(leiasFrames).build());
        var game = Game.builder().players(players).build();

        System.out.print(game.getPrintableText());
    }

}
