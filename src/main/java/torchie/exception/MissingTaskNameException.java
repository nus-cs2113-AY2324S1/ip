package torchie.exception;

public class MissingTaskNameException extends TorchieException{

    @Override
    public void showExceptionMessage() {
        System.out.println("Make sure input contains <task name> after <command> word");
    }}
