package main;
import magpie.files.Storage;
import magpie.input.Ui;
import magpie.task.TaskList;

import java.io.IOException;


public class Magpie {

    private Storage storage;
    private TaskList taskManager;
    private Ui ui;

    public Magpie() {
        taskManager = new TaskList();
        ui = new Ui();
        storage = new Storage();
        storage.loadFile(taskManager);

    }


    public void exit() {
        ui.printByeMessage();
        try{
            storage.saveToFile();
        }
        catch (IOException e){
            System.out.println("Opps. File writing error!");
        }
    }
    public void run() {
        ui.printLogo();
        ui.processUserInput(taskManager);

        exit();
    }

    public static void main(String[] args) {

        new Magpie().run();

    }

}