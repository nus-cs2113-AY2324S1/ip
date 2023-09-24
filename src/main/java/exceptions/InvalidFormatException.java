package exceptions;

public class InvalidFormatException extends Exception{
    public InvalidFormatException() {
        System.out.println("    The Knave of Hearts noticed that your FORMATTING IS WRONG!!!\n");
        System.out.println("    Correct it before he attacks us!\n");
    }
}