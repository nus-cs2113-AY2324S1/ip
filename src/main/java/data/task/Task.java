package data.task;

public class Task {
    protected boolean isMarked;
    protected String description;
    private static int count = 0;

    public Task (String description) {
        this.description = description;
        count++;
    }

    public Task (String description, int setMark) {
        this.description = description;
        if (setMark == 1) {
            isMarked = true;
        } else if (setMark == 0) {
            isMarked = false;
        }
        count++;
    }

    public void markTask() {
        isMarked = true;
    }

    public void unMarkTask() {
        isMarked = false;
    }

    public boolean getTaskStatus() {
        return isMarked;
    }

    public String getDetails() {
        String mark;
        if (getTaskStatus()){
            mark = "X";
        } else {
            mark = " ";
        }
        return "[" + mark + "] " + description;
    }

    public String getDescription() {
        return description;
    }

    public static int getTaskCount() {
        return count;
    }

    public static void decreaseTaskCountByOne() {
        count--;
    }

}
