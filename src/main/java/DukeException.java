public class DukeException extends Exception{

    private String errMsg;
    static String DIVIDER = "------------------------------------------------------------";

    public DukeException(String errMsg){
        this.errMsg = errMsg;
    }

    @Override
    public String toString(){
        return String.format("%s\n%s", errMsg, DIVIDER);
    }
}