package com.tinchop.bowling.parser;

import com.tinchop.bowling.model.frame.*;

import java.util.ArrayList;
import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public final class FrameFactory {

    public List<Frame> createFrames(List<String> chances) {

        var frames = new ArrayList<Frame>();

        for (int i = 0; i < chances.size() - 1; i++) {

            if (frames.size() == 9) {
                var frame = new TenthFrame();
                frame.setFirstChance(translateChance(chances.get(i)));
                if (isSpare(chances.get(i), chances.get(i + 1)) && !isPerfectScore(chances.get(i))) {
                    frame.setSecondChance(OUTPUT_SPARE);
                } else {
                    frame.setSecondChance(translateChance(chances.get(i + 1)));
                }
                if (chances.size() > i + 2) {
                    frame.setThirdChance(translateChance(chances.get(i + 2)));
                } else {
                    frame.setThirdChance("");
                }
                frames.add(frame);
                i = chances.size();
            } else if (INPUT_STRIKE.equals(chances.get(i))) {
                var frame = new StrikeFrame();
                frame.setFirstChance(OUTPUT_STRIKE);
                frames.add(frame);
            } else if (isSpare(chances.get(i), chances.get(i + 1))) {
                var frame = new SpareFrame();
                frame.setFirstChance(chances.get(i));
                frame.setSecondChance(OUTPUT_SPARE);
                frames.add(frame);
                i++;
            } else {
                var frame = new OpenFrame();
                frame.setFirstChance(chances.get(i));
                frame.setSecondChance(chances.get(i + 1));
                frames.add(frame);
                i++;
            }
            linkFrames(frames);

        }
        return frames;
    }

    private String translateChance(String chance) {
        if (isPerfectScore(chance)) {
            return OUTPUT_STRIKE;
        }
        return chance;
    }

    private boolean isSpare(String firstChance, String secondChance) {
        return isNumeric(firstChance) && isNumeric(secondChance) && (parseInt(firstChance) + parseInt(secondChance)) == 10;
    }

    private boolean isPerfectScore(String chance) {
        return INPUT_STRIKE.equals(chance);
    }

    private void linkFrames(List<Frame> frames) {
        if (frames.size() > 1) {
            var lastFrame = frames.get(frames.size() - 2);
            var newlyCreatedFrame = frames.get(frames.size() - 1);
            newlyCreatedFrame.setPreviousFrame(lastFrame);
            lastFrame.setNextFrame(newlyCreatedFrame);
        }
    }

}
