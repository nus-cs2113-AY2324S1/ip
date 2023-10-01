package jarvis.tasklist;

import jarvis.tasks.Task;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;
import jarvis.tasks.Todo;
import jarvis.exception.JarvisException;

import java.util.ArrayList;

/*
TODO: migrate taskmanager here, some parts of userInputHandler but that'll go to parser
 returns a tasklist so that storage can store the same arraylist
 */

/**
 * Stores the list of tasks registered on the ChatBot
 * list of tasks is temporary and will be disposed upon program termination
 */

public class TaskList {
    private ArrayList<Task> taskList; // stores all inputs

    public void addToTaskList(String input, Task.TaskType taskType, boolean displayMessage){
        switch(taskType){
        case TODO:
            return;
        case DEADLINE:
            return;
        case EVENT:
            return;
        }
    }
}
