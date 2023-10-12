package exceptions;

/**
 * A general class that represent all kinds of exceptions related to ListWhisper
 */

class ListWhisperExceptions extends Exception {

    /**
     * Constructor
     *
     * @param errorMessage to be printed out when the exception is thrown
     */
    public ListWhisperExceptions(String errorMessage) {
        super(errorMessage);
    }
}
