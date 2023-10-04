package Duchess.TaskObjects;

/** Class for ToDo tasks. Extension of Task class.
 * 
 */
public class ToDo extends Task {
    
    /** Empty constructor. */
    public ToDo() {
        super();
    }

    /** Main constructor for ToDo class.
     * @param name Name of task.
     */
    public ToDo(String name) {
        super(name);
    }

    /** Returns string representation of ToDo object.
     * @return String representation of ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /** Returns string representation of ToDo object for saving.
     * @return String representation of ToDo object for saving.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}    