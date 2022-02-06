import com.tinchop.bowling.parser.GameFileParser;
import com.tinchop.bowling.parser.InvalidInputException;
import com.tinchop.bowling.parser.InputValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class GameFileParserTest {

    private static GameFileParser parser;

    @BeforeAll
    public static void init() {
        parser = GameFileParser.builder().inputValidator(new InputValidator()).build();
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

    @Test
    public void parsePerfectFileTest() {
        try {
            var expectedPlayersCount = 1;
            var expectedCarlChancesCount = 12;
            var parsedGame = parser.parse("src/test/resources/positive/perfect.txt");

            assertEquals(expectedPlayersCount, parsedGame.keySet().size());
            assertEquals(expectedCarlChancesCount, parsedGame.get("Carl").size());

        } catch (FileNotFoundException e) {
            fail(e);
        }
    }

    @Test
    public void parseEmptyFileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/empty.txt"));
    }

    @Test
    public void parseInvalidScoreFileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/invalid-score.txt"));
    }

    @Test
    public void parseExtraScoreFileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/extra-score.txt"));
    }

}
