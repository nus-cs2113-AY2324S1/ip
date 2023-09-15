public class DukeyException extends Exception {
    public DukeyException(String message){
        super(message);
    }

    public static String InvalidInputError(){
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public static String EmptyInputError(){
        return "☹ OOPS!!! I'm sorry, but you have to input something :-|";
    }

    public static String todoDescriptionError(){
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    public static String deadlineDescriptionError(){
        return "☹ OOPS!!! Please enter the description then '/by', followed by the time.";
    }

    public static String EventFormatError(){
        return "☹ OOPS!!! Please enter '/from' and '/to' into your instructions.";
    }

}
