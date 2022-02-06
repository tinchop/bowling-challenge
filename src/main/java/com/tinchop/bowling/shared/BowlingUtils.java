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
                && (parseInt(first.trim()) + parseInt(second.trim()) == 10);
        boolean spareWithFoul = (isFoul(first) && isMaxScore(second));
        return spareWithoutFoul || spareWithFoul;
    }

    public static boolean isFoul(String chance) {
        return isNotEmpty(chance) && FOUL.equals(chance.trim());
    }

    public static boolean isMaxScore(String chance) {
        return isNotEmpty(chance) && isNumeric(chance) && MAX_CHANCE_SCORE.equals(parseInt(chance.trim()));
    }

}
