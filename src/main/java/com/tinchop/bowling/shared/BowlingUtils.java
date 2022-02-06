package com.tinchop.bowling.shared;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class BowlingUtils {

    public static boolean isStrike(String chanceInput) {
        return isNotEmpty(chanceInput) && isNumeric(chanceInput.trim()) && INPUT_STRIKE.equals(chanceInput);
    }

    public static boolean isSpare(String first, String second) {
        boolean spareWithoutFoul = isNotEmpty(first) && isNumeric(first.trim())
                && isNotEmpty(second) && isNumeric(second.trim())
                && MAX_CHANCE_SCORE.equals(sumChances(first, second));
        boolean spareWithFoul = (isFoul(first) && isMaxScore(second));
        return spareWithoutFoul || spareWithFoul;
    }

    public static boolean isFoul(String chance) {
        return isNotEmpty(chance) && FOUL.equals(chance.trim());
    }

    public static boolean isMaxScore(String chance) {
        return isNotEmpty(chance) && isNumeric(chance) && MAX_CHANCE_SCORE.equals(parseInt(chance.trim()));
    }

    public static Integer sumChances(String first, String second) {
        first = FOUL.equals(first.trim()) ? "0" : first.trim();
        second = FOUL.equals(second.trim()) ? "0" : second.trim();
        return parseInt(first) + parseInt(second);
    }

}
