package commands;

import main.ResponseProcessor;
import task.Task;

public class ListCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        int num = 0;
        for (Task task : processor.taskList) {
            num += 1;
            System.out.println(num + ". " + task.getStatus());
        }
        if (processor.taskList.isEmpty()){
            throw new IllegalArgumentException ("uwu masta task list is empty ｡･ﾟﾟ･(>д<)･ﾟﾟ･｡");
        }
    }
}
