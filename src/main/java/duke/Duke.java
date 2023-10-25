package main.java.duke;

import main.java.duke.util.*;

public class Duke {

    private UIHandler uiHandler;
    private FileStorage fileStorage;
    private TaskList taskList;

    public Duke() {
        taskList = new TaskList();
        fileStorage = new FileStorage("./duke.txt");
        uiHandler = new UIHandler();
    }

    public void run() {
        fileStorage.fillListFromFile(taskList);
        uiHandler.startMessage();
        Parser.parse(taskList, uiHandler);
        uiHandler.endMessage();
        fileStorage.fillFileFromList(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}