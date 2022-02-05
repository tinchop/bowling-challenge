import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    @Test
    public void getPrintableTextTest() {
        var players = List.of(
                Player.builder().name("Luke").frames(new ArrayList<>()).build(),
                Player.builder().name("Leia").frames(new ArrayList<>()).build());
        var game = Game.builder().players(players).build();

        System.out.print(game.getPrintableText());
    }

}
