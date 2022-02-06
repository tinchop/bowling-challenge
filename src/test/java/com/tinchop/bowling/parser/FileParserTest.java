package com.tinchop.bowling.parser;

import com.tinchop.bowling.parser.validation.TraditionalScoringLineValidator;
import com.tinchop.bowling.parser.FileParser;
import com.tinchop.bowling.parser.validation.InvalidInputException;
import com.tinchop.bowling.parser.validation.TraditionalScoringBulkValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class FileParserTest {

    private static FileParser parser;

    @BeforeAll
    public static void init() {
        parser = FileParser.builder().lineValidator(new TraditionalScoringLineValidator()).bulkValidator(new TraditionalScoringBulkValidator()).build();
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
    @DisplayName("Shouldn't be more than 10 pins")
    public void parseInvalidScore2FileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/invalid-score-2.txt"));
    }

    @Test
    public void parseExtraScoreFileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/extra-score.txt"));
    }

    @Test
    @DisplayName("Tenth frame shouldn't have bonus chance (no strike nor spare)")
    public void parseExtraScore2FileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/extra-score-2.txt"));
    }

    @Test
    @DisplayName("Tenth frame should have bonus chance (strike)")
    public void parseIncompleteScoreFileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/incomplete-score.txt"));
    }

    @Test
    @DisplayName("Tenth frame should have bonus chance (spare)")
    public void parseIncompleteScore2FileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/incomplete-score-2.txt"));
    }

    @Test
    @DisplayName("Not enough frames")
    public void parseIncompleteScore3FileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/incomplete-score-3.txt"));
    }

    @Test
    @DisplayName("Tenth frame should have bonus chance (spare with foul)")
    public void parseIncompleteScore4FileTest() {
        assertThrows(InvalidInputException.class, () -> parser.parse("src/test/resources/negative/incomplete-score-4.txt"));
    }

}
