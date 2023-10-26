package Exceptions;

public class DukeyException extends Exception {
    public DukeyException(String message){
        super(message);
    }
    public static String EmptyInputError(){
        return "☹ OOPS!!! I'm sorry, but you have to input something :-|";
    }

}
