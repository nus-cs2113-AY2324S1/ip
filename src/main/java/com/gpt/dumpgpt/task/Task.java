package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ApplicationState;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.*;
import java.util.ArrayList;

public class Task {
    private final static String TASKS_FILE = "data/tasks.bin";
    private final static String STATE_KEY = "Tasks";
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
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
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
        serializer.assignType(type);
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

    public static void addTask(Task task) {
        ArrayList<Task> tasks = getTasks();
        tasks.add(task);
    }

    public static boolean deleteTask(Task task) {
        ArrayList<Task> tasks = getTasks();
        return tasks.remove(task);
    }

    public static Task getTask(int pos) {
        ArrayList<Task> tasks = getTasks();
        if (pos < 0 || pos >= tasks.size()) {
            return null;
        }
        return tasks.get(pos);
    }

    public static Task getTask(Command command) {
        int taskNumber = ProgramConstants.parsePositiveNumber(command.getArguments());
        return getTask(--taskNumber);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Task> getTasks() {
        ApplicationState appState = ApplicationState.getAppState();
        Object tasks = appState.getStateObject(STATE_KEY);
        if (tasks == null) {
            tasks = new ArrayList<Task>();
            appState.setStateObject(STATE_KEY, tasks);
        }
        return (ArrayList<Task>) tasks;
    }

    private static Task readTask(Serializer serializer) throws DukeException, IOException {
        String type = serializer.getType();
        Task task = null;
        switch (type) {
        case "Todo":
            task = new Todo("");
            task.deserialize(serializer);
            break;
        case "Deadline":
            task = new Deadline("", "");
            task.deserialize(serializer);
            break;
        case "Event":
            task = new Event("", "", "");
            task.deserialize(serializer);
            break;
        }
        return task;
    }

    public static void restoreTasks() throws DukeException, IOException {
        File tasksFile = new File(TASKS_FILE);
        if (!tasksFile.exists()) {
            return;
        }

        if (!tasksFile.canRead()) {
            throw new DukeException(TASKS_FILE + " is not readable...");
        }

        try (FileInputStream inputStream = new FileInputStream(tasksFile)) {
            Serializer serializer = new Serializer(inputStream);
            serializer.readObjectInfo();
            if (!serializer.getType().equals(STATE_KEY)) {
                throw new DukeException("Failed to restore old tasks...");
            }

            ArrayList<Task> tasks = getTasks();
            int totalTasks = serializer.readInt();
            byte[] tasksBytes = serializer.readBytes();
            ByteArrayInputStream taskStream = new ByteArrayInputStream(tasksBytes);

            Serializer tasksSerializer = new Serializer(taskStream);
            for (int i = 0; i < totalTasks; ++i) {
                tasksSerializer.readObjectInfo();
                Task task = readTask(tasksSerializer);
                if (task == null) {
                    throw new DukeException("Failed to restore old tasks...");
                }
                tasks.add(task);
            }
        }
    }

    public static void saveTasks() throws DukeException, IOException {
        File tasksFile = new File(TASKS_FILE);
        tasksFile.getParentFile().mkdirs();
        tasksFile.createNewFile();
        if (!tasksFile.canWrite()) {
            throw new DukeException(TASKS_FILE + " is not writable...");
        }

        ArrayList<Task> tasks = getTasks();
        try (
                FileOutputStream outputStream = new FileOutputStream(tasksFile);
                ByteArrayOutputStream tasksBytes = new ByteArrayOutputStream()
        ) {
            for (Task task : tasks) {
                task.serialize().writeObject(tasksBytes);
            }

            Serializer serializer = new Serializer();
            serializer.assignType(STATE_KEY);
            serializer.putInt(tasks.size());
            serializer.putBytes(tasksBytes.toByteArray());
            serializer.writeObject(outputStream);
        }
    }
}
