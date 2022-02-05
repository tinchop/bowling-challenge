package com.tinchop.bowling.model;

import lombok.Setter;

public class TenthFrame extends Frame {

    @Setter
    private String thirdChance;

    protected void calculateScore() {
        super.calculateScore();
        score += (parseChance(firstChance) + parseChance(secondChance) + parseChance(thirdChance));
    }

}
