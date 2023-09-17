public class DukeException extends Exception{
    protected String line = "_______________________________________________";
    public DukeException(String message){
        super(message);
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
}
