package notGPT.commands;    

import java.util.Scanner;

public class UserInput {
    public static String[] getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput.split(" ");
    }
}
