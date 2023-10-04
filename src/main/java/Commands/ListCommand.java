package Commands;

import Task.TaskList;
import static Task.TaskList.list;

public class ListCommand extends Command{

    @Override
    public void execute(){
        for (int i = 0; i < TaskList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(list.get(i));
        }
    }
}
