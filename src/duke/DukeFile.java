package duke;

import duke.deadline.Deadline;
import duke.event.Event;
import duke.task.Task;
import duke.todo.Todo;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class DukeFile {
    protected String fileName;
    protected int taskCount = 0;

    public DukeFile() {
        this.fileName = "./data/duke.txt";
    }
    public DukeFile(String fileName) {
        this.fileName = fileName;
    }

    protected Task createTaskFromFile(String line) {
        String[] details = line.split("[|]");
        for (int i = 0; i < details.length; i++) {
            details[i] = details[i].trim();
        }

        Task task;
        char todoType = details[0].charAt(0);
        boolean isDone = Boolean.parseBoolean(details[1]);

        switch(todoType) {
            case 'T':
                task = new Todo(details[2]);
                break;
            case 'E':
                task = new Event(details[2], details[3], details[4]);
                break;
            case 'D':
                task = new Deadline(details[2], details[3]);
                break;
            default:
                task = new Task("");
        }

        task.setIsDone(isDone);

        return task;
    }

    public Task[] readTasksFromFile() throws IOException {
        File taskFile = new File(fileName);
        Task[] taskList = new Task[100];
        Scanner s = new Scanner(taskFile);
        String line;

        taskCount = 0;

        while (s.hasNext()) {
            line = s.nextLine();

            Task task = createTaskFromFile(line);
            taskList[taskCount] = task;
            taskCount++;
        }

        return taskList;
    }

    public void writeTasksToFile(String tasks) throws IOException {
        FileWriter fw = new FileWriter(fileName);

        fw.write(tasks);
        fw.close();
    }

}
