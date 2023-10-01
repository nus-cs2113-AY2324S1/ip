package duke.exceptions;

public class InvalidTimeSpanException extends Exception{
    public void printErrorMessage(){
        System.out.println("The start time of an event cannot be after the end time!");
    }
}
