package com.tinchop.bowling.factory;

import com.tinchop.bowling.model.frame.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.PAR;
import static com.tinchop.bowling.constant.BowlingChallengeConstants.STRIKE;

@Builder
public final class FrameFactory {

    private ChancesValidator chancesValidator;

    public List<Frame> createFrames(List<String> chances) {
        chancesValidator.validateChances(chances);

        var frames = new ArrayList<Frame>();

        for (int i = 0; i < chances.size(); i++) {

            if (frames.size() == 9) {
                var frame = new TenthFrame();
                frame.setFirstChance(chances.get(i));
                frame.setSecondChance(chances.get(i + 1));
                if (chances.size() > i + 2) {
                    frame.setThirdChance(chances.get(i + 2));
                } else {
                    frame.setThirdChance("");
                }
                frames.add(frame);
                i = chances.size();
            } else if (STRIKE.equals(chances.get(i))) {
                var frame = new Strike();
                frame.setFirstChance(chances.get(i));
                frames.add(frame);
            } else if (PAR.equals(chances.get(i + 1))) {
                var frame = new Par();
                frame.setFirstChance(chances.get(i));
                frame.setSecondChance(chances.get(i + 1));
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

    private void linkFrames(List<Frame> frames) {
        if (frames.size() > 1) {
            var lastFrame = frames.get(frames.size() - 2);
            var newlyCreatedFrame = frames.get(frames.size() - 1);
            newlyCreatedFrame.setPreviousFrame(lastFrame);
            lastFrame.setNextFrame(newlyCreatedFrame);
        }
    }

}
