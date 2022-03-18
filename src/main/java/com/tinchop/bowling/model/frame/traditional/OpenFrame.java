package com.tinchop.bowling.model.frame.traditional;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_NEW_LINE;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_TAB;

public final class OpenFrame extends TraditionalScoringFrame {

    @Builder
    public OpenFrame(@NonNull String firstChance, @NonNull String secondChance) {
        super(List.of(firstChance, secondChance));
    }

    protected void calculateScore() {
        super.calculateScore();
        score += (parseChance(getFirstChance()) + parseChance(getSecondChance()));
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
