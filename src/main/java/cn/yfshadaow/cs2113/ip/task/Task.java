package cn.yfshadaow.cs2113.ip.task;

import com.google.gson.annotations.Expose;

public abstract class Task {
    @Expose
    protected boolean isDone = false;
    @Expose
    protected String name;

    @SuppressWarnings("unused")
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    public String toStringWithIsDone() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }
}
