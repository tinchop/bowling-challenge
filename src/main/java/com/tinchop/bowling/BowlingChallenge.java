package com.tinchop.bowling;

import static com.tinchop.bowling.constant.BowlingChallengeMessages.NO_FILEPATH_PROVIDED;


public class BowlingChallenge {

    private static final int EXIT_CODE_UNSUCCESSFUL = 1;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(NO_FILEPATH_PROVIDED);
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }

    }
}
