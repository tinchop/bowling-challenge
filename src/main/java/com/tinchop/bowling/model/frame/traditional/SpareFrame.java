package com.tinchop.bowling.model.frame.traditional;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public final class SpareFrame extends TraditionalScoringFrame {

    @Builder
    public SpareFrame(@NonNull String firstChance) {
        super(List.of(firstChance, OUTPUT_SPARE));
    }

    protected void calculateScore() {
        super.calculateScore();
        score += (MAX_CHANCE_SCORE + parseChance(nextFrame.getFirstChance()));
    }

    private String getSecondChance() {
        return this.chances.get(1);
    }

    @Override
    public String get() {
        var pinfallsLine = getFirstChance() + OUTPUT_TAB + getSecondChance() + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }

    @Override
    public Integer sayStrikeBonusToPrevious() {
        return parseChance(getFirstChance()) + parseChance(getSecondChance());
    }

}
