package ui;

import common.Messages;
import listWhisper.exceptions.DescriptionFormatException;

import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.in;

public class Ui {
    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(in);
    }

    public String getUserCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        Messages.printGreetingMessage();
    }

    public void showLine() {
        Messages.printStraightLine();
    }

    public static void showError(Exception e) {
        System.out.println(e);
    }
}
