package duke.tasks;

/**
 * Represents a type of task that can be entered into task-list.
 * namely a task of type deadline.
 */
public class Deadline extends Task{

    public Deadline(String newTask,String date) {
        super(newTask);
        dueDate= date;
        taskType = new String[1];
        taskType[0] = "D";
    }
}
