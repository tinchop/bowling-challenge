package com.tinchop.bowling.model;

import lombok.Setter;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public abstract class Frame {

    @Setter
    String firstChance;
    @Setter
    String secondChance;
    @Setter
    Frame nextFrame;
    @Setter
    Frame previousFrame;
    Integer score;


    protected void calculateScore() {
        score = 0;
        if (previousFrame != null) {
            score += previousFrame.getScore();
        }
    }

    ;

    public Integer getScore() {
        if (score == null) {
            calculateScore();
        }
        return score;
    }

    protected Integer giveStrikeBonusToPrevious() {
        return parseChance(firstChance) + parseChance(secondChance);
    }

    Integer parseChance(String chance) {
        if (STRIKE.equals(chance)) {
            return 10;
        } else if (PAR.equals(chance)) {
            return 10 - Integer.parseInt(firstChance);
        } else if (FOUL.equals(chance)) {
            return 0;
        } else return Integer.parseInt(chance);
    }
}
