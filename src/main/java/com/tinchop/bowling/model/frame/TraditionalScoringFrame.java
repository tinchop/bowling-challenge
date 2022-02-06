package com.tinchop.bowling.model.frame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static java.lang.Integer.parseInt;

@AllArgsConstructor
public abstract class TraditionalScoringFrame implements Frame {

    @Getter
    @Setter
    protected String firstChance;
    @Setter
    protected String secondChance;
    protected Frame nextFrame;
    @Setter
    protected Frame previousFrame;
    protected Integer score;

    @Override
    public Integer getScore() {
        if (score == null) calculateScore();
        return score;
    }

    @Override
    public void setNextFrame(Frame frame) {
        this.nextFrame = frame;
    }

    protected void calculateScore() {
        score = 0;
        if (previousFrame != null) score += previousFrame.getScore();
    }

    @Override
    public Integer sayStrikeBonusToPrevious() {
        return parseChance(firstChance) + parseChance(secondChance);
    }

    public Integer parseChance(String chance) {
        if (OUTPUT_STRIKE.equals(chance)) {
            return MAX_CHANCE_SCORE;
        } else if (OUTPUT_SPARE.equals(chance)) {
            return MAX_CHANCE_SCORE - parseInt(firstChance);
        } else if (FOUL.equals(chance) || StringUtils.isEmpty(chance)) {
            return FOUL_SCORE;
        } else return parseInt(chance);
    }

    @Override
    public String get() {
        var pinfallsLine = firstChance + OUTPUT_TAB + secondChance + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }

}
