package com.tinchop.bowling.model.frame;

import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_NEW_LINE;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_TAB;

public class TenthFrame extends TraditionalScoringFrame {

    @Setter
    private String thirdChance;

    @Builder
    public TenthFrame(@NonNull String firstChance, @NonNull String secondChance, String thirdChance) {
        super(firstChance, secondChance, null, null, null);
        this.thirdChance = thirdChance;
    }

    protected void calculateScore() {
        super.calculateScore();
        score += (parseChance(firstChance) + parseChance(secondChance) + parseChance(thirdChance));
    }

    @Override
    public String get() {
        var pinfallsLine = firstChance + OUTPUT_TAB + secondChance + OUTPUT_TAB + thirdChance + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }

}
