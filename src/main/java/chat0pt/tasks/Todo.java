package chat0pt.tasks;

public class Todo extends Task {
    /**
     * Constructor for Todo
     * @param addtask Name of task to add
     */
    public Todo(String addtask) {
        super(addtask);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        return "T#" + super.toFile();
    }
}
