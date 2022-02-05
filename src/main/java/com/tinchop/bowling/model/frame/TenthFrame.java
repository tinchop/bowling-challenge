package com.tinchop.bowling.model.frame;

import lombok.Setter;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_NEW_LINE;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_TAB;

public class TenthFrame extends Frame {

    @Setter
    private String thirdChance;

    protected void calculateScore() {
        super.calculateScore();
        score += (parseChance(firstChance) + parseChance(secondChance) + parseChance(thirdChance));
    }

    @Override
    public String getPrintableText() {
        var pinfallsLine = firstChance + OUTPUT_TAB + secondChance + OUTPUT_TAB + thirdChance + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }

}
