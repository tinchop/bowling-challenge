package com.tinchop.bowling.model.frame;

import java.util.List;

public interface FrameFactory {

    List<Frame> createFrames(List<String> chances);

}
