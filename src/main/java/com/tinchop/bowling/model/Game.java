package com.tinchop.bowling.model;

import com.tinchop.bowling.model.frame.FrameFactory;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.OUTPUT_HEADER;

public class Game implements Printable {

    @Builder
    public Game(@NonNull Map<String, List<String>> parsedFile, @NonNull FrameFactory frameFactory) {
        players = parsedFile.keySet().stream().map(playerName -> Player.builder().name(playerName).frames(frameFactory.createFrames(parsedFile.get(playerName))).build()).toList();
    }

    @NonNull
    final private List<Player> players;

    @Override
    public String get() {
        return OUTPUT_HEADER + players.stream().map(Player::get).collect(Collectors.joining());
    }
}
