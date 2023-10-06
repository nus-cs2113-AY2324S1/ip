package doli.tasks;

/**
 * <h3>ToDo class</h3>
 * The ToDo class extends the Task class and is specifically
 * made to handle tasks of type todo, meaning that they do not have
 * any due date or timings in general, but simply a description.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public class ToDo extends Task{
    /**
     * Constructs an object of type ToDo and calls its superclass'constructor.
     *
     * @param description of type String containing details about the task.
     */
    public ToDo(String description) {
        super(description);
    }
    /**
     * Changes the boolean variable isDone of the task.
     *
     * @param isDone of type boolean.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    /**
     * Returns whether a todo task was completed or not.
     *
     * @return boolean variable explaining whether the task was completed or not.
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Overrides the superclass' method .toString() allowing for
     * a tailored use incorporating details of the task such as its type and whether it is done or not.
     *
     * @return a String summarising the todo's details.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString(); // T stands for ToDo
    }
}
