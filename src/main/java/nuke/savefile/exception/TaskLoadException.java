package nuke.savefile.exception;

public class TaskLoadException extends SaveFileException {
    public String backupFilePath;

    public TaskLoadException(String backupFilePath) {
        this.backupFilePath = backupFilePath;
    }
}
