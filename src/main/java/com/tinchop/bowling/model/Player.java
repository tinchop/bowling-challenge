package com.tinchop.bowling.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
public class Player {

    @Getter
    private String name;
    private List<Frame> frames;

}
