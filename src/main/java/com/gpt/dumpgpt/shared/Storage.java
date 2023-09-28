package com.gpt.dumpgpt.shared;

import com.gpt.dumpgpt.task.Deadline;
import com.gpt.dumpgpt.task.Event;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.Todo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final static String SERIALIZER_TYPE = "Tasks";
    private final String TASKS_FILE;

    public Storage(String tasksFile) {
        TASKS_FILE = tasksFile;
    }

    /**
     * Reads a single task from file {@link Serializer} instance
     *
     * @param serializer {@link Serializer} instance to read task from
     * @return A task that was read from {@code serializer}
     * @throws DukeException when deserialization fails
     * @throws IOException   when read to underlying stream for deserialization fails
     */
    private Task readTask(Serializer serializer) throws DukeException, IOException {
        String type = serializer.getType();
        Task task = null;
        switch (type) {
        case "Todo":
            task = new Todo(null);
            task.deserialize(serializer);
            break;
        case "Deadline":
            task = new Deadline(null, null);
            task.deserialize(serializer);
            break;
        case "Event":
            task = new Event(null, null, null);
            task.deserialize(serializer);
            break;
        default:
            break;
        }
        return task;
    }

    /**
     * Restores tasks from specified {@link #TASKS_FILE}
     *
     * @return List of tasks read from {@link #TASKS_FILE}
     * @throws DukeException when deserialization fails
     * @throws IOException   when read to underlying stream for deserialization fails
     */
    public ArrayList<Task> restoreTasks() throws DukeException, IOException {
        File tasksFile = new File(TASKS_FILE);
        if (!tasksFile.exists()) {
            return null;
        }

        if (!tasksFile.canRead()) {
            throw new DukeException(TASKS_FILE + " is not readable...");
        }

        try (FileInputStream inputStream = new FileInputStream(tasksFile)) {
            Serializer serializer = new Serializer(inputStream);
            serializer.readObjectInfo();
            serializer.assertType(SERIALIZER_TYPE);

            ArrayList<Task> tasks = new ArrayList<>();
            int totalTasks = serializer.readInt();

            for (int i = 0; i < totalTasks; ++i) {
                Serializer taskSerializer = serializer.readSerializable();
                Task task = readTask(taskSerializer);
                if (task == null) {
                    throw new DukeException("Failed to restore old tasks...");
                }
                tasks.add(task);
            }

            return tasks;
        }
    }

    /**
     * Saves tasks to file specified in {@link #TASKS_FILE}
     *
     * @param tasks List of tasks to be saved
     * @throws DukeException when serialization fails
     * @throws IOException   when write to underlying stream for serialization fails
     */
    public void saveTasks(ArrayList<Task> tasks) throws DukeException, IOException {
        File tasksFile = new File(TASKS_FILE);
        tasksFile.getParentFile().mkdirs();
        tasksFile.createNewFile();
        if (!tasksFile.canWrite()) {
            throw new DukeException(TASKS_FILE + " is not writable...");
        }

        try (
                FileOutputStream outputStream = new FileOutputStream(tasksFile);
                ByteArrayOutputStream tasksBytes = new ByteArrayOutputStream()
        ) {
            Serializer serializer = new Serializer();
            serializer.setType(SERIALIZER_TYPE);
            serializer.putInt(tasks.size());

            for (Task task : tasks) {
                serializer.putSerializable(task);
            }

            serializer.writeObject(outputStream);
        }
    }
}
