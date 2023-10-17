package nuke.storage.exception;

public class TaskLoadException extends StorageException {
    public String backupFilePath;

    public TaskLoadException(String backupFilePath) {
        this.backupFilePath = backupFilePath;
    }
}
