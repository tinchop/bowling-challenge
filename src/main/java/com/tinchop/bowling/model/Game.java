package com.tinchop.bowling.model;

import lombok.Builder;

import java.util.List;

@Builder
public class Game {

    private List<Player> bowlers;

}
