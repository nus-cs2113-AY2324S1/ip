package doli.exceptions;

/**
 * <h3>DoliExceptions class</h3>
 * The DoliExceptions class handles specific exceptions for the chatbot.
 * It inherits from the Exception class.
 * So far merely implemented in its most trivial form
 * and used as practice.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public class DoliExceptions extends Exception {
    /**
     * Constructs an object of type DoliExceptions.
     *
     * @param str of type String to be passed to the super class.
     */
    public DoliExceptions(String str) {
        super(str);
    }
}
