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
