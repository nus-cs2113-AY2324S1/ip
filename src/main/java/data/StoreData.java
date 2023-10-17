package data;
import main.Parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Represents a store data function to store the task list to a savefile.txt
 */
public class StoreData {
    public static void store(Parser processor) {
        String directory = System.getProperty("user.dir");
        Path path = Paths.get(directory + "/savefile.txt");
        File file = path.toFile();
        /*
          Loop through the txt file and write the data line by line
         */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int num = 0; num < processor.taskList.size(); num += 1){
                String type = processor.taskList.get(num).getType();
                String done = processor.taskList.get(num).isCompleted()? "1" :  "0";
                String description = processor.taskList.get(num).getDescription();
                String excess = processor.taskList.get(num).getExcess();
                writer.write(type + " | " + done + " | " + description + " | " + excess);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error when storing data uwu, " + e.getMessage());
        }
    }
}