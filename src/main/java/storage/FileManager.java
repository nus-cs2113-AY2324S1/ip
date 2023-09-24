package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private final String filePath = "C:\\Users\\chark\\Downloads\\CS2113\\ip\\src\\main\\java\\data\\alice.txt";
    private File file;

    public FileManager() {
        try {
            this.file = new File(filePath);
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("    Oh no Alice, there was an ERROR in opening the file...");
        }
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            int noOfTasks = tasks.size();
            for (int i=0; i < noOfTasks; i++) {
                fw.write(tasks.get(i).encode() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("    Something went wrong!");
        }
    }

    public ArrayList<Task> retrieve() throws FileNotFoundException {
        ArrayList<Task> tasks= new ArrayList<>();
        Task task = null;
        Scanner scanner = null;

        try{
            scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                task = decode(scanner.nextLine());
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("    Your file can't be not found :(");
        }

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