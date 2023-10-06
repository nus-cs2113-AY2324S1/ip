package luke.tasks;

/**
 * The Todo class represents a task of type "Todo" in the Luke application.
 * It extends the Task class and includes specific behavior for todo tasks.
 */
public class Todo extends Task {
    //protected boolean isDone;
    protected String todoGuide = "\ttodo <description>";

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description); //ensures superclass is properly initialised

        if (description.isEmpty()) {
            System.out.println("\tThere is a missing task description. Please follow this format:");
            printGuide();
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Displays a guide on how to format a todo task when printing an error message.
     */
    @Override
    public void printGuide() {
        System.out.println(todoGuide);
    }

    /**
     * Returns a string representation of the todo task for printing.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        //super.toString();
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "\t[T]" + isDoneString + " " + getDescription();
    }

    /**
     * Returns a string representation of the todo task for storing in memory.
     *
     * @return A string representation of the todo task for memory storage.
     */
    @Override
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[T]" + isDoneString + " " + getDescription();
    }
}
