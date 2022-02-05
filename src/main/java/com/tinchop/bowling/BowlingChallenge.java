package com.tinchop.bowling;

import com.tinchop.bowling.factory.ChancesValidator;
import com.tinchop.bowling.factory.FrameFactory;
import com.tinchop.bowling.model.Game;
import com.tinchop.bowling.model.Player;
import com.tinchop.bowling.parser.GameFileParser;
import com.tinchop.bowling.parser.InvalidLineException;
import com.tinchop.bowling.parser.LineValidator;

import java.io.FileNotFoundException;

import static com.tinchop.bowling.constant.BowlingChallengeMessages.NO_FILEPATH_PROVIDED;


public class BowlingChallenge {

    private static final int EXIT_CODE_UNSUCCESSFUL = 1;

    public static void main(String[] args) {

        validateArgs(args);

        var parser = GameFileParser.builder().lineValidator(new LineValidator()).build();

        try {
            var parsedGame = parser.parse(args[0]);
            var frameFactory = FrameFactory.builder().chancesValidator(new ChancesValidator()).build();
            var players = parsedGame.keySet().stream().map(playerName -> Player.builder().name(playerName).frames(frameFactory.createFrames(parsedGame.get(playerName))).build()).toList();
            var game = Game.builder().players(players).build();

            System.out.println(game.getPrintableText());

        } catch (FileNotFoundException | InvalidLineException e) {
            System.err.println(e.getMessage());
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }

    }

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            System.err.println(NO_FILEPATH_PROVIDED);
            System.exit(EXIT_CODE_UNSUCCESSFUL);
        }
    }

}
