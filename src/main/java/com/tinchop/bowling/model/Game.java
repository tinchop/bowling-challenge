package com.tinchop.bowling.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_HEADER;

@Builder
public class Game implements Printable {

    @Getter
    @NonNull
    private List<Player> players;

    @Override
    public String getPrintableText() {
        return OUTPUT_HEADER + players.stream().map(Player::getPrintableText).collect(Collectors.joining());
    }
}
