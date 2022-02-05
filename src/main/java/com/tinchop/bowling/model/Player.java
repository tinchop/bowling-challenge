package com.tinchop.bowling.model;

import com.tinchop.bowling.model.frame.Frame;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.*;


@Builder
public class Player implements Printable {

    @NonNull
    private String name;
    @NonNull
    private List<Frame> frames;

    @Override
    public String getPrintableText() {
        var nameLine = name + NEW_LINE;
        var pinfallsLine = OUTPUT_PINFALLS + NEW_LINE;
        var scoreLine = OUTPUT_SCORE + NEW_LINE;
        return nameLine + pinfallsLine + scoreLine;
    }
}
