package com.tinchop.bowling;

import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.Printable;
import com.tinchop.bowling.model.frame.traditional.TraditionalScoringFrameFactory;
import com.tinchop.bowling.parser.FileParser;
import com.tinchop.bowling.parser.validation.InvalidInputException;
import com.tinchop.bowling.parser.validation.TraditionalScoringBulkValidator;
import com.tinchop.bowling.parser.validation.TraditionalScoringLineValidator;

import java.io.FileNotFoundException;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.EXIT_CODE_UNSUCCESSFUL;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.NO_FILEPATH_PROVIDED_MSG;


public class BowlingChallenge {

    public static void main(String[] args) {

        validateArgs(args);
        var filePath = args[0];

        var parser = FileParser.builder()
                .lineValidator(new TraditionalScoringLineValidator())
                .bulkValidator(new TraditionalScoringBulkValidator())
                .build();

        try {
            Printable game = Game.builder().parsedFile(parser.parse(filePath)).frameFactory(new TraditionalScoringFrameFactory()).build();
//            printer.accept(game.get());
            System.out.println(game.get());
        } catch (FileNotFoundException | InvalidInputException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            System.out.println(NO_FILEPATH_PROVIDED_MSG);
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }
    }

}
