package elvis.task;

import java.util.Scanner;

//Task Class that stores the description and isDone of task
public abstract class Task {

    protected String description;
    protected boolean isDone;

    //Constructor of the Task Class
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toFileString() {
        int status = isDone ? 1 : 0;
        if (this instanceof ToDo) {
            return String.format("%c|%d|%s", this.getTaskType(), status, description);
        } else if (this instanceof Deadline) {
            return String.format("%c|%d|%s/by%s", this.getTaskType(), status, description, getDate());
        } else if (this instanceof Event) {
            return String.format("%c|%d|%s/from%s/to%s", this.getTaskType(), status, description,
                    getStartTime(), getEndTime());
        }
        return "ERROR";
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