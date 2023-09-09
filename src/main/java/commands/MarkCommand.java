package commands;

import main.ResponseProcessor;

public class MarkCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        int number = parseInt(statement, processor);
        processor.taskList.get(number).setCompleted(true);
        System.out.println("Nicu! I have marked this as done master! d(⏜⍢⏜)b");
        System.out.println(processor.taskList.get(number).getStatus());
    }
}
