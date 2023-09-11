package commands;

import main.ResponseProcessor;
import task.Task;

public class ListCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        if (!statement.isEmpty()){
            throw new IllegalArgumentException ("uwu dont write anything after 'list' ｡･ﾟﾟ･(>д<)･ﾟﾟ･｡");
        }
        if (processor.taskList.isEmpty()){
            throw new IllegalArgumentException ("uwu masta task list is empty ｡･ﾟﾟ･(>д<)･ﾟﾟ･｡");
        }
        int num = 0;
        for (Task task : processor.taskList) {
            num += 1;
            System.out.println(num + ". " + task.getStatus());
        }
    }
}
