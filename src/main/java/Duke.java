import Task.Task;
import Task.Tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String LINE_DIVIDER = "    _____________________________________";

    public static void main(String[] args) {

        ArrayList<Task> itemList = IO.load();

        System.out.println("Hello! I'm Axel!\n"
                + "What can I do for you?\n"
                + LINE_DIVIDER);

        Scanner in = new Scanner(System.in);
        String buf = in.nextLine();

        int i = itemList.size(); // current index in itemList

        while(!buf.equalsIgnoreCase("bye")){
            switch(buf.toLowerCase().split(" ")[0]) {

            case "list":
                Tasks.listItems(itemList, i);
                break;

            case "mark":
                Tasks.markTask(itemList,
                        Integer.parseInt(buf.split(" ")[1]));
                break;

            case "unmark":
                Tasks.unmarkTask(itemList,
                        Integer.parseInt(buf.split(" ")[1]));
                break;

            case "todo":
                Tasks.addTodo(buf, itemList, i);
                i += 1;
                break;

            case "deadline":
                Tasks.addDeadline(buf, itemList, i);
                i += 1;
                break;

            case "event":
                Tasks.addEvent(buf, itemList, i);
                i += 1;
                break;

            case "delete":
                Tasks.delete(Integer.parseInt(buf.split(" ")[1]), itemList);
                break;

            default:
                System.out.println(LINE_DIVIDER +
                        "\n    I couldn't find a command word, try again!\n"
                        + LINE_DIVIDER);
                break;
            }
            buf = in.nextLine();

            try {
                IO.save(itemList, i);
            } catch (IOException e) {
                System.out.println("    I couldn't save your file, try again?\n"
                        + LINE_DIVIDER);
            }
        }

        System.out.println(LINE_DIVIDER +
                "\n    Bye. Hope to see you again soon!\n"
                + LINE_DIVIDER);
    }
}

