package com.tinchop.bowling.model.frame;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_TAB;

public class Strike extends Frame {

    protected void calculateScore() {
        super.calculateScore();
        score += (MAX_CHANCE_SCORE + nextFrame.sayStrikeBonusToPrevious());
    }

    protected Integer sayStrikeBonusToPrevious() {
        return MAX_CHANCE_SCORE + parseChance(nextFrame.firstChance);
    }

    @Override
    public String getPrintableText() {
        var pinfallsLine = OUTPUT_TAB + firstChance + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }
}
