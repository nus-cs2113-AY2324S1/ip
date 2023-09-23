package eggybyte.ip.data;

import eggybyte.ip.data.task.Task;

import java.util.ArrayList;

public class RunningState {
    public ArrayList<Task> tasks;
    private Boolean running;

    public RunningState(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.running = true;
    }

    public void exit() {
        running = false;
    }

    public Boolean isRunning() {
        return running;
    }
}
