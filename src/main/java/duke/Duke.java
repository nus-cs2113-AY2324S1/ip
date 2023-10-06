package main.java.duke;

import main.java.duke.util.task.*;
import main.java.duke.util.*;

import java.util.ArrayList;

public class Duke {

    private UIHandler uiHandler;
    private FileStorage fileStorage;
    private TaskList taskList;

    public Duke() {
        taskList = new TaskList();
        fileStorage = new FileStorage("./duke.txt");
    }

    public void run() {
        fileStorage.fillListFromFile(taskList);
        UIHandler.startMessage();
        Parser.parse(taskList);
        UIHandler.endMessage();
        fileStorage.fillFileFromList(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}