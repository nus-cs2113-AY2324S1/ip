package torchie.exception;

public class CorruptedFileException extends TorchieException{
    public CorruptedFileException() {
        super("File is corrupted! Please check path of file, or content of file");
    }
}
