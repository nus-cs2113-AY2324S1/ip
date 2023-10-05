package lemon.file;

import lemon.exception.LemonException;
import lemon.task.Task;
import lemon.validation.Parser;

import static lemon.common.Messages.LINE_SEPARATOR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage() {
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws LemonException{
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            System.out.println("Loading data file...");

            Parser parser = new Parser();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parser.parseFile(line);
                tasks.add(task);
            }
            scanner.close();
            System.out.println("Data file loaded!\n");
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException f) {
                    throw new LemonException("Uh-oh! An error has occurred while creating file.\n");
                }
            }
            System.out.println("New file created!");
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            for (Task task : tasks) {
                fileWriter.write(task.toFile());
                fileWriter.write(LINE_SEPARATOR);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Uh-oh! An error has occurred while writing to file.\n");
        }
    }
}
