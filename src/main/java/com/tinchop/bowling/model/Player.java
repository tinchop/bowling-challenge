package com.tinchop.bowling.model;

import com.tinchop.bowling.model.frame.Frame;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
public class Player {

    @Getter
    private String name;
    private List<Frame> frames;

}
