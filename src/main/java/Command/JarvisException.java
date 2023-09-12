package Command;

public class JarvisException extends Exception {

    public static JarvisException invalidTodoFormat() {
        return new JarvisException("Invalid format. Description cannot be empty for todo.");
    }

    public static JarvisException invalidDeadlineFormat() {
        return new JarvisException("Invalid format. Use: deadline <description> /by <time>");
    }

    public static JarvisException invalidEventFormat() {
        return new JarvisException("Invalid format. Use: event <description> /from <start_time> /to <end_time>");
    }

    public static JarvisException invalidTaskNumber(int index) {
        return new JarvisException("Invalid task number " + (index + 1) + ". Try Again!");
    }

    private JarvisException(String message) {
        super(message);
    }
}
