package com.tinchop.bowling.parser;

import com.tinchop.bowling.model.frame.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static com.tinchop.bowling.shared.BowlingUtils.isMaxChanceScore;
import static com.tinchop.bowling.shared.BowlingUtils.isSpare;

public final class FrameFactory {

    public List<Frame> createFrames(List<String> chances) {

        var frames = new ArrayList<Frame>();

        for (int i = 0; i < chances.size() - 1; i++) {

            if (frames.size() == (FRAMES_PER_GAME - 1)) {
                frames.add(TenthFrame.builder()
                        .firstChance(translateChance(chances.get(i)))
                        .secondChance(isSpare(chances.get(i), chances.get(i + 1)) ? OUTPUT_SPARE : translateChance(chances.get(i + 1)))
                        .thirdChance((chances.size() > i + 2) ? chances.get(i + 2) : StringUtils.EMPTY).build());
                i = chances.size();
            } else if (INPUT_STRIKE.equals(chances.get(i))) {
                frames.add(StrikeFrame.builder().firstChance(OUTPUT_STRIKE).build());
            } else if (isSpare(chances.get(i), chances.get(i + 1))) {
                frames.add(SpareFrame.builder().firstChance(chances.get(i)).secondChance(OUTPUT_SPARE).build());
                i++;
            } else {
                frames.add(OpenFrame.builder().firstChance(chances.get(i)).secondChance(chances.get(i + 1)).build());
                i++;
            }
            linkFrames(frames);

        }

        return frames;
    }

    private String translateChance(String chance) {
        if (isMaxChanceScore(chance)) {
            return OUTPUT_STRIKE;
        }
        return chance;
    }

    private void linkFrames(List<Frame> frames) {
        if (frames.size() > 1) {
            var previousFrame = frames.get(frames.size() - 2);
            var newlyCreatedFrame = frames.get(frames.size() - 1);
            newlyCreatedFrame.setPreviousFrame(previousFrame);
            previousFrame.setNextFrame(newlyCreatedFrame);
        }
    }

}
