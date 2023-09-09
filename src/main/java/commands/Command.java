package commands;
import main.ResponseProcessor;

public abstract class Command {
    public abstract void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException;

    public boolean isValidTask(String response){
        if ("".equalsIgnoreCase(response)){
            throw new IllegalArgumentException ("uwu master please put in the task name!");
        }
        return true;
    }

    public int parseInt(String value, ResponseProcessor processor){
        try {
            int num = Integer.parseInt(value);
            if ((0 < num) && (num <= processor.taskList.size())){
                return num;
            }
            throw new IllegalArgumentException ("That is not a valid number masta! (.>ෆ<.)");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("Please put in a number masta! (.>ෆ<.)");
        }
    }
}
