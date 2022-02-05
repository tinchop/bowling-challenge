package com.tinchop.bowling.parser;

import lombok.Builder;
import lombok.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.tinchop.bowling.constant.BowlingChallengeConstants.COLUMN_DELIMITER;

@Builder
public class GameFileParser {

    @NonNull
    private LineValidator lineValidator;

    public Map<String, List<String>> parse(String filePath) throws FileNotFoundException {

        var scanner = createScanner(filePath);

        var parsedGame = new LinkedHashMap<String, List<String>>();
        while (scanner.hasNext()) {
            var line = lineValidator.validateLine(scanner.next());
            var splitLine = line.split(COLUMN_DELIMITER);

            parsedGame.compute(splitLine[0], (playerName, chances) -> {
                if (chances == null) {
                    chances = new ArrayList<>();
                }
                chances.add(splitLine[1].trim());
                return chances;
            });

        }

        scanner.close();
        return parsedGame;
    }

    private Scanner createScanner(String filePath) throws FileNotFoundException {
        var scanner = new Scanner(new File(filePath));
        scanner.useDelimiter(System.getProperty("line.separator"));
        return scanner;
    }

}
