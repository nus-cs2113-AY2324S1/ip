package app;

import exceptions.ZranExceptions;
import taskmanagement.TaskItems;
import taskmanagement.TaskListData;

import java.io.IOException;

public class Zran {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________";

    public static void main(String[] args) throws ZranExceptions, IOException {
        System.out.printf((outputFormat) + "%n",
                "hello! I'm app.Zran, your personal assistant:)! \n" +
                "    Type in your to dos for the day and press enter! \n " +
                "   Type 'help' to view to list of commands the bot accepts! ");
        TaskListData.init();
        TaskItems.addItems();
    }

}
