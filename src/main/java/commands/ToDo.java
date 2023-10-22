package commands;

/**
 * The `ToDo` class represents a to-do task, which is a type of task that needs to be done.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.isDone = false;
        this.type = 'T';
    }

}
