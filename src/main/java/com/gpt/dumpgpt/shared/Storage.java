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

    private Task readTask(Serializer serializer) throws DukeException, IOException {
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
        default:
            break;
        }
        return task;
    }

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
            if (!serializer.getType().equals(SERIALIZER_TYPE)) {
                throw new DukeException("Failed to restore old tasks...");
            }

            ArrayList<Task> tasks = new ArrayList<>();
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

            return tasks;
        }
    }

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
            for (Task task : tasks) {
                task.serialize().writeObject(tasksBytes);
            }

            Serializer serializer = new Serializer();
            serializer.setType(SERIALIZER_TYPE);
            serializer.putInt(tasks.size());
            serializer.putBytes(tasksBytes.toByteArray());
            serializer.writeObject(outputStream);
        }
    }
}
