package Duchess;
import java.util.Scanner;

import Duchess.ErrorObjects.DuchessError;
import Duchess.FunctionObjects.CommandHandler;
import Duchess.FunctionObjects.ErrorHandler;
import Duchess.TextObjects.Constants;
import Duchess.TextObjects.DefaultStrings;

/** Main class to run. 
 * Follows - A-CodingStandard
*/
public class Duchess {


    /** Main method that instantiates CommandHandler object and take
     * input from the user to echo.
     */
    
    public static void main(String[] args) {

        System.out.println(DefaultStrings.logo);
        CommandHandler ActiveCommandHandler = new CommandHandler();
        ErrorHandler ActiveErrorHandler = new ErrorHandler();
        Scanner sc = new Scanner(System.in);
        int endProgram = Constants.stayFlag;


        while (endProgram == Constants.stayFlag) {
            try {
                String command = sc.nextLine();
                endProgram = ActiveCommandHandler.ParseCommand(command);
            } catch (DuchessError e) {
                ActiveErrorHandler.HandleError(e);
            }
        }

        sc.close();


    }

}

