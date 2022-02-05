package com.tinchop.bowling.model.frame;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.MAX_CHANCE_SCORE;

public class Strike extends Frame {

    protected void calculateScore() {
        super.calculateScore();
        score += (MAX_CHANCE_SCORE + nextFrame.sayStrikeBonusToPrevious());
    }

    protected Integer sayStrikeBonusToPrevious() {
        return MAX_CHANCE_SCORE + parseChance(nextFrame.firstChance);
    }


}
