public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        setName(name);
        setDone(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String mark = isDone()? "X" : " ";
        return String.format("[%s] %s", mark, getName());
    }
}
