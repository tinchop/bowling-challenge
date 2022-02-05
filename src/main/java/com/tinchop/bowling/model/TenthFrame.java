package com.tinchop.bowling.model;

import lombok.Setter;

public class TenthFrame extends Frame {

    @Setter
    private String thirdChance;

    void calculateScore() {
        score = previousFrame.getScore() + parseChance(firstChance) + parseChance(secondChance);
        if (!isOpenFrame()) {
            score += parseChance(thirdChance);
        }
    }

}
