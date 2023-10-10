package eggybyte.ip.data;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Task;

import java.util.ArrayList;

/**
 * A class used for recording a state, contaning 'tasks' for a list of task that
 * is going to be operated, and 'running' to indicate the status of the program.
 */
public class RunningState {
    public ArrayList<Task> tasks;
    private Boolean running;

    public RunningState(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.running = true;
    }

    /**
     * Terminate the current program.
     * However, you may switch to another running state for further operation.
     */
    public void exit() {
        running = false;
    }

    /**
     * Running getter as it's a private variable.
     */
    public Boolean isRunning() {
        return running;
    }
}
