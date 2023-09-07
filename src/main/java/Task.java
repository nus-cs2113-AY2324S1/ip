abstract class Task {
    private final String description;
    private boolean isDone;
    private TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public boolean equals(Object obj) {
        return this.description.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        output.append(this.type).append("]");

        if (isDone) {
            output.append("[X] ");
        } else {
            output.append("[ ] ");
        }
        return output.append(this.description).toString();
    }
}
