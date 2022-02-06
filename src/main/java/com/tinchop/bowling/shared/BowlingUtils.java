package com.tinchop.bowling.shared;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.INPUT_STRIKE;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class BowlingUtils {

    public static boolean isStrike(String chanceInput) {
        return isNotEmpty(chanceInput) && isNumeric(chanceInput.trim()) && INPUT_STRIKE.equals(chanceInput);
    }

}
