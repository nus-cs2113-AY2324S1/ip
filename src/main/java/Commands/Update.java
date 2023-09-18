package Commands;

import Exceptions.KenMissingTaskException;
import Ken.TaskList;
import Ken.Ui;

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
            list.updateStatus(taskNumber, isDone);

            String text;
            if (isDone)  {
                text = "Barbie-tastic! You've completed this task with glamour!";
            } else {
                text = "Back to the runway, darling! This task needs more Barbie magic!";
            }
            Ui.printTexts(new String[]{
                    text,
                    list.getTask(taskNumber).toString()
            });
        } catch (KenMissingTaskException e) {
            throw new KenMissingTaskException();
        }
    }
}
