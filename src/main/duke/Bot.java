package duke;

import java.util.Scanner;

import Command.Command;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class Bot {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Scanner input;
    
    public Bot() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        input = new Scanner(System.in);
    }

    public void runBot() {
        storage.importToArrayList(taskList);
        ui.printBotMessage();
        ui.printLineDivider();
        Command command = new Command();
        boolean isExit = false;
        while (!(isExit)) {
            command.convertInput(input.nextLine());
            command.execute(storage, ui, taskList);
            isExit = command.isExit();
        }
        ui.printByeMessage();
    }
}