package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private final String filePath = "./src/main/java/data/alice.txt";
    private File file;
    private int noOfTasks;

    public FileManager() {
        try {
            this.file = new File(filePath);
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Oh no Alice, there was an ERROR in opening the file...");
        }
    }

    public void setNoOfTasks(int noOfTasks) {
        this.noOfTasks = noOfTasks;
    }

    public int getNoOfTasks() {
        return this.noOfTasks;
    }
    public void save(Task[] tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            int index = 0;
            while (tasks[index] != null) {
                fw.write(tasks[index].encode() + System.lineSeparator());
                index++;
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }

    public Task[] retrieve() throws FileNotFoundException {
        Task[] tasks = new Task[100];
        Task task = null;
        int pos = 0;

        Scanner s = new Scanner(this.file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            task = decode(s.nextLine());
            tasks[pos] = task;
            pos++;
        }

        setNoOfTasks(pos);
        return tasks;
    }

    public Task decode(String input) {
        Task task = null;

        String[] inputArray = input.split(" \\| ");
        String command = inputArray[0];
        String description = inputArray[2];
        boolean isMarked = inputArray[1].equals("1") ? true : false;

        switch (command) {
        case "Todo":
            task = new Todo(description);
            break;
        case "Deadline":
            String deadline = inputArray[3];
            task = new Deadline(description, deadline);
            break;
        case "Event":
            String start = inputArray[3];
            String end = inputArray[4];
            task = new Event(description, start, end);
            break;
        }
        if (isMarked) {
            task.setIsDone(true);
        }

        return task;
    }
}