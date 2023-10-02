package duke;

import duke.ui.Ui;

import duke.storage.FileRW;

import duke.tasks.*;

import java.io.IOException;

import duke.commands.Command;

import duke.exception.DukeException;

import duke.parser.Parser;


public class Duke {

   
    

    public static void main(String[] args) throws DukeException {
        try {

            Tasklist tasks = new Tasklist();

            FileRW.readFromFile(tasks);
            
    
            Ui.showWelcome();
    
            Command nextCommand = new Command();
    
            while (!nextCommand.isExit()) {
                try {
                    //Read in the user input
                    String input = Ui.readCommand();
                    //Parse the user input
                    nextCommand = Parser.parse(input);
                    //Execute the command
                    nextCommand.execute(tasks);
                } catch (DukeException e) {
                    //Handle DukeException
                    Ui.showMessage(e.getMessage());
                } 
            
                
            }
    
            //Print out a goodbye message
            Ui.showGoodbye();
    
            //Write the tasks to the file
            FileRW.writeToFile(tasks);
        } catch (DukeException e) {
            //Handle DukeException
            Ui.showMessage(e.getMessage());
        }
    }

}