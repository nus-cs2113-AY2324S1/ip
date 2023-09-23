package duke.commands;

import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import static duke.commands.Ui.newList;

/**
 * Represents a class to handle management of tasks to load
 * into the duke.txt file.
 */
public class Storage {

    /**
     * Loads task information from hard drive into task-list.
     * Enables user to save previously inputted tasks.
     *
     *
     * @param filePath relative path of file duke.txt.
     * @throws FileNotFoundException If file is not found.
     */
    public static void loadFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner reader = new Scanner(f);


        while(reader.hasNext()){
            String data = reader.nextLine();

           String dataInArray = data;
           String[] spliced = dataInArray.split("/");

            String taskType = spliced[0];
            String marker = spliced[1];
            String taskName = spliced[2];
            String timing = spliced[3];
            String timingEnd = spliced[4];

            String taskNameUsed = taskName.substring(0,taskName.length());

            Task newTask = new Task(taskName);
            newTask.toBeDone = taskNameUsed;

            if(Objects.equals(taskType, "[T]")){
                newTask.taskType[0] ="T";
            }else if(Objects.equals(taskType, "[D]")) {
                newTask.taskType[0] = "D";
                newTask.dueDate = timing.substring(5, timing.length());
            }else if(Objects.equals(taskType, "[E]")) {
                newTask.taskType[0] = "E";
                newTask.startTime = timing.substring(8,timing.length());
                newTask.endTime = timingEnd.substring(6,timingEnd.length());
            }

            if(Objects.equals(marker, "[ ]")){
                newTask.markAsDone[0] = " ";
            } else if (Objects.equals(marker, "[X]")) {
                newTask.markAsDone[0] = "X";

            }
            newList.taskList.add(newTask);
        }
    }
}
