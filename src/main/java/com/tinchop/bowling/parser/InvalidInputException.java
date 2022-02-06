package com.tinchop.bowling.parser;

import com.tinchop.bowling.model.Printable;

public class InvalidInputException extends RuntimeException implements Printable {

    public InvalidInputException(String message) {
        super(message);
    }

    @Override
    public String get() {
        return getMessage();
    }
}
