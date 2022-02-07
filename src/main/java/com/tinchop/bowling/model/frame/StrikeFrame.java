package com.tinchop.bowling.model.frame;

import lombok.Builder;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public class StrikeFrame extends TraditionalScoringFrame {

    @Builder
    public StrikeFrame() {
        super(OUTPUT_STRIKE, null, null, null, null);
    }

    protected void calculateScore() {
        super.calculateScore();
        score += (MAX_CHANCE_SCORE + nextFrame.sayStrikeBonusToPrevious());
    }

    public Integer sayStrikeBonusToPrevious() {
        return MAX_CHANCE_SCORE + parseChance(nextFrame.getFirstChance());
    }

    @Override
    public String get() {
        var pinfallsLine = OUTPUT_TAB + firstChance + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }
}
