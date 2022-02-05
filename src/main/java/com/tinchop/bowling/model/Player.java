package com.tinchop.bowling.model;

import com.tinchop.bowling.model.frame.Frame;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;


@Builder
public class Player implements Printable {

    @NonNull
    private String name;
    @NonNull
    private List<Frame> frames;

    @Override
    public String getPrintableText() {
        var nameLine = name + OUTPUT_NEW_LINE;
        var pinfallsLine = OUTPUT_PINFALLS + frames.stream().map(frame -> frame.getPrintableText().split(OUTPUT_NEW_LINE)[0]).collect(Collectors.joining()) + OUTPUT_NEW_LINE;
        var scoreLine = OUTPUT_SCORE + frames.stream().map(frame -> frame.getPrintableText().split(OUTPUT_NEW_LINE)[1]).collect(Collectors.joining()) + OUTPUT_NEW_LINE;
        return nameLine + pinfallsLine + scoreLine;
    }
}
