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
    private InputValidator inputValidator;

    public Map<String, List<String>> parse(String filePath) throws FileNotFoundException {

        var scanner = createScanner(filePath);

        var bulk = new LinkedHashMap<String, List<String>>();
        while (scanner.hasNext()) {
            var line = scanner.next();
            inputValidator.validateLine(line);
            var splitLine = line.split(COLUMN_DELIMITER);

            bulk.compute(splitLine[0], (playerName, chances) -> {
                if (chances == null) {
                    chances = new ArrayList<>();
                }
                chances.add(splitLine[1].trim());
                return chances;
            });

        }

        scanner.close();

        inputValidator.validateBulk(bulk);

        return bulk;
    }

    private Scanner createScanner(String filePath) throws FileNotFoundException {
        var scanner = new Scanner(new File(filePath));
        scanner.useDelimiter(System.getProperty("line.separator"));
        return scanner;
    }

}
