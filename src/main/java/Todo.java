public class Todo extends Task{
    public Todo(String description, String taskType) {
        super(description, taskType);
    }

    public String toString() {
        return taskType + super.toString();
    }
}
