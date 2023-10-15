package duke.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given task name and sets its status to not done.
     *
     * @param taskName The name of the task.
     */
    public Todo(String taskName){
        super(taskName);
    }

    /**
     * Returns a string representation of the Todo object.
     *
     * @return A string representation of the Todo object.
     */
    public String toString(){
        if(this.getIsDone()){
            return "[T][X] " + this.getTaskName();
        }else{
            return "[T][ ] " + this.getTaskName();
        }
    }
}