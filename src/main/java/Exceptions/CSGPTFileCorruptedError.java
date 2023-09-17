package Exceptions;

public class CSGPTFileCorruptedError extends CSGPTException{
    public CSGPTFileCorruptedError(String message) {
        super("Error! File corrupted. " + message);
    }
}
