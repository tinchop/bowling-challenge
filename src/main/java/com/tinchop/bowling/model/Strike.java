package com.tinchop.bowling.model;

public class Strike extends Frame {

    protected void calculateScore() {
        super.calculateScore();
        score += (10 + nextFrame.giveStrikeBonusToPrevious());
    }

    protected Integer giveStrikeBonusToPrevious() {
        return 10 + parseChance(nextFrame.firstChance);
    }


}
