package duke;

import duke.commands.*;
import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;

import java.io.IOException;

public class Execute {
    String command;
    String userInput;
    Parser parser;
    TaskList tasks;
    public Execute(String command, String userInput, Parser parser, TaskList tasks){
        this.command = command;
        this.userInput = userInput;
        this.parser = parser;
        this.tasks = tasks;
    }

    public void execute(){
        switch (command){
        case "list":
            new PrintList(tasks).print();
            break;
        case "unmark":
            new UnmarkTask(userInput, tasks).unmark();
            break;
        case "mark":
            new MarkTask(userInput, tasks).mark();
            break;
        case "deadline":
            new AddDeadline(parser, tasks).AddDeadlineTask();
            break;
        case "event":
            new AddEvent(parser, tasks).AddEventTask();
            break;
        case "todo":
            new AddTodo(userInput, tasks).AddTodoTask();
            break;
        case "delete":
            new DeleteTask(userInput, tasks).delete();
            break;
        case "find":
            new FindTasks(userInput, tasks).find();
            break;
        case "help":
            Ui.help();
            break;
        default:
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
