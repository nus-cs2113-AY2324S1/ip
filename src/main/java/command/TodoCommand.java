package command;

import Oriento.Oriento;
import exception.OrientoException;
import message.Text;
import task.Todo;

import java.io.IOException;

public class TodoCommand extends AddCommand{
    /**
     *
     * @param todoCmd represents thw whole original user command starts from "todo“
     */
    public TodoCommand(String todoCmd){
        super(todoCmd);
    }

    @Override
    public void executeCommand(){
        try {
            Oriento.list[Oriento.taskCount] = Todo.newTodoTask(this.command);
            Text.createTaskSuccessMsg();
        } catch (OrientoException e) {
            e.incorrectFormatException("todo");
        } catch (IOException io){
            System.out.println("OMG! Something went wrong! Please check if the source files are available.");
        }
    }
}
