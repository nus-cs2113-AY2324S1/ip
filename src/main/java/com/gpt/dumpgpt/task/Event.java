package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.command.Parser;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class Event extends Task {
    private static final String TYPE = "Event";
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String name, String from, String to) throws DukeException {
        super(name, TYPE);
        this.from = Parser.parseDateTime(from);
        this.to = Parser.parseDateTime(to);
    }

    @Override
    public void validate() throws DukeException {
        super.validate();

        if (from == null) {
            throw new DukeException("From cannot be empty...");
        }

        if (to == null) {
            throw new DukeException("To cannot be empty...");
        }

        if (from.isAfter(to)) {
            throw new DukeException("From cannot be set to after to...");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s | to: %s)",
                super.toString(),
                this.from.format(ProgramConstants.DATETIME_PRINT_FORMATTER),
                this.to.format(ProgramConstants.DATETIME_PRINT_FORMATTER)
        );
    }

    @Override
    public Serializer serialize() throws DukeException {
        Serializer serializer = super.serialize();
        serializer.putString(from.format(ProgramConstants.DATETIME_INPUT_FORMATTER));
        serializer.putString(to.format(ProgramConstants.DATETIME_INPUT_FORMATTER));
        return serializer;
    }

    @Override
    public void deserialize(Serializer serializer) throws DukeException, IOException {
        super.deserialize(serializer);
        from = LocalDateTime.parse(serializer.readString(), ProgramConstants.DATETIME_INPUT_FORMATTER);
        to = LocalDateTime.parse(serializer.readString(), ProgramConstants.DATETIME_INPUT_FORMATTER);
    }
}
