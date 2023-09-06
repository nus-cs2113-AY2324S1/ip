package cn.yfshadaow.cs2113.ip;

public class Task {
    private boolean isDone = false;
    private String name;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String toStringWithIsDone() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }
}
