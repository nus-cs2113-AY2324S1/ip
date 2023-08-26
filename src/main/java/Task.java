public class Task {
    protected String name;
    protected Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public String getNameWithStatus() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.name);
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }
}
