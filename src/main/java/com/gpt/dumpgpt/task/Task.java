package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.IOException;

public class Task {
    protected String type;
    protected String name;
    protected Boolean isDone;

    public Task(String name, String type) {
        this.type = type;
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getNameWithStatus() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", getName());
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getNameWithStatus();
    }

    protected Serializer serialize() throws DukeException {
        Serializer serializer = new Serializer();
        serializer.setType(type);
        serializer.putBoolean(isDone);
        serializer.putString(name);
        return serializer;
    }

    protected void deserialize(Serializer serializer) throws DukeException, IOException {
        if (!serializer.getType().equals(type)) {
            throw new DukeException("Unexpected type...");
        }
        isDone = serializer.readBoolean();
        name = serializer.readString();
    }

    public void validate() throws DukeException {
        if (name == null || name.isBlank()) {
            throw new DukeException(
                    String.format("%s name cannot be empty...", type)
            );
        }
    }
}
