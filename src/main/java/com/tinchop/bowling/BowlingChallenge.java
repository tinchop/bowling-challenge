package com.tinchop.bowling;

import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.Printable;
import com.tinchop.bowling.model.frame.traditional.TraditionalScoringFrameFactory;
import com.tinchop.bowling.parser.FileParser;
import com.tinchop.bowling.parser.validation.InvalidInputException;
import com.tinchop.bowling.parser.validation.TraditionalScoringBulkValidator;
import com.tinchop.bowling.parser.validation.TraditionalScoringLineValidator;

import java.io.FileNotFoundException;
import java.util.function.Consumer;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.EXIT_CODE_UNSUCCESSFUL;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.NO_FILEPATH_PROVIDED_MSG;


public class BowlingChallenge {

    private static final Consumer<String> printer = System.out::println;

    public static void main(String[] args) {

        validateArgs(args);
        var filePath = args[0];

        var parser = FileParser.builder()
                .lineValidator(new TraditionalScoringLineValidator())
                .bulkValidator(new TraditionalScoringBulkValidator())
                .build();

        try {
            Printable game = Game.builder().parsedFile(parser.parse(filePath)).frameFactory(new TraditionalScoringFrameFactory()).build();
            printer.accept(game.get());
        } catch (FileNotFoundException | InvalidInputException e) {
            printer.accept(e.getMessage());
        }

    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            System.err.println(NO_FILEPATH_PROVIDED_MSG);
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }
    }

}
