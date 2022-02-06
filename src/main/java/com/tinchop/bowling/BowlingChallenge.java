package com.tinchop.bowling;

import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.frame.FrameFactory;
import com.tinchop.bowling.parser.FileParser;
import com.tinchop.bowling.parser.InputValidator;
import com.tinchop.bowling.parser.InvalidInputException;

import java.io.FileNotFoundException;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.EXIT_CODE_UNSUCCESSFUL;
import static com.tinchop.bowling.constant.BowlingChallengeMessages.NO_FILEPATH_PROVIDED_MSG;


public class BowlingChallenge {

    public static void main(String[] args) {

        validateArgs(args);

        var parser = FileParser.builder().inputValidator(new InputValidator()).build();

        try {
            var game = Game.builder().parsedFile(parser.parse(args[0])).frameFactory(new FrameFactory()).build();
            System.out.println(game.getPrintableText());

        } catch (FileNotFoundException | InvalidInputException e) {
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
