package com.tinchop.bowling.model;

import lombok.Setter;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.PAR;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.STRIKE;

public class Frame {

    @Setter
    String firstChance;
    @Setter
    String secondChance;
    @Setter
    private Frame nextFrame;
    @Setter
    Frame previousFrame;
    Integer score;


    void calculateScore() {
        if (isOpenFrame() && previousFrame != null) {
            score = previousFrame.getScore() + Integer.parseInt(firstChance) + Integer.parseInt(secondChance);
        } else if (isOpenFrame() && previousFrame == null) {
            score = Integer.parseInt(firstChance) + Integer.parseInt(secondChance);
        } else if (!isOpenFrame() && previousFrame != null) {
            score = previousFrame.getScore() + 10 + nextFrame.giveBonusToPrevious();
        } else {
            score = 10 + nextFrame.giveBonusToPrevious();
        }
    }

    public Integer getScore() {
        if (score == null) {
            calculateScore();
        }
        return score;
    }

    boolean isStrike() {
        return STRIKE.equals(firstChance);
    }

    boolean isPar() {
        return PAR.equals(secondChance);
    }

    boolean isOpenFrame() {
        return !isStrike() && !isPar();
    }

    private Integer giveBonusToPrevious() {
        if (previousFrame.isPar()) {
            return parseChance(firstChance);
        } else if (previousFrame.isStrike() && !isStrike()) {
            return parseChance(firstChance) + parseChance(secondChance);
        } else if (previousFrame.isStrike() && isStrike() && nextFrame != null) {
            return 10 + parseChance(nextFrame.firstChance);
        } else if (previousFrame.isStrike() && isStrike() && nextFrame == null) {
            return 10 + parseChance(secondChance);
        }
        return 0;
    }

    Integer parseChance(String chance) {
        if (STRIKE.equals(chance)) {
            return 10;
        } else if (PAR.equals(chance)) {
            return 10 - Integer.parseInt(firstChance);
        } else return Integer.parseInt(chance);
    }
}
