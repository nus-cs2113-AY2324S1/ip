package nuke.task;

import static nuke.task.TaskParser.TASK_FORMAT_MARKED;
import static nuke.task.TaskParser.TASK_FORMAT_SEPARATOR;
import static nuke.task.TaskParser.TASK_FORMAT_UNMARKED;

/**
 * Represents a task.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a task with the name.
     *
     * @param name name of the task
     */
    public Task(String name) {
        setName(name);
        setDone(false);
    }

    /**
     * Returns name of the task.
     *
     * @return name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the task.
     *
     * @param name name of the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns condition of the task if it is done or not.
     *
     * @return condition of the task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets condition of the task as done or not.
     *
     * @param done condition of the task
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns type of the task.
     *
     * @return type of the task
     */
    public abstract String getType();

    /**
     * Returns string representations of the task.
     *
     * @return string representations of the task
     */
    @Override
    public String toString() {
        String mark = isDone()? "X" : " ";
        return String.format("[%s][%s] %s", getType(), mark, getName());
    }

    /**
     * Returns the task in form of formatted manner,
     * which is used to save in file.
     *
     * @return the task in form of formatted manner.
     */
    public String formatData() {
        String mark = isDone()? TASK_FORMAT_MARKED : TASK_FORMAT_UNMARKED;
        return getType() + TASK_FORMAT_SEPARATOR
                + mark + TASK_FORMAT_SEPARATOR
                + getName();
    }
}
