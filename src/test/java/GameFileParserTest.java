import com.tinchop.bowling.parser.GameFileParser;
import com.tinchop.bowling.parser.LineValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GameFileParserTest {

    private GameFileParser parser;

    @BeforeEach
    public void init() {
        if (parser == null) {
            parser = GameFileParser.builder().lineValidator(new LineValidator()).build();
        }
    }

    @Test
    public void parseScoresFileTest() {
        try {
            var expectedPlayersCount = 2;
            var expectedJeffChancesCount = 17;
            var expectedJohnChancesCount = 18;
            var parsedGame = parser.parse("src/test/resources/positive/scores.txt");

            assertEquals(expectedPlayersCount, parsedGame.keySet().size());
            assertEquals(expectedJeffChancesCount, parsedGame.get("Jeff").size());
            assertEquals(expectedJohnChancesCount, parsedGame.get("John").size());

        } catch (FileNotFoundException e) {
            fail(e);
        }
    }

}
