public class DukeyException extends Exception {
    public static String InvalidInputError() {
        String errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        return errorMsg;
    }
    public static void EmptyDescriptionError() {
        String errorMsg = "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
