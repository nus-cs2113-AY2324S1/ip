package bob.todo;

import bob.task.Task;

public class Todo extends Task {

    /**
     * Constructs a new Todo.
     *
     * @param description contains description of Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }

}
