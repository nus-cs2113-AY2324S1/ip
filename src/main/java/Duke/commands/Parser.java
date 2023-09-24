package duke.commands;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the process of making sense of information from string array elements
 * of classes.
 */
public class Parser {

    public Parser(){

    }

    /**
     * Converts string array of task type to string to be printed.
     *
     * @param task task with task type to be processed.
     * @return task type in string data type.
     */
    public static String parseTaskType(Task task){
        String taskType = Arrays.toString(task.taskType);
        return taskType;

    }

    /**
     * Converts string array of whether a task is done to string to be printed.
     *
     * @param task task with marked or unmarked status to be processed.
     * @return marker of whether a task is done in string type to be printed.
     */
    public static String parseMarkAsDone(Task task){
        String marker = Arrays.toString(task.markAsDone);
        return marker;

    }

    public static void parseAndPrintTasks(Task task){
        if (task.taskType[0] == "T") {
            System.out.print( "." + Parser.parseTaskType(task) +
                    Parser.parseMarkAsDone(task) + " "
                    + task.toBeDone);
        }
        else if(task.taskType[0] == "D"){
            System.out.print("." + Parser.parseTaskType(task) +
                    Parser.parseMarkAsDone(task)
                    + " " + task.toBeDone
                    + " (by: " + task.dueDate + ")");
        }
        else if (task.taskType[0] == "E"){
            System.out.print("." + Parser.parseTaskType(task) +
                    Parser.parseMarkAsDone(task) + " " +
                    task.toBeDone
                    + " (from: " + task.startTime
                    + " to: "+ task.endTime + ")");
        }
    }
}
