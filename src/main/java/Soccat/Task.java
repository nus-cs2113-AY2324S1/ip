package Soccat;

/**
 * Represents a task that is able to represent the
 * type of task and has the status of done or undone.
 * */

public class Task {

    public static final String SPLIT_CHAR = "|";
    public static final String DONE_CHAR = "1";
    public static final String NOT_DONE_CHAR = "0";
    public static final int TYPE_IDX = 0;
    public static final int ISDONE_IDX = 1;
    public static final int NAME_IDX = 2;
    private String name;
    private Boolean isDone;
    private static int taskCount = 0;
    public Task(String name){
        this.name = name;
        this.isDone = false;
        taskCount++;
    }

    public String getName() {
        return name;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return ((isDone) ? "[X] " : "[ ] ") + name;
    }
}