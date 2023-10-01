package command;

import task.TaskList;
import exception.FrankException;

public class MarkCommand extends Command {

    public MarkCommand(String command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks) throws FrankException {
        int index;
        try {
            index = Integer.parseInt(commands[1]);
            // This is the user index starting from 1
            index--;
            if(index < 0 || index + 1 > tasks.getTotalTasks()) {
                throw new FrankException("Brough it is out of index!");
            }
            if(tasks.getTask(index).getIsDone()) {
                throw new FrankException("Brough it is already marked!");
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new FrankException("Brough there is no task to mark!" );
        } catch (NumberFormatException e) {
            throw new FrankException("Brough please put the number index in the second word. ");
        }
        tasks.markTask(index,true);
    }
}
