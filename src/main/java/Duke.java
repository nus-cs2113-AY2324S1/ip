import Task.Task;
import Task.TaskList;
import Storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String LINE_DIVIDER = "    _____________________________________";

    public static void main(String[] args) {

        TaskList itemList = new TaskList();

        System.out.println("Hello! I'm Axel!\n"
                + "What can I do for you?\n"
                + LINE_DIVIDER);

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
                    System.out.println(LINE_DIVIDER +
                        "\n    I couldn't find a command word, try again!\n"
                        + LINE_DIVIDER);
                    break;
            }
                Storage.save(itemList);
            } catch (IOException e) {
                System.out.println("    I couldn't save your file, try again?\n"
                        + LINE_DIVIDER);
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(LINE_DIVIDER + "\n"
                        + "    Oops, looks like you left something out!\n"
                        + LINE_DIVIDER);
            }

            buf = in.nextLine();
        }

        System.out.println(LINE_DIVIDER +
                "\n    Bye. Hope to see you again soon!\n"
                + LINE_DIVIDER);
    }
}

