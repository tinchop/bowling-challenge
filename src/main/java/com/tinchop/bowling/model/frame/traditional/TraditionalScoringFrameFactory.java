package com.tinchop.bowling.model.frame.traditional;

import com.tinchop.bowling.model.frame.Frame;
import com.tinchop.bowling.model.frame.FrameFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.shared.BowlingUtils.*;

public class TraditionalScoringFrameFactory implements FrameFactory {

    public List<Frame> createFrames(List<String> chances) {

        var frames = new ArrayList<Frame>();

        for (int i = 0; i < chances.size() - 1; i++) {

            if (frames.size() == (FRAMES_PER_GAME - 1)) {
                frames.add(TsTenthFrame.builder()
                        .firstChance(translateChance(chances.get(i)))
                        .secondChance(isInputSpare(chances.get(i), chances.get(i + 1)) ? OUTPUT_SPARE : translateChance(chances.get(i + 1)))
                        .thirdChance((chances.size() > i + 2) ? translateChance(chances.get(i + 2)) : StringUtils.EMPTY).build());
                i = chances.size();
            } else if (isInputStrike(chances.get(i))) {
                frames.add(TsStrikeFrame.builder().build());
            } else if (isInputSpare(chances.get(i), chances.get(i + 1))) {
                frames.add(TsSpareFrame.builder().firstChance(chances.get(i)).build());
                i++;
            } else {
                frames.add(TsOpenFrame.builder().firstChance(chances.get(i)).secondChance(chances.get(i + 1)).build());
                i++;
            }
        }
        linkFrames(frames);

        return frames;
    }

    private String translateChance(String chance) {
        if (isMaxChanceScore(chance)) {
            return OUTPUT_STRIKE;
        }
        return chance;
    }

    private void linkFrames(List<Frame> frames) {
        for (int i = 1; i < frames.size(); i++) frames.get(i).setPreviousFrame(frames.get(i - 1));
    }

}
