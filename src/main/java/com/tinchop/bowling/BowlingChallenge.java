package com.tinchop.bowling;

import com.tinchop.bowling.constant.BowlingChallengeMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BowlingChallenge {

    private static final int EXIT_CODE_UNSUCCESSFUL = 1;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(BowlingChallengeMessages.NO_FILEPATH_PROVIDED);
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
