package duke;

/**
 * Represents a task consisting of a description and the marking label.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Generates new Task with a specified description.
     * By default, the task is not done.
     * @param description description of the task
     */
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

    /**
     * Sets the task as done or not done. This method is only used when reading from the file.
     * @param done 0 if not done, 1 if done
     * @throws CorruptedFileException if done is not 0 or 1
     */
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
    /**
     * Returns the single line representation of the task which is used by the UI.
     * @return string representation of the task.
     */
    public String toString() {
        String ex = " ";
        if(isDone) {
            ex = "x";
        }
        return ("["+ex+"] "+description);
    }

    /**
     * Returns the single line representation of the task which is used for the file.
     * @return string representation of the task.
     */
    public String toFileString() {
        int binaryIsDone = 0;
        if (isDone) {
            binaryIsDone = 1;
        }
        return (binaryIsDone + " | " + this.description);
    }

}
