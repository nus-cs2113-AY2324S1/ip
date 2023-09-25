package Commands;

import Exceptions.KenMissingTaskException;
import Tasks.TaskList;
import UI.Ui;

public class Update extends Command {
    private final int taskNumber;
    private final boolean isDone;

    public Update(int taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }


    @Override
    public void run(TaskList list) throws KenMissingTaskException{
        try {
            list.updateStatus(taskNumber - 1, isDone);

            String text;
            text = isDone ? "Barbie-tastic! You've completed this task with glamour!"
                    : "Back to the runway, darling! This task needs more Barbie magic!";
            Ui.printTexts(new String[]{
                    text,
                    list.getTask(taskNumber - 1).toString()
            });
        } catch (KenMissingTaskException e) {
            throw new KenMissingTaskException();
        }
    }
}
