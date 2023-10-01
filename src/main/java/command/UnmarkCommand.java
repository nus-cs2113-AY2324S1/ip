package command;

import task.TaskList;
import exception.FrankException;
import utility.Ui;

public class UnmarkCommand extends Command{
    public UnmarkCommand(String command) {
        super(command);
    }

     /**
     * Unmarks a task of a certain index.
     * Index is fed from same line i.e. unmark 1 (1 indexed)
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
            if(!tasks.getTask(index).getIsDone()) {
                throw new FrankException("Brough it is already unmarked!");
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new FrankException("Brough there is no task to unmark!" );
        } catch (NumberFormatException e) {
            throw new FrankException("Brough please put the number index in the second word. ");
        }
        tasks.markTask(index,false);
    }
}
