package main.java.duke.task;

// util.Task object implementation
public class Task {
    private boolean isMarked = false;
    private String name;

    // constructor
    public Task(String name) {
        this.name = name;
    }

    //setters and getters
    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // String representation of util.Task
    @Override
    public String toString() {
        String taskCheckbox = this.isMarked ? "[X]" : "[ ]";
        return taskCheckbox + " " + this.name;
    }

    // String representation for storing in file
    public String toStringFile() {
        String taskChecked = this.isMarked ? "1" : "0";
        return taskChecked + "|" + this.name;
    }
}
