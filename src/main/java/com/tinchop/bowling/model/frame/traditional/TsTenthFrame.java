package com.tinchop.bowling.model.frame.traditional;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_NEW_LINE;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_TAB;

public final class TsTenthFrame extends TraditionalScoringFrame {

    @Builder
    public TsTenthFrame(@NonNull String firstChance, @NonNull String secondChance, String thirdChance) {
        super(List.of(firstChance, secondChance, thirdChance));
    }

    protected void calculateScore() {
        super.calculateScore();
        score += (parseChance(getFirstChance()) + parseChance(getSecondChance()) + parseChance(getThirdChance()));
    }

    @Override
    public String get() {
        var pinfallsLine = getFirstChance() + OUTPUT_TAB + getSecondChance() + OUTPUT_TAB + getThirdChance() + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }

    private String getSecondChance() {
        return chances.get(1);
    }

    private String getThirdChance() {
        return chances.get(2);
    }

    @Override
    public Integer sayStrikeBonusToPrevious() {
        return parseChance(getFirstChance()) + parseChance(getSecondChance());
    }

}
