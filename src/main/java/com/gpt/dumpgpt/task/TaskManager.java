package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ApplicationState;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private final static String TASKS_FILE = "data/tasks.bin";
    private final static String STATE_KEY = "Tasks";
    private final ArrayList<Task> TASKS;

    @SuppressWarnings("unchecked")
    public TaskManager() {
        ApplicationState appState = ApplicationState.getAppState();
        Object tasks = appState.getStateObject(STATE_KEY);
        if (tasks == null) {
            tasks = new ArrayList<Task>();
            appState.setStateObject(STATE_KEY, tasks);
        }
        this.TASKS = (ArrayList<Task>) tasks;
    }

    public void addTask(Task task) {
        TASKS.add(task);
    }

    public boolean deleteTask(Task task) {
        return TASKS.remove(task);
    }

    public Task getTask(int pos) {
        if (pos < 0 || pos >= TASKS.size()) {
            return null;
        }
        return TASKS.get(pos);
    }

    public Task getTask(Command command) {
        int taskNumber = ProgramConstants.parsePositiveNumber(command.getArguments());
        return getTask(--taskNumber);
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
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
        }
        return task;
    }

    public void restoreTasks() throws DukeException, IOException {
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

    public void saveTasks() throws DukeException, IOException {
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
            serializer.setType(STATE_KEY);
            serializer.putInt(tasks.size());
            serializer.putBytes(tasksBytes.toByteArray());
            serializer.writeObject(outputStream);
        }
    }
}
