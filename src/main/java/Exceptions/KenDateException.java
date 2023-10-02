package Exceptions;

public class KenDateException extends KenException {
    public KenDateException() {
        super("Uh-oh, I'm a little confused! Please make sure your date follows this style: dd/mm/yyyy HHmm. Thanks, doll!");
    }
}
