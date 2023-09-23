package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.command.Parser;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private static final String TYPE = "Deadline";
    protected LocalDateTime by;

    public Deadline(String name, String by) throws DukeException {
        super(name, TYPE);
        this.by = Parser.parseDateTime(by);
    }

    @Override
    public void validate() throws DukeException {
        super.validate();

        if (by == null) {
            throw new DukeException("By cannot be empty...");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                by.format(ProgramConstants.DATETIME_PRINT_FORMATTER)
        );
    }

    @Override
    public Serializer serialize() throws DukeException {
        Serializer serializer = super.serialize();
        serializer.putString(by.format(ProgramConstants.DATETIME_INPUT_FORMATTER));
        return serializer;
    }

    @Override
    public void deserialize(Serializer serializer) throws DukeException, IOException {
        super.deserialize(serializer);
        by = LocalDateTime.parse(serializer.readString(), ProgramConstants.DATETIME_INPUT_FORMATTER);
    }
}
