package duke.tasks;

/**
 * Represent a todo task type.
 */
public class ToDos extends Task{


    public ToDos(String newTask) {
        super(newTask);
        taskType = new String[1];
        taskType[0] = "T";
    }
}
