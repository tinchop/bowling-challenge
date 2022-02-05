package com.tinchop.bowling.model.frame;

import com.tinchop.bowling.model.Printable;
import lombok.Setter;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;

public abstract class Frame implements Printable {

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

    protected Integer sayStrikeBonusToPrevious() {
        return parseChance(firstChance) + parseChance(secondChance);
    }

    protected Integer parseChance(String chance) {
        if (STRIKE.equals(chance)) {
            return MAX_CHANCE_SCORE;
        } else if (PAR.equals(chance)) {
            return MAX_CHANCE_SCORE - Integer.parseInt(firstChance);
        } else if (FOUL.equals(chance)) {
            return FOUL_SCORE;
        } else return Integer.parseInt(chance);
    }

}
