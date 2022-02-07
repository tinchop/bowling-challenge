package com.tinchop.bowling.unit.parser;

import com.tinchop.bowling.parser.FileParser;
import com.tinchop.bowling.parser.validation.InvalidInputException;
import com.tinchop.bowling.parser.validation.TraditionalScoringBulkValidator;
import com.tinchop.bowling.parser.validation.TraditionalScoringLineValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
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
            var parsedGame = parser.parse(SCORES_FILEPATH);

            assertAll(
                    () -> assertEquals(expectedPlayersCount, parsedGame.keySet().size()),
                    () -> assertEquals(expectedJeffChancesCount, parsedGame.get("Jeff").size()),
                    () -> assertEquals(expectedJohnChancesCount, parsedGame.get("John").size())
            );

        } catch (FileNotFoundException e) {
            fail(e);
        }
    }

    @Test
    public void parsePerfectFileTest() {
        try {
            var expectedPlayersCount = 1;
            var expectedCarlChancesCount = 12;
            var parsedGame = parser.parse(PERFECT_FILEPATH);

            assertAll(
                    () -> assertEquals(expectedPlayersCount, parsedGame.keySet().size()),
                    () -> assertEquals(expectedCarlChancesCount, parsedGame.get("Carl").size())
            );

        } catch (FileNotFoundException e) {
            fail(e);
        }
    }

    @Test
    public void parseZeroFileTest() {
        try {
            var expectedPlayersCount = 1;
            var expectedCarlChancesCount = 20;
            var parsedGame = parser.parse(ZERO_FILEPATH);

            assertAll(
                    () -> assertEquals(expectedPlayersCount, parsedGame.keySet().size()),
                    () -> assertEquals(expectedCarlChancesCount, parsedGame.get("Carl").size())
            );

        } catch (FileNotFoundException e) {
            fail(e);
        }
    }

    @Test
    public void parseEmptyFileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(EMPTY_FILEPATH));
    }

    @Test
    public void parseInvalidScoreFileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(INVALID_SCORE_FILEPATH));
    }

    @Test
    public void parseInvalidScore2FileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(INVALID_SCORE_2_FILEPATH));
    }

    @Test
    public void parseExtraScoreFileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(EXTRA_SCORE_FILEPATH));
    }

    @Test
    public void parseExtraScore2FileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(EXTRA_SCORE_2_FILEPATH));
    }

    @Test
    public void parseIncompleteScoreFileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(INCOMPLETE_SCORE_FILEPATH));
    }

    @Test
    public void parseIncompleteScore2FileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(INCOMPLETE_SCORE_2_FILEPATH));
    }

    @Test
    public void parseIncompleteScore3FileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(INCOMPLETE_SCORE_3_FILEPATH));
    }

    @Test
    public void parseIncompleteScore4FileTest() {
        var thrown = assertThrows(InvalidInputException.class, () -> parser.parse(INCOMPLETE_SCORE_4_FILEPATH));
    }

}
