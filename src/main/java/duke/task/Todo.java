package main.java.duke.task;

public class Todo extends Task{
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    // String representation for storing in file
    @Override
    public String toStringFile() {
        return "T|" + super.toStringFile();
    }
}
