package task;

import duke.Duke;
import exception.DukeException;

import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(int taskNo, int taskCount, Task[] list) {
        if ( (taskNo > taskCount ) || (taskNo <1) ){
            System.out.println("Oops! You don't have any task in this positions.");
        } else if(this.isDone){
            System.out.println("You have already completed the task.");
        } else{
            this.isDone = true;
            System.out.println("  Nice! I've marked this task as done:\n"
                    + "  [X] " + list[taskNo - 1].description);
        }
    }

    public void setNotDone(int taskNo, int taskCount, Task[] list) {
        if ( (taskNo > taskCount ) || (taskNo <1) ){
            System.out.println("Oops! You don't have any task in this position.");
        } else if(!this.isDone){
            System.out.println("Oh, you haven't finished this yet.");
        } else{
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + "  [ ] " + list[taskNo - 1].description);
        }
    }

    //return a new list after delete the target object
    public static Task[] updatedTaskList(int indexOfDelete){
        Task[] newList = new  Task[100];
        int numOfCopy = Duke.getTaskList().length - indexOfDelete - 1;
        System.arraycopy(Duke.getTaskList(), 0, newList, 0, indexOfDelete);
        System.arraycopy(Duke.getTaskList(), indexOfDelete + 1, newList, indexOfDelete, numOfCopy);
        return newList;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
