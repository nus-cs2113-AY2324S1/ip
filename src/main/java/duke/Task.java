package duke;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDone(int done) throws CorruptedFileException {
        if (done == 0) {
            isDone = false;
        } else {
            if(done == 1) {
                isDone = true;
            } else {
                throw new CorruptedFileException();
            }
        }
    }

    @Override
    public String toString() {
        String ex = " ";
        if(isDone) {
            ex = "x";
        }
        return ("["+ex+"] "+description);
    }

    public String toFileString() {
        int binaryIsDone = 0;
        if (isDone) {
            binaryIsDone = 1;
        }
        return (binaryIsDone + " | " + this.description);
    }

}
