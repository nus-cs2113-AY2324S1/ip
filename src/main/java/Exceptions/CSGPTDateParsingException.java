package Exceptions;

public class CSGPTDateParsingException extends CSGPTParsingException {
    public CSGPTDateParsingException() {
        super("Date format incorrect. Please use YYYY-MM-DD.");
    }
}
