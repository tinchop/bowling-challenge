package com.tinchop.bowling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.tinchop.bowling.constant.BowlingChallengeMessages.NO_FILEPATH_PROVIDED;


public class BowlingChallenge {

    private static final int EXIT_CODE_UNSUCCESSFUL = 1;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(NO_FILEPATH_PROVIDED);
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }

        try {
            var file = new File(args[0]);
            var input = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
