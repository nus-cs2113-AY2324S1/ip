class Task {
    private final String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public static Task createTask(String task) {
        return new Task(task);
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String output = "";
        if (done) {
            output = "[X] " + this.task;
        } else {
            output = "[ ] " + this.task;
        }
        return output;
    }
}
