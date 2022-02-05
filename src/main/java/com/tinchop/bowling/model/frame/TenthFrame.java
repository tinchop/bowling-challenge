package com.tinchop.bowling.model.frame;

import lombok.Setter;

public class TenthFrame extends Frame {

    @Setter
    private String thirdChance;

    protected void calculateScore() {
        super.calculateScore();
        score += (parseChance(firstChance) + parseChance(secondChance) + parseChance(thirdChance));
    }

    @Override
    public String getPrintableText() {
        return null;
    }
}
