package Commands;

import Exceptions.KenMissingTaskException;
import Tasks.TaskList;
import UI.Ui;

public class Delete extends Command{
    private final int taskNumber;

    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void run(TaskList list) throws KenMissingTaskException {
        try {
            String taskText = list.getTask(taskNumber - 1).toString();
            list.deleteTask(taskNumber - 1);
            Ui.printTexts(new String[] {
                    "Noted, darling! I've given that task the boot, just like a fashion faux pas!",
                    taskText,
                    "Now your list is sparkling with " + list.getSize() + " glamorous tasks, darling!"
            });
        } catch (KenMissingTaskException e) {
            throw new KenMissingTaskException();
        }
    }
}
