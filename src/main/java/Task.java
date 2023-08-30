public class Task {
    protected String name;
    protected Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getNameWithStatus() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }
}
