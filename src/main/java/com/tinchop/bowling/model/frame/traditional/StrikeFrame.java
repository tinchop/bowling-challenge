package com.tinchop.bowling.model.frame.traditional;

import lombok.Builder;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public final class StrikeFrame extends TraditionalScoringFrame {

    @Builder
    public StrikeFrame() {
        super(List.of(OUTPUT_STRIKE));
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
        var pinfallsLine = OUTPUT_TAB + getFirstChance() + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }

}
