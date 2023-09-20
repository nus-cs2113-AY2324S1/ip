package chat0pt.helper;

import chat0pt.commands.Task;
import chat0pt.commands.CommandHandler;

import java.util.ArrayList;

public class CommandProcessor {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static boolean runCommand(String input){
        String[] splitString = SplitTokens.splitString(input);
        switch(splitString[0].toLowerCase()){
        case "bye":
            Printer.goodbyeMessage();
            return false;
        case "list":
            Printer.listHandler(tasks);
            break;
        case "todo":
            Task todoTask = CommandHandler.addTodo(splitString);
            if(todoTask != null){
                tasks.add(todoTask);
                Printer.successfulTask(todoTask,tasks.size());
            }
            break;
        case "deadline":
            Task deadlineTask = CommandHandler.addDeadline(splitString);
            if (deadlineTask != null){
                tasks.add(deadlineTask);
                Printer.successfulTask(deadlineTask,tasks.size());
            }
            break;
        case "event":
            Task eventTask = CommandHandler.addEvent(splitString);
            if(eventTask != null){
                tasks.add(eventTask);
                Printer.successfulTask(eventTask,tasks.size());
            }
            break;
        case "mark":
            int markKey = CommandHandler.markChecker(splitString);
            if(markKey != -1){
                Task t = tasks.get(markKey);
                t.setMarked(true);
                tasks.set(markKey,t);
                Printer.mark(t,true);
            }
            break;
        case "unmark":
            int unmarkKey = CommandHandler.markChecker(splitString);
            if(unmarkKey != -1){
                Task t = tasks.get(unmarkKey);
                t.setMarked(false);
                tasks.set(unmarkKey,t);
                Printer.mark(t, false);
            }
            break;
        default:
            Printer.unsupportedCommand();
            break;
        }
        return true;
    }
}
