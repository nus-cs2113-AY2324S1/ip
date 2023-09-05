// Task object implementation
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

    // String representation of Task
    @Override
    public String toString() {
        String taskCheckbox = this.isMarked ? "[x]" : "[ ]";
        return taskCheckbox + " " + this.name;
    }
}
