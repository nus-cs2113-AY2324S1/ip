package Duke.Task;

/**
 * A specific type of task that only contains task description.
 */
public class ToDo extends Task {

    public static final String taskType = "todo";
    public static final String symbol = "T";

    public ToDo(String description) {
        super(description);
    }
    @Override
    public String getSymbol() {
        return symbol;
    }

    /**
     * Override the printing of Todo.
     *
     * @return String format of Todo for printing.
     */
    @Override
    public String toString() {
        return "\t[" + symbol + "]" + super.toString();
    }
}
