package duke;

import java.util.Scanner;

public class Ui {
    protected static String CHATBOTNAME = "Andrew Tate";
    protected static String LINE_DIVIDER = "____________________________________________________________";
    protected Scanner myScanner;

    public Ui(){
        this.myScanner = new Scanner(System.in);
    }

    public void showWelcomeMessage(){
        showLine();
        System.out.println("Hello! I'm the top G " + CHATBOTNAME);
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLoadingError(String filePath){
        System.out.println("Unable to create file @ " + filePath);
    }

    public void showExitMessage(){
        System.out.println("Bye, hope to see you again soon!");
        showLine();
    }

    public void showError(Exception e){
        System.out.println(e);
    }

    public String readCommand(){
        System.out.print("> ");
        return myScanner.nextLine();
    }

    public void showLine(){
        System.out.println(LINE_DIVIDER);
    }
}
