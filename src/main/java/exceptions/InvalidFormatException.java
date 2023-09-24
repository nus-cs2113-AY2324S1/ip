package exceptions;

public class InvalidFormatException extends Exception{
    public InvalidFormatException() {
        System.out.println("The Knave of Hearts noticed that your FORMATTING IS WRONG!!!\nCorrect it before he attacks us!\n");
    }
}