package Duchess;
import java.util.Scanner;

import Duchess.ErrorObjects.DuchessError;
import Duchess.FunctionObjects.CommandHandler;
import Duchess.FunctionObjects.UI;
import Duchess.TextObjects.Constants;
import Duchess.TextObjects.DefaultStrings;

/** Main class to run. 
 * Follows - A-CodingStandard
*/
public class Duchess {


    /** Main method that instantiates CommandHandler object and take
     * input from the user to echo.
     */

    private UI ui;
    private CommandHandler commandHandler;

    

    public Duchess(String filepath){
        this.ui = new UI();
        this.commandHandler = new CommandHandler(ui, filepath);
    }
    
    public void run(){
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.getCommand();
                isExit = commandHandler.ParseCommand(command);
            } catch (DuchessError e) {
                ui.printError(e);
            } finally {
                ui.printLine();
            }
        }
    }
    public static void main(String[] args) {

        new Duchess(Constants.taskFilePath).run();

    }

}

