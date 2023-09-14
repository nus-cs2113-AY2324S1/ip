package listWhisper.task;

import listWhisper.task.Task;
import listWhisper.task.TaskType;

class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.T);
    }
}
