package nuke.savefile.exception;

public class TaskFileCopyException extends SaveFileException {
    public String filePath;

    public TaskFileCopyException(String filePath) {
        this.filePath = filePath;
    }
}
