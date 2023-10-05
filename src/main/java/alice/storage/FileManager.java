package alice.storage;

import alice.parser.DateTimeParser;
import alice.tasks.*;
import alice.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private File file;
    private String fileName;
    private String dataDirectory;
    private String filePath;

    public FileManager() {
        this.dataDirectory = "./data/";
        this.fileName = "alice.txt";
        this.filePath = this.dataDirectory + this.fileName;

        this.file = new File(filePath);
        File fileDirectory = new File(dataDirectory);

        try {
            if (!fileDirectory.isDirectory()) {
                fileDirectory.mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("    Oh no alice.Alice, there was an ERROR in opening the file...");
        }
    }

    public void save(TaskList tasks) {
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
        ArrayList<Task> tasks = new ArrayList<>();

        Task task = null;
        Scanner scanner = null;

        try{
            scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                task = decode(scanner.nextLine());
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            Ui.printOneTabMessage("Your file can't be not found :(");
            Ui.printLineDivider();
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
            LocalDateTime deadline = LocalDateTime.parse(inputArray[3], DateTimeParser.getFormatter());
            task = new Deadline(description, deadline);
            break;
        case "Event":
            LocalDateTime start = LocalDateTime.parse(inputArray[3], DateTimeParser.getFormatter());
            LocalDateTime end = LocalDateTime.parse(inputArray[4], DateTimeParser.getFormatter());
            task = new Event(description, start, end);
            break;
        default:
            break;
        }
        if (isMarked) {
            task.setIsDone(true);
        }

        return task;
    }
}