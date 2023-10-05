package jarvis.exception;

public class JarvisException extends Exception {

    public static JarvisException invalidTodoFormat() {
        return new JarvisException("Invalid format. Description cannot be empty for todo sir.");
    }

    public static JarvisException invalidDeadlineFormat() {
        return new JarvisException("Invalid format. Use: deadline <description> /by <time> sir");
    }

    public static JarvisException invalidEventFormat() {
        return new JarvisException("Invalid format. Use: event <description> /from <start_time> /to <end_time> sir");
    }

    public static JarvisException invalidTaskNumber(int index) {
        return new JarvisException("Invalid task number " + (index + 1) + ". Try Again sir!");
    }

    public static JarvisException invalidCommand(){
        return new JarvisException("Unknown jarvis.command. Please try again sir.");
    }
    
    public static JarvisException numberFormatException() {
        return new JarvisException("Expected a number for the task index. Please provide a valid integer sir!");
    }

    public JarvisException(String message) {
        super(message);
    }
}
