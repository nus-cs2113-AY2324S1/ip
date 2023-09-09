package Commands;

import CSGPT.CSGPT;
import CSGPT.Task;
import CSGPT.TaskList;

public class Add extends Command{
    private final Task task;

    public Add(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList list) {
        list.add(task);
        String[] text = {"Added: " + task.getDescription(), "Now you have " + list.size() + " tasks in the list."};
        CSGPT.printMultipleText(text);
    }
}
