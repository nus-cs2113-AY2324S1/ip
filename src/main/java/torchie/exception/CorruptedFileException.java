package torchie.exception;

public class CorruptedFileException extends TorchieException{

    @Override
    public void showExceptionMessage() {
        System.out.println("File is corrupted! Please check path of file, or content of file");
    }
}
