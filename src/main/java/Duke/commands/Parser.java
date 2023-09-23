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
}
