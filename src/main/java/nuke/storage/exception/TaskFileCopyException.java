package nuke.storage.exception;

public class TaskFileCopyException extends StorageException {
    public String filePath;

    public TaskFileCopyException(String filePath) {
        this.filePath = filePath;
    }
}
