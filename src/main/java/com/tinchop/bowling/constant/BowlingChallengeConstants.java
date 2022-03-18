package com.tinchop.bowling.constant;

public class BowlingChallengeConstants {

    public final static String INPUT_STRIKE = "10";
    public final static Integer INPUT_VALID_COLUMN_COUNT = 2;
    public final static String FOUL = "F";
    public final static Integer FRAMES_PER_GAME = 10;
    public final static Integer MAX_CHANCE_SCORE = 10;
    public final static Integer MAX_FRAME_SCORE = 30;
    public final static Integer MAX_POSSIBLE_CHANCES = 21;
    public final static Integer MIN_POSSIBLE_CHANCES = 11;
    public final static Integer MAX_CHANCES_TENTH_FRAME = 3;
    public final static Integer MIN_CHANCES_TENTH_FRAME = 2;
    public final static Integer FLOOR_SCORE_RANGE = 0;
    public final static Integer CEILING_SCORE_RANGE = 10;
    public final static Integer FOUL_SCORE = 0;
    public final static String COLUMN_DELIMITER = "\t";
    public final static String OUTPUT_NEW_LINE = "\n";
    public final static String OUTPUT_TAB = "\t";
    public final static String OUTPUT_HEADER = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";
    public final static String OUTPUT_PINFALLS = "Pinfalls\t";
    public final static String OUTPUT_SCORE = "Score\t\t";
    public final static String OUTPUT_STRIKE = "X";
    public final static String OUTPUT_SPARE = "/";
    public static final int EXIT_CODE_UNSUCCESSFUL = 1;

    public static final String POSITIVE_FOLDER = "src/test/resources/positive/";
    public static final String SCORES_FILEPATH = POSITIVE_FOLDER + "scores.txt";
    public static final String PERFECT_FILEPATH = POSITIVE_FOLDER + "perfect.txt";
    public static final String ZERO_FILEPATH = POSITIVE_FOLDER + "zero.txt";
    public static final String THREE_PLAYER_GAME_FILEPATH = POSITIVE_FOLDER + "three-player-game.txt";
    public static final String FOUR_PLAYER_GAME_FILEPATH = POSITIVE_FOLDER + "four-player-game.txt";
    public static final String NEGATIVE_FOLDER = "src/test/resources/negative/";
    public static final String EMPTY_FILEPATH = NEGATIVE_FOLDER + "empty.txt";
    public static final String FREE_TEXT_FILEPATH = NEGATIVE_FOLDER + "free-text.txt";
    public static final String EXTRA_SCORE_FILEPATH = NEGATIVE_FOLDER + "extra-score.txt";
    public static final String EXTRA_SCORE_2_FILEPATH = NEGATIVE_FOLDER + "extra-score-2.txt";
    public static final String INCOMPLETE_SCORE_FILEPATH = NEGATIVE_FOLDER + "incomplete-score.txt";
    public static final String INCOMPLETE_SCORE_2_FILEPATH = NEGATIVE_FOLDER + "incomplete-score-2.txt";
    public static final String INCOMPLETE_SCORE_3_FILEPATH = NEGATIVE_FOLDER + "incomplete-score-3.txt";
    public static final String INCOMPLETE_SCORE_4_FILEPATH = NEGATIVE_FOLDER + "incomplete-score-4.txt";
    public static final String INVALID_SCORE_FILEPATH = NEGATIVE_FOLDER + "invalid-score.txt";
    public static final String INVALID_SCORE_2_FILEPATH = NEGATIVE_FOLDER + "invalid-score-2.txt";
    public static final String NEGATIVE_FILEPATH = NEGATIVE_FOLDER + "negative.txt";

}
