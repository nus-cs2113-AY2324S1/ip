package Task;

/**
 * class indicating an instance of a deadline object, extending task class
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * constructor of "Deadline"
     * @param taskName name of task
     * @param deadline deadline
     */
    public Deadline(String taskName, String deadline){
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * returns string representation of deadline task
     * @return a string representation of the task with its status and deadline
     */
    @Override
    public String toString(){
        return "[D] " + super.toString() + " (by: " + deadline + ")";
    }
}
