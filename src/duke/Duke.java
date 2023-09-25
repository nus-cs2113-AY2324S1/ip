package duke;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {


    public static void main(String[] args) {
        String line;
        String result;

        Ui ui = new Ui();
        TaskList tasks;

        ui.printWelcome();

        Storage f = new Storage();
        try {
            tasks = new TaskList(f.readTasksFromFile());
        } catch (IOException e) {
            ui.println(String.valueOf(e));
            tasks = new TaskList();
        }

        line = ui.getCommand();
        while (line.equals("bye") == false) {
            ui.printLine();

            if (line.startsWith("list")) {
                result = tasks.handleGetList();
                ui.printCommandResult(result);
            } else if (line.startsWith("mark")) {
                result = tasks.markItem(line);
                ui.printCommandResult(result);
            } else if (line.startsWith("unmark")) {
                result = tasks.unmarkItem(line);
                ui.printCommandResult(result);
            } else if (line.startsWith("todo")) {
                try {
                    result = tasks.handleCreateTodo(line);
                    ui.printCommandResult(result);
                } catch (DukeException e) {
                    ui.println(String.valueOf(e));
                }
            } else if (line.startsWith("deadline")) {
                try {
                    result = tasks.handleCreateDeadline(line);
                    ui.printCommandResult(result);
                } catch (DukeException e) {
                    ui.println(String.valueOf(e));
                }
            } else if (line.startsWith("event")) {
                result = tasks.handleCreateEvent(line);
                ui.printCommandResult(result);
            } else if (line.startsWith("delete")) {
                result = tasks.handleDeleteTask(line);
                ui.printCommandResult(result);
            } else {
                ui.println("I don't know that command");
            }

            ui.printLine();

            line = ui.getCommand();
        }


        try {
            f.writeTasksToFile(tasks.handleWriteList());
        } catch (IOException e) {
            ui.println(String.valueOf(e));
        }

        ui.printFarewell();
    }
}
