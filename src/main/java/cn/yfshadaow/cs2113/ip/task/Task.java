package cn.yfshadaow.cs2113.ip.task;

import com.google.gson.annotations.Expose;

/**
 * Represents an abstract task.
 */
public abstract class Task {
    /**
     * Whether this task is done.
     */
    @Expose
    protected boolean isDone = false;
    /**
     * The task name.
     */
    @Expose
    protected String name;

    /**
     * Gets whether the task is done.
     *
     * @return whether task is done
     */
    @SuppressWarnings("unused")
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets whether the task is done
     *
     * @param done the boolean value to be set
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Gets name.
     *
     * @return the name of task
     */
    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name of task
     */
    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets a string used for printing by UI
     *
     * @return the formatted string
     */
    public String toStringWithIsDone() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }
}
