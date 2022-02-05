package com.tinchop.bowling.model.frame;

import lombok.Setter;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public abstract class Frame {

    @Setter
    protected String firstChance;
    @Setter
    protected String secondChance;
    @Setter
    protected Frame nextFrame;
    @Setter
    protected Frame previousFrame;
    protected Integer score;

    public Integer getScore() {
        if (score == null) {
            calculateScore();
        }
        return score;
    }

    protected void calculateScore() {
        score = 0;
        if (previousFrame != null) {
            score += previousFrame.getScore();
        }
    }

    protected Integer giveStrikeBonusToPrevious() {
        return parseChance(firstChance) + parseChance(secondChance);
    }

    protected Integer parseChance(String chance) {
        if (STRIKE.equals(chance)) {
            return MAX_CHANCE_SCORE;
        } else if (PAR.equals(chance)) {
            return MAX_CHANCE_SCORE - Integer.parseInt(firstChance);
        } else if (FOUL.equals(chance)) {
            return FOUL_SCOURE;
        } else return Integer.parseInt(chance);
    }

}
