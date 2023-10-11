package command;

import task.TaskList;
import exception.FrankException;
import utility.Ui;

public class DeleteCommand extends Command{
    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Deletes a task from the TaskList.
     * Index is fed from the same line i.e. Delete 1 (1 indexed)
     *
     * @param tasks TaskList of current Tasks
     * @param ui Current User Interface
     * @throws FrankException Unique Exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException {
        int index;
        try {
            index = Integer.parseInt(commands[1]);
            // This is the user index starting from 1
            index--;
            if(index < 0 || index + 1 > tasks.getTotalTasks()) {
                throw new FrankException("Brough it is out of index!");
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new FrankException("Brough there is no task to mark!" );
        } catch (NumberFormatException e) {
            throw new FrankException("Brough please put the number index in the second word. ");
        }

        tasks.deleteTask(index);
    }
}
