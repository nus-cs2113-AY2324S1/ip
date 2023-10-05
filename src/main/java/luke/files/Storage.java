package luke.files;

import luke.tasks.Task;
import luke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks;

    public Storage(String filePath) {


        File taskListFile = new File("./src/main/java/luke/files/memory.txt");
        if (taskListFile.exists()) {
            try {
                tasks = Memory.readMemory("./src/main/java/luke/files/memory.txt");
                System.out.println("\n\tMemory retrieval successful!\n");
                //return tasks;
            } catch (FileNotFoundException e) {
                System.out.println("\tNo existing memory. (1)");
            }
        } else {
            try {
                taskListFile.createNewFile();
                System.out.println("\tNo existing memory.");
            } catch (IOException e) {
                System.out.println("\tIOException.");
            } catch (SecurityException e) {
                System.out.println("\tSecurityException.");
            }
        }
        //return null;

    }

    public ArrayList<Task> load() {
        return tasks;
    }

    public void store(TaskList tasksToStore) {
        //store in memory.txt

        Memory.storeMemory("./src/main/java/luke/files/memory.txt", tasksToStore.getMainTaskList());

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
