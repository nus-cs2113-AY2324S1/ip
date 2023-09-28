package notGPT.ui;

import java.util.Scanner;

public class Ui{
    private final Scanner input = new Scanner(System.in);  // Create a Scanner object

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void displayIntroMessage() {
        showLine();
        System.out.println(
        "Hello! I'm notChatGPT :)\n" 
        + "What can I do for you?");
        showLine();
    }

    public String getUserInput() {
        String userInput = input.nextLine();
        return userInput;
    }


}
