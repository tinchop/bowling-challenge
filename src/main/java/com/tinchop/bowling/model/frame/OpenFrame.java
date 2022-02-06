package com.tinchop.bowling.model.frame;

import lombok.Builder;
import lombok.NonNull;

public class OpenFrame extends TraditionalScoringFrame {

    @Builder
    public OpenFrame(@NonNull String firstChance, @NonNull String secondChance) {
        super(firstChance, secondChance, null, null, null);
    }

    protected void calculateScore() {
        super.calculateScore();
        score += (parseChance(firstChance) + parseChance(secondChance));
    }

}
