package Duchess;
import java.util.Scanner;

/** Main class to run for increment 1. */
public class Duchess {


    /** Main method that instantiates CommandHandler object and take
     * input from the user to echo.
     */
    public static void main(String[] args) {

        System.out.println(DefaultStrings.logo);
        CommandHandler ActiveCommandHandler = new CommandHandler();
        Scanner sc = new Scanner(System.in);
        int endProgram = Constants.stayFlag;

        while (endProgram == Constants.stayFlag) {
            String command = sc.nextLine();
            endProgram = ActiveCommandHandler.ParseCommand(command);
        }

        sc.close();


    }

}

