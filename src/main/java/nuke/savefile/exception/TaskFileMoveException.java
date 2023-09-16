package nuke.savefile.exception;

public class TaskFileMoveException extends SaveFileException {
    public String filePath;

    public TaskFileMoveException(String filePath) {
        this.filePath = filePath;
    }
}
