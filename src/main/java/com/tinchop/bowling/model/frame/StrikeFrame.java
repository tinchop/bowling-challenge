package com.tinchop.bowling.model.frame;

import lombok.Builder;
import lombok.NonNull;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public class StrikeFrame extends Frame {

    @Builder
    public StrikeFrame(@NonNull String firstChance) {
        super(firstChance, null, null, null, null);
    }

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
