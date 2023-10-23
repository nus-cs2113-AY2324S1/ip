package chatbot;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import exception.FattyException;
import tasks.Task;
import tasks.ToDo;
import tasks.Event;
import tasks.Deadline;

/**
 * This is the storage class.
 * It handles the storing of data between sessions.
 */

public class Storage {
    private File file;

    public Storage() {
        this.file = new File("data.txt");
    }

    /**
     * Method to save taskList information to file
     * Called every time there is an update to the taskList
     */
    public void save() {
        try (FileWriter fileWriter = new FileWriter("data.txt")) {
            for (int i = 0; i < Duke.taskList.size(); i++) {
                Task task = Duke.taskList.get(i);
                String description = task.getDescription();
                String isDone = task.getDone();

                if (task instanceof ToDo) {
                    fileWriter.write(isDone + "|" + description + "\n");
                    continue;
                }

                String time = task.getTime();
                fileWriter.write(isDone + "|" + description + "|" + time + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    /**
     * Loads information from the save file to the program
     * Called on program startup
     * Creates new file if the save file does not exist
     * Throws format exceptions if the save file has been corrupted
     * @throws IOException
     * @throws FattyException
     */
    public void load() throws IOException, FattyException {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("File creation failed");
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String [] words = line.split("\\|");

                    for(int i = 0; i < words.length; i++) {
                        words[i] = words[i].trim();
                    }

                    boolean isDone = words[0].equals("true");

                    if (words.length == 2) {
                        Duke.taskList.add(new ToDo(words[1], isDone));
                    } else if (words.length == 3) {
                        String [] args = {words[1], words[2]};
                        Duke.taskList.add(new Deadline(args, isDone));
                    } else if(words.length == 4) {
                        String [] args = {words[1], words[2], words[3]};
                        Duke.taskList.add(new Event(args, isDone));
                    } else {
                        throw new FattyException("Save file corrupted.");
                    }
                }
            }
        }
    }
}
