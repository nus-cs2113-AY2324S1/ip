package elvis.task;

import java.util.Scanner;

//Task Class that stores the description and isDone of task
public class Task {

    protected String description;
    protected boolean isDone;

    //Constructor of the Task Class
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Getting the To-Do attribute of the task instance
    public String getDescription() {
        return this.description;
    }

    //Getting the isDone attribute of the task instance
    public String getStatus() {
        return (this.isDone ? "X" : " ");    // mark done task with X
    }

    //Setting the status of the isDone
    public void setStatus(boolean truthValue) {
        this.isDone = truthValue;
    }

    //Getting the taskType attribute of ToDo, Deadline, Event instantiations (Polymorphism)
    public char getTaskType() {
        return '?';
    }

    public String getDate() {
        return "?";
    }

    public String getStartTime() {
        return "?";
    }

    public String getEndTime() {
        return "?";
    }
}