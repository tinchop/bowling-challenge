package com.tinchop.bowling;

import com.tinchop.bowling.parser.GameFileParser;
import com.tinchop.bowling.parser.LineValidator;

import java.io.FileNotFoundException;

import static com.tinchop.bowling.constant.BowlingChallengeMessages.NO_FILEPATH_PROVIDED;


public class BowlingChallenge {

    private static final int EXIT_CODE_UNSUCCESSFUL = 1;

    public static void main(String[] args) {

        validateArgs(args);

        var parser = GameFileParser.builder().lineValidator(new LineValidator()).build();

        try {
            parser.parse(args[0]);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }


    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            System.err.println(NO_FILEPATH_PROVIDED);
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }
    }

}
