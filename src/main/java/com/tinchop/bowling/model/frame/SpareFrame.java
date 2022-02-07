package com.tinchop.bowling.model.frame;

import lombok.Builder;
import lombok.NonNull;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.MAX_CHANCE_SCORE;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_SPARE;

public class SpareFrame extends TraditionalScoringFrame {

    @Builder
    public SpareFrame(@NonNull String firstChance) {
        super(firstChance, OUTPUT_SPARE, null, null, null);
    }

    protected void calculateScore() {
        super.calculateScore();
        score += (MAX_CHANCE_SCORE + parseChance(nextFrame.getFirstChance()));
    }

}
