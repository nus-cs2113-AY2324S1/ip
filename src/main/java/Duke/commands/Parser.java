package duke.commands;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    public Parser(){

    }

    public static String parseTaskType(Task task){
        String taskType = Arrays.toString(task.taskType);
        return taskType;

    }

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
