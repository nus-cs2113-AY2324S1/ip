package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.shared.DukeDateTime;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class Event extends Task {
    private static final String TYPE = "Event";
    protected DukeDateTime from;
    protected DukeDateTime to;

    public Event(String name, String from, String to) throws DukeException {
        super(name, TYPE);
        this.from = new DukeDateTime(from);
        this.to = new DukeDateTime(to);
    }

    @Override
    public void validate() throws DukeException {
        super.validate();

        if (from.getOriginalDatetime() == null) {
            throw new DukeException("From cannot be empty...");
        }

        if (to.getOriginalDatetime() == null) {
            throw new DukeException("To cannot be empty...");
        }

        if (from.getDateTime().isAfter(to.getDateTime())) {
            throw new DukeException("From cannot be set to after to...");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s | to: %s)",
                super.toString(),
                this.from,
                this.to
        );
    }

    @Override
    public Serializer serialize() throws DukeException, IOException {
        Serializer serializer = super.serialize();
        serializer.putSerializable(from);
        serializer.putSerializable(to);
        return serializer;
    }

    @Override
    public void deserialize(Serializer serializer) throws DukeException, IOException {
        super.deserialize(serializer);
        from = new DukeDateTime();
        from.deserialize(serializer.readSerializable());
        to = new DukeDateTime();
        to.deserialize(serializer.readSerializable());
    }
}
