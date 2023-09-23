package Task;

import java.time.format.DateTimeFormatter;

public class Task {
    
    private String name;
    private boolean isCompleted;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + name;
    }

}
