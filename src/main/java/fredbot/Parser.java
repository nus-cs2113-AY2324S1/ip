package fredbot;

import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.util.ArrayList;

public class Parser {
    public static TaskList parseFromFile(ArrayList<String> dataItems) {
       TaskList tasks = new TaskList();
       for (String line: dataItems) {
           switch (line.charAt(1)) {
           case 'T':
               tasks.addTask(new Todo(line.substring(6).trim()));
               tasks.getTask(Task.getNumTask()).setDone(line.charAt(4) == 'X');
               Task.setNumTask(Task.getNumTask()+1);
               break;
           case 'D':
               String[] argumentsDeadline = line.substring(6).split("by:");
               tasks.addTask(new Deadline(argumentsDeadline[0].trim(),argumentsDeadline[1].trim()));
               tasks.getTask(Task.getNumTask()).setDone(line.charAt(4) == 'X');
               Task.setNumTask(Task.getNumTask()+1);
               break;
           case 'E':
               String[] argumentsEvent = line.substring(6).split("to:|from:");
               tasks.addTask(new Event(argumentsEvent[0].trim(),
                       argumentsEvent[1].trim(), argumentsEvent[2].trim()));
               tasks.getTask(Task.getNumTask()).setDone(line.charAt(4) == 'X');
               Task.setNumTask(Task.getNumTask()+1);
               break;
           }
       }
       return tasks;
    }
}
