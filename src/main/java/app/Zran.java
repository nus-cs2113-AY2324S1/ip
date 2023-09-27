package app;

import exceptions.ZranExceptions;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Ui;

import java.io.IOException;

public class Zran {

    public static void main(String[] args) throws ZranExceptions, IOException {
        Ui ui = new Ui();
        ui.showWelcome();
        Storage.init();
        TaskList.addItems(ui);
        ui.closeScanner();
    }
}
