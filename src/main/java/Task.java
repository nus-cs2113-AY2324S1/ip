class Task {
    private final String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public static Task createTask(String task) {
        return new Task(task);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public boolean equals(Object obj) {
        return this.task.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder output;
        if (isDone) {
            output = new StringBuilder("[X] ");
        } else {
            output = new StringBuilder("[ ] ");
        }
        return output.append(this.task).toString();
    }
}
