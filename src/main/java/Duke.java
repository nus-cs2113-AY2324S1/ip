import Task.Task;
import Task.TaskList;
import Storage.Storage;
import Ui.Ui;

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
                switch(buf.toLowerCase().split(" ")[0]) {

                case "list":
                    itemList.listItems();
                    break;

                case "mark":
                    itemList.markTask(Integer.parseInt(buf.split(" ")[1]));
                    break;

                case "unmark":
                    itemList.unmarkTask(Integer.parseInt(buf.split(" ")[1]));
                    break;

                case "todo":
                    itemList.addTodo(buf);
                    break;

                case "deadline":
                    itemList.addDeadline(buf);
                    break;

                case "event":
                    itemList.addEvent(buf);
                    break;

                case "delete":
                    itemList.delete(Integer.parseInt(buf.split(" ")[1]));
                    break;

                default:
                    Ui.reportMissingCommandWord();
                    break;
            }
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

