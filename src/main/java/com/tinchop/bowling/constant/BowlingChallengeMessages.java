package com.tinchop.bowling.constant;

public class BowlingChallengeMessages {

    public static final String OUTPUT_INVALID_LINE = "Invalid line: ";
    public static final String NO_FILEPATH_PROVIDED_MSG = "Please provide a valid filepath as an argument.";
    public static final String NON_EMPTY_FILE_RULE_MSG = "The file cannot be empty.";
    public static final String NUMERIC_OR_FOUL_SCORE_VALUE_RULE_MSG = "Score values can only be either integers (from 0 to 10) or F (Foul).";
    public static final String TWO_COLUMNS_RULE_MSG = "Each line must have exactly 2 tab-separated columns: one with the player name and one with the score of the chance represented.";
    public static final String VALID_CHANCES_COUNT_RULE_MSG = "The number of chances is not valid.";
    public static final String VALID_PINFALL_COUNT_RULE_MSG = "The number of pinfalls per frame (except the last one) cannot be more than 10.";

}
