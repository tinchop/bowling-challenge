package com.tinchop.bowling.model.frame;

import lombok.Builder;
import lombok.NonNull;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.MAX_CHANCE_SCORE;

public class SpareFrame extends Frame {

    @Builder
    public SpareFrame(@NonNull String firstChance, @NonNull String secondChance) {
        super(firstChance, secondChance, null, null, null);
    }

    protected void calculateScore() {
        super.calculateScore();
        score += (MAX_CHANCE_SCORE + parseChance(nextFrame.firstChance));
    }

}
