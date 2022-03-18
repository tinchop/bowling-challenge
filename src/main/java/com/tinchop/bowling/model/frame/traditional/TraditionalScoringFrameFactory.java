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

            boolean shouldAddLastFrame = frames.size() == (FRAMES_PER_GAME - 1);
            if (shouldAddLastFrame) {
                frames.add(buildLastFrame(chances, i));
                break;
            } else if (isInputStrike(chances.get(i))) {
                frames.add(StrikeFrame.builder().build());
            } else if (isInputSpare(chances.get(i), chances.get(i + 1))) {
                frames.add(SpareFrame.builder().firstChance(chances.get(i)).build());
                i++;
            } else {
                frames.add(OpenFrame.builder()
                        .firstChance(chances.get(i))
                        .secondChance(chances.get(i + 1)).build());
                i++;
            }
        }
        linkFrames(frames);

        return frames;
    }

    private TenthFrame buildLastFrame(List<String> chances, int tenthFrameStartIndex) {
        boolean thirdChanceExists = (chances.size() > tenthFrameStartIndex + 2);
        return TenthFrame.builder()
                .firstChance(translateChance(chances.get(tenthFrameStartIndex)))
                .secondChance(isInputSpare(chances.get(tenthFrameStartIndex), chances.get(tenthFrameStartIndex + 1)) ? OUTPUT_SPARE : translateChance(chances.get(tenthFrameStartIndex + 1)))
                .thirdChance(thirdChanceExists ? translateChance(chances.get(tenthFrameStartIndex + 2)) : StringUtils.EMPTY).build();
    }

    private void linkFrames(List<Frame> frames) {
        for (int i = 1; i < frames.size(); i++) frames.get(i).setPreviousFrame(frames.get(i - 1));
    }

    private String translateChance(String chance) {
        if (isMaxChanceScore(chance)) {
            return OUTPUT_STRIKE;
        }
        return chance;
    }

}
