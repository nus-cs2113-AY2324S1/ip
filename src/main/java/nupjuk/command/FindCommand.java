package nupjuk.command;
import nupjuk.*;
import java.io.IOException;
import static nupjuk.Printer.printLine;

public class FindCommand {
    public boolean execute(TaskList tasks, String[] tokens) throws IOException{
        int foundTasks = 0;
        for(int i=0;i<tasks.getSize();i++){
            Task task = tasks.getTask(i);
            String description = task.getDescription();
            if(description.contains(tokens[1].trim())){
                foundTasks++;
                if(foundTasks == 1){
                    printLine("Here are the matching tasks in your list:");
                }
                printLine(String.format("%d.[%s][%s] %s",
                        foundTasks, task.getTypeIcon(), task.getStatusIcon(), task.getDescription()));
            }
        }
        if(foundTasks == 0){
            printLine("Cannot found any matching Tasks");
        }
        System.out.println("    ____________________________________________________________\n");
        return false;
    }
}
