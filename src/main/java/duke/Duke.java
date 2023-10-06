package main.java.duke;

import main.java.duke.util.task.*;
import main.java.duke.util.*;

import java.util.ArrayList;

public class Duke {

    private UIHandler uiHandler;
    private FileStorage fileStorage;
    private TaskList taskList;

    private void run() {
        taskList = new TaskList();
        FileStorage.fillListFromFile(taskList);
        UIHandler.startMessage();
        Parser.parse(taskList);
        UIHandler.endMessage();
        FileStorage.fillFileFromList(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}