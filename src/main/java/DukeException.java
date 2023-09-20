public class DukeException extends Exception{
    protected String LINE = "_______________________________________________";
    public DukeException(String message){
        super(message);
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}
