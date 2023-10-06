package main.java.duke;

import main.java.duke.util.task.*;
import main.java.duke.util.*;

import java.util.ArrayList;

public class Duke {

    private UIHandler uiHandler;
    private FileStorage fileStorage;
    private Parser parser;
    private TaskList taskList;

    private static void run() {
        ArrayList<Task> tasks = TaskList.emptyList();
        FileStorage.fillListFromFile(tasks);
        UIHandler.startMessage();
        Parser.parse(tasks);
        UIHandler.endMessage();
        FileStorage.fillFileFromList(tasks);
    }

    public static void main(String[] args) {
        run();
    }
}