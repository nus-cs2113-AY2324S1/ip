package torchie;

import torchie.parser.Parser;
import torchie.storage.Storage;
import torchie.task.TaskList;
import torchie.ui.Ui;

public class Torchie {
    public static void main(String[] args) {
        // initialisation
        Storage storage = new Storage();
        TaskList taskList = storage.retrieveData();
        Ui ui = new Ui(taskList);
        Parser commandparser = new Parser(taskList, storage);

        ui.start();
        commandparser.getUserCommand();

    }
}