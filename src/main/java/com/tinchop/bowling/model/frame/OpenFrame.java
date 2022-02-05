package com.tinchop.bowling.model.frame;

public class OpenFrame extends Frame {

    protected void calculateScore() {
        super.calculateScore();
        score += (parseChance(firstChance) + parseChance(secondChance));
    }

    @Override
    public String getPrintableText() {
        return null;
    }
}
