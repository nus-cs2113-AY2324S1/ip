package Exceptions;

public class CSGPTWriteFileException extends CSGPTException{
    public CSGPTWriteFileException() {
        super("Error writing to file");
    }
}
