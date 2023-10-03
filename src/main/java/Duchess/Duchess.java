package Duchess;

import Duchess.ErrorObjects.DuchessError;
import Duchess.FunctionObjects.CommandHandler;
import Duchess.FunctionObjects.UI;
import Duchess.TextObjects.Constants;

/** Main class to run. 
 * Follows - A-CodingStandard
*/
public class Duchess {




    private UI ui;
    private CommandHandler commandHandler;

    
    /** Main method that instantiates CommandHandler object and take
     * input from the user to echo.
     * @param filepath contains the filepath for data saving.
     */
    public Duchess(String filepath){
        this.ui = new UI();
        this.commandHandler = new CommandHandler(ui, filepath);
    }
    
    private void run(){
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

    /**
     * Main method to run the program via the run function.
     * @param args
     */
    public static void main(String[] args) {

        new Duchess(Constants.taskFilePath).run();

    }

}

