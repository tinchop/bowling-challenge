package com.tinchop.bowling.model.frame;

import com.tinchop.bowling.model.Printable;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static java.lang.Integer.parseInt;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Frame implements Printable {

    @Setter
    protected String firstChance;
    @Setter
    protected String secondChance;
    @Setter
    protected Frame nextFrame;
    @Setter
    protected Frame previousFrame;
    protected Integer score;

    public Integer getScore() {
        if (score == null) {
            calculateScore();
        }
        return score;
    }

    protected void calculateScore() {
        score = 0;
        if (previousFrame != null) {
            score += previousFrame.getScore();
        }
    }

    protected Integer sayStrikeBonusToPrevious() {
        return parseChance(firstChance) + parseChance(secondChance);
    }

    protected Integer parseChance(String chance) {
        if (OUTPUT_STRIKE.equals(chance)) {
            return MAX_CHANCE_SCORE;
        } else if (OUTPUT_SPARE.equals(chance)) {
            return MAX_CHANCE_SCORE - parseInt(firstChance);
        } else if (FOUL.equals(chance)) {
            return FOUL_SCORE;
        } else if (StringUtils.isEmpty(chance)) {
            return 0;
        } else return parseInt(chance);
    }

    @Override
    public String getPrintableText() {
        var pinfallsLine = firstChance + OUTPUT_TAB + secondChance + OUTPUT_TAB + OUTPUT_NEW_LINE;
        var scoreLine = getScore() + OUTPUT_TAB + OUTPUT_TAB;
        return pinfallsLine + scoreLine;
    }

}
