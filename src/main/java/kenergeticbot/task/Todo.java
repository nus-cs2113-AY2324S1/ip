package kenergeticbot.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.taskType = "[T]";
    }
    public Todo(String description, boolean isDone) {
        super(description);
        this.taskType = "[T]";
        this.isDone = isDone;
    }

    public String toString() {
        return taskType + super.toString();
    }
    public String printTaskToSave() {
        String task = taskType.replace("[", "").replace("]","");
        return task + super.printTaskToSave() + "\n";
    }
}
