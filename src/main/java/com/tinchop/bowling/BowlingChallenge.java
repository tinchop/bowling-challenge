package com.tinchop.bowling;

import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.Printable;
import com.tinchop.bowling.model.frame.FrameFactory;
import com.tinchop.bowling.parser.FileParser;
import com.tinchop.bowling.parser.InputValidator;
import com.tinchop.bowling.parser.InvalidInputException;

import java.io.FileNotFoundException;
import java.util.function.Consumer;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.EXIT_CODE_UNSUCCESSFUL;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.NO_FILEPATH_PROVIDED_MSG;


public class BowlingChallenge {

    static Consumer<Printable> printer = printable -> System.out.println(printable.get());

    public static void main(String[] args) {

        validateArgs(args);

        var parser = FileParser.builder().inputValidator(new InputValidator()).build();

        try {
            Printable game = Game.builder().parsedFile(parser.parse(args[0])).frameFactory(new FrameFactory()).build();
            printer.accept(game);
        } catch (InvalidInputException e) {
            printer.accept(e);
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }

    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            System.err.println(NO_FILEPATH_PROVIDED_MSG);
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }
    }

}
