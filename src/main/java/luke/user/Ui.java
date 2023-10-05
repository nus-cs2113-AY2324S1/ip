package luke.user;

import java.util.Scanner;

public class Ui {
    //directly interact with user (read in (to Parser)/print)
    public String echo;
    public Scanner userInput;
    public Ui () {
        userInput = new Scanner(System.in);
    }

    //decompiler???
    public void closeUi() {
        userInput.close();
    }

    public void showWelcome() {
        String logo = "\t _           _        \n"
                + "\t| |    _   _| | _____ \n"
                + "\t| |   | | | | |/ / _ \\\n"
                + "\t| |___| |_| |   <  __/\n"
                + "\t|_____|\\__,_|_|\\_\\___|\n";

        System.out.println("\t" + "Hello! I'm\n" + logo);
        System.out.println("\tWhat can I do for you?");
    }

    public void showLine() {
        // show the divider line ("_______")
        System.out.println("\t____________________________________________________________");
    }
    public void showLoadingError() {
        System.out.println("Loading Error... (pls change)");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public String readCommand() {
        echo = userInput.nextLine();
        return echo;
    }

}
