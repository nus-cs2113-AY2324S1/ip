/**
 * The Command class represents a Command and its execution.
 */
package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;

public class Command {

    String input;

    /**
     * initializes the Command class
     * @param input input of the user
     */
    public Command (String input){
        this.input = input;
    }

    /**
     * executes the command
     * @param tasks passes the current task list
     * @param ui passes the ui
     * @param storage passes the storage
     */
    public void execute (TaskList tasks, Ui ui, Storage storage){

    }
}
