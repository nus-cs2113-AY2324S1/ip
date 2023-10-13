package task;

/**
 * Represents a todo task
 */
class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.todo);
    }

}
