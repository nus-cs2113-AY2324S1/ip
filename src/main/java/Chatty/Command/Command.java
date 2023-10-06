/**
 * The Command class represents a Command and its execution. It is an abstract type
 */
package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;

public abstract class Command {

    String input;

    public Command (String input){
        this.input = input;
    }
    public void execute (TaskList tasks, Ui ui, Storage storage){

    }
}
