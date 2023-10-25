package nupjuk.command;

import nupjuk.Task;
import nupjuk.TaskList;

import static nupjuk.Printer.printLine;

/**
 * ListCommand class
 * get commands starts with "list" and execute it
 * list all tasks in taskList
 */
public class ListCommand {
    public boolean execute(TaskList tasks){
        if(tasks.getSize() == 0){
            printLine("Nothing in the list");
        }
        else{
            for(int i=0;i<tasks.getSize();i++){
                Task task = tasks.getTask(i);
                printLine(String.format("%d.[%s][%s] %s",
                        i+1, task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
            }
        }
        System.out.println("    ____________________________________________________________\n");
        return false;
    }
}
