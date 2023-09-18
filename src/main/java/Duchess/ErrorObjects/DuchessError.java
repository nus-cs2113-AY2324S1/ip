package Duchess.ErrorObjects;

import java.util.Scanner;


public class DuchessError extends Exception {

    private Scanner scanner;

    public DuchessError(String message) {
        super(message);
    }



    public void HandleError() {
        System.out.println("Error: " + this.getMessage());
    }

    public void setScanner(Scanner sc) {
        this.scanner = sc;
    }

    public String getNewString() {
        return scanner.nextLine();
    }


}
