package duke;

public class InvalidCommandException extends Exception{
    public void printErrorMessage(){
        System.out.println("Oops, seems like I don't know this command. Please provide a valid command!");
    }
}
