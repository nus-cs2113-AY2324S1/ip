package Commands;

import taskmanagement.Task;
import taskmanagement.Storage;
import userinputs.Ui;

import java.util.ArrayList;

public class ListTaskCommand extends Commands {
    public ListTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        echo(tasks);
    }

    public static void echo(ArrayList<Task> items){
        System.out.println("    ____________________________________________________________");
        System.out.println("    List of Tasks:");
        int index=0;
        for(Task item : items){
            if((item != null)) {
                System.out.print("    "+ ++index + ". ");
                System.out.println(item.toString());
            }
        }
        System.out.println("    ____________________________________________________________");
    }
}
