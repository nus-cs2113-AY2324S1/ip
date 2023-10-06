package command;

import duke.Duke;
import exception.DukeException;
import message.Text;
import task.Todo;

import java.io.IOException;

public class TodoCommand extends AddCommand{

    public TodoCommand(String todoCmd){
        super(todoCmd);
    }

    @Override
    public void executeCommand(){
        try {
            Duke.list[Duke.taskCount] = Todo.newTodoTask(this.command);
            Text.createTaskSuccessMsg();
        } catch (DukeException e) {
            e.incorrectFormatException("todo");
        } catch (IOException io){
            System.out.println("OMG! Something went wrong! Please check if the source files are available.");
        }
    }
}
