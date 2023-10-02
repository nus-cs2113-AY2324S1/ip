package duke.tasks;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a Deadline object with the given task name and deadline.
     *
     * @param taskName The name of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String taskName, String deadline){
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    public String toString(){
        if(this.getIsDone()){
            return "[D][X] " + this.getTaskName() + " (" + this.deadline + ")";
        }else{
            return "[D][ ] " + this.getTaskName() + " (" + this.deadline + ")";
        }
    }

    /**
     * Returns the deadline of the Deadline object.
     *
     * @return The deadline of the Deadline object.
     */
    public String getDeadline(){
        return this.deadline;
    }
}