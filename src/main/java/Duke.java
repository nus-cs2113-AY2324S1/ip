import Task.Task;
import Task.TaskList;
import Storage.Storage;
import Ui.Ui;
import Parser.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static final String LINE_DIVIDER = "    _____________________________________";
    static TaskList itemList;

    public static void main(String[] args) {
        itemList = new TaskList();
        Ui.greet();

        Scanner in = new Scanner(System.in);
        String buf = in.nextLine();


        while(!buf.equalsIgnoreCase("bye")){
            try{
                Parser.parse(buf, itemList);
                Storage.save(itemList);
            } catch (IOException e) {
                Ui.reportSaveError();
            } catch(ArrayIndexOutOfBoundsException e) {
                Ui.reportMissingTaskInfo();
            }

            buf = in.nextLine();
        }

        Ui.bye();
    }
}

