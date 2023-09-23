import neo.task.Deadline;
import neo.task.Event;
import neo.task.Task;
import neo.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final String fileDirectory;

    public Storage(String filePath, String fileDirectory) {
        this.filePath = filePath;
        this.fileDirectory = fileDirectory;
    }
    private boolean checkMarked(int mark) {
        return (mark == 1);
    }

    public void findFile(ArrayList<Task> list) {
        try {
            generateFile(filePath, fileDirectory, list);
        } catch (IOException e) {
            System.out.println("Error with data.txt file");
        }
    }

    private void generateFile(String filePath, String fileDirectory, ArrayList<Task> list) throws IOException {
        File directory = new File(fileDirectory);

        if (directory.mkdir()) {
            System.out.println("Creating new data folder...");
        }
        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("Creating new data.txt file...");
        }

        Scanner s = new Scanner(f);
        readFile(list, s);
    }

    private void readFile(ArrayList<Task> list, Scanner s) {
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] task = line.split(" / ");
            String taskType = task[0];
            int mark = Integer.parseInt(task[1]);
            boolean isMarked = checkMarked(mark);

            switch (taskType) {
            case "T":
                Todo todo = new Todo(task[2]);
                todo.setDone(isMarked);
                list.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(task[2], task[3]);
                deadline.setDone(isMarked);
                list.add(deadline);
                break;
            case "E":
                Event event = new Event(task[2], task[3], task[4]);
                event.setDone(isMarked);
                list.add(event);
                break;
            }
        }
    }
    public void updateFile(ArrayList<Task> list) {
        try {
            writeToFile(filePath, list);
        } catch (IOException e) {
            System.out.println("Error with data.txt file.");
        }
    }
    private void writeToFile(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task task : list) {
            String formatTask = task.formatTask();
            fw.write(formatTask + System.lineSeparator());
        }

        fw.close();
    }
}
