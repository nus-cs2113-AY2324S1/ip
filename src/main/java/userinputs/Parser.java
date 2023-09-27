package userinputs;

import Commands.AddTaskCommand;
import Commands.Commands;
import Commands.MarkTaskCommand;
import Commands.UnmarkTaskCommand;
import Commands.DeleteTaskCommand;
import Commands.ListTaskCommand;
import Commands.HelpTaskCommand;

public class Parser {
    public static Commands parse(String input) {
        if (input.startsWith(Ui.MARK_TASK_COMMAND)) {
            return new MarkTaskCommand(input);
        } else if (input.startsWith(Ui.UNMARK_TASK_COMMAND)) {
            return new UnmarkTaskCommand(input);
        } else if (input.startsWith(Ui.DELETE_TASK_COMMAND)){
            return new DeleteTaskCommand(input);
        } else if(input.equals(Ui.LIST_TASK_COMMAND)) {
            return new ListTaskCommand(input);
        } else if (input.startsWith(Ui.USER_HELP_COMMAND)){
            return new HelpTaskCommand(input);
        } else {
            //try{
                return new AddTaskCommand(input);
           // }
//            catch(ZranExceptions e){
//                System.out.println();
//                System.out.printf((outputFormat) + "%n", e.getMessage());
//            }
        }
    }
}
