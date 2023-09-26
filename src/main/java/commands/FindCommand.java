package commands;

import ascii.AsciiArt;
import main.ResponseProcessor;
import task.Task;
/**
 * Represents a find Command to search for a string in the task list
 */
public class FindCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        if (statement.isEmpty()){
            throw new IllegalArgumentException ("write something to find masta! " + AsciiArt.getArt("cry"));
        }
        if (processor.taskList.isEmpty()){
            throw new IllegalArgumentException ("uwu masta task list is empty " + AsciiArt.getArt("cry"));
        }
        int num = 1;
        for (Task task : processor.taskList) {
            String temp = (task.getStatus()).toLowerCase();
            if(temp.contains(statement.toLowerCase())){
                if(num == 1){
                    System.out.println("Masta! here are the matching tasks in your list:");
                }
                System.out.println(num + ". " + task.getStatus());
                num += 1;
            }
        }
        if(num == 1){
            System.out.println("Sorry masta there is no matching task " + AsciiArt.getArt("sad"));
        }
    }
}
