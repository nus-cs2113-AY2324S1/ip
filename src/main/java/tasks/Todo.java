package tasks;

/**
 * Represents a 'Todo' task in the Duke application.
 */
public class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns a new Todo task based on the given task data.
     * 
     * @param taskData The task data to be converted.
     * @return The new Todo task.
     */
    public static Task dataToTask(String taskData) {
        int firstSplitIndex = taskData.indexOf("|");
        boolean isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
        String desc = taskData.substring(firstSplitIndex + 2);
        Todo newTodo =  new Todo(desc);
        newTodo.isDone = isDone;
        return newTodo;
    }

    /**
     * Converts the Todo task object to a data representation for saving to a file.
     * 
     * @return The data representation of the Todo task.
     */    
    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "T | " + done + " | " + this.description;
    }
    
    /**
     * Returns the string representation of the Todo task.
     * 
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
