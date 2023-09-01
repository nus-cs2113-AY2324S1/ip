class Task {
    private final String task;
    private int id;
    private boolean done;

    public Task(String task, int id) {
        this.task = task;
        this.id = id;
        this.done = false;
    }

    public static Task createTask(String task, int id) {
        return new Task(task, id);
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String output = "";
        if (done) {
            output = id + "." + "[X] " + this.task;
        } else {
            output = id + "." + "[ ] " + this.task;
        }
        return output;
    }
}
