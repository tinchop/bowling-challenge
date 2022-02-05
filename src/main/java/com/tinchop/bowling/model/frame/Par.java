package com.tinchop.bowling.model.frame;

public class Par extends Frame {

    protected void calculateScore() {
        super.calculateScore();
        score += (10 + parseChance(nextFrame.firstChance));
    }

}
