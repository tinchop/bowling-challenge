package com.tinchop.bowling.model.frame.traditional;

import com.tinchop.bowling.model.frame.Frame;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static java.lang.Integer.parseInt;

public abstract sealed class TraditionalScoringFrame implements Frame permits TsOpenFrame, TsStrikeFrame, TsSpareFrame, TsTenthFrame {

    @NonNull
    protected List<String> chances;
    @Setter
    protected Frame nextFrame;
    protected Frame previousFrame;
    protected Integer score;

    public TraditionalScoringFrame(@NonNull List<String> chances) {
        this.chances = chances;
    }

    @Override
    public String getFirstChance() {
        return chances.get(0);
    }

    @Override
    public Integer getScore() {
        if (score == null) calculateScore();
        return score;
    }

    @Override
    public void setPreviousFrame(Frame frame) {
        this.previousFrame = frame;
        this.previousFrame.setNextFrame(this);
    }

    protected void calculateScore() {
        score = 0;
        if (previousFrame != null) score += previousFrame.getScore();
    }

    protected Integer parseChance(String chance) {
        if (OUTPUT_STRIKE.equals(chance)) {
            return MAX_CHANCE_SCORE;
        } else if (OUTPUT_SPARE.equals(chance)) {
            return MAX_CHANCE_SCORE - parseInt(getFirstChance());
        } else if (FOUL.equals(chance) || StringUtils.isEmpty(chance)) {
            return FOUL_SCORE;
        } else return parseInt(chance);
    }

}
