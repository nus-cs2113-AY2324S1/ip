package data;

import main.ResponseProcessor;
import task.Deadline;
import task.Event;
import task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadData {
    public static void load(ResponseProcessor processor) {

        String directory = System.getProperty("user.dir");
        Path path = Paths.get(directory + "/savefile.txt");
        File file = path.toFile();
        if (!file.exists()){
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                String[] sections = line.split(" \\| ");
                boolean isComplete = sections[1].equals("1");
                int index = processor.taskList.size();
                if(sections[0].equalsIgnoreCase("T")) {
                    processor.taskList.add(new Todo(sections[2]));
                    processor.taskList.get(index).setCompleted(isComplete);
                } else if(sections[0].equalsIgnoreCase("D")) {
                    processor.taskList.add(new Deadline(sections[2], sections[3]));
                    processor.taskList.get(index).setCompleted(isComplete);
                } else if(sections[0].equalsIgnoreCase("E")) {
                    processor.taskList.add(new Event(sections[2], sections[3], sections[4]));
                    processor.taskList.get(index).setCompleted(isComplete);
                } else {
                    throw new IOException("incorrect file formatting");
                }
                line = reader.readLine();
            }
        } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("memory file corrupted :(");
            System.out.println(e.getMessage());
        }
    }
}
