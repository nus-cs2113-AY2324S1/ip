package Commands;

import Ken.Task;
import Ken.TaskList;
import Ken.Ui;

public class Add extends Command {
    private final Task task;

    public Add(Task task) {
        this.task = task;
    }

    @Override
    public void run(TaskList list) {
        list.addTask(task);
        Ui.printTexts(new String[] {
                "Barbie-approved! You've added this glamorous task:",
                task.toString(),
                "Now your list is sparkling with " + list.getSize() + " glamorous tasks, darling!"
        });
    }
}
