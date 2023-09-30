package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;
    private ArrayList<Task> tasks;
    public Storage() {
        this.path = "data/duke.txt";
    }

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> loadData(){
        try {
            tasks = new ArrayList<Task>();
            File f = new File(path);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String input = s.nextLine();
                readDataLine(input);
            }
        } catch (IOException e) {
            System.out.println("An error occurred when accessing the file.");
        }
        return tasks;
    }

    public void readDataLine(String input) {
        String[] parts = input.split(" \\| ");
        try {
            Task task;

            switch(parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new CorruptedFileException();
            }

            int binaryIsDone = Integer.parseInt(parts[1]);
            task.setDone(binaryIsDone);
            tasks.add(task);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | CorruptedFileException e) {
            System.out.println("Failed to read line, the file is corrupted.");
        }
    }

    public void saveData(ArrayList<Task> tasks) {
        try {
            this.tasks = tasks;
            File f = new File(path);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter(path);
            for (Task task : tasks) {
                fw.write(task.toFileString()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when accessing the file.");
        }
    }

}
