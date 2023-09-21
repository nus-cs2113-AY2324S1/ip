package duke;

public class DukeException extends Exception{
    protected String errorMessage;
    protected static String LINE_DIVIDER = "____________________________________________________________";
    public DukeException(String errorMessage){
        this.errorMessage = errorMessage;
    }
    @Override
    public String toString(){
        return String.format("%s", errorMessage);
    }
}
