package com.gpt.dumpgpt.shared;

public class DukeException extends Exception {
    private final String ERROR_MESSAGE;

    public DukeException(String errorMessage) {
        this.ERROR_MESSAGE = errorMessage;
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
