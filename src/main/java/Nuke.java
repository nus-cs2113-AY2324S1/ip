import java.util.Scanner;
import java.util.ArrayList;

public class Nuke {

    public static void printGreetingMessage() {
        printLine();
        System.out.println("     Hello! I'm Nuke\n" + "     What can I do for you?");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void echo() {
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
        if (item.equals("bye")) {
            printExitMessage();
            return;
        } else {
            printLine();
            System.out.println(item);
            printLine();
            echo();
        }
    }

    public static void dialogue(ArrayList<String> itemList, ArrayList<String> markList) {
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
         if (item.equals("bye")) {
            printExitMessage();
            return;
        } else if (item.equals("list")) {
            list(itemList, markList);
        } else if (item.length() > 4 && item.substring(0, 4).equals("mark")) {
            String[] splitItem = item.split(" ");
            int listIndex = Integer.parseInt(splitItem[1]);
            mark(itemList, markList, listIndex);
        } else if (item.length() > 6 && item.substring(0, 6).equals("unmark")) {
            String[] splitItem = item.split(" ");
            int listIndex = Integer.parseInt(splitItem[1]);
            unmark(itemList, markList, listIndex);
        } else {
            add(itemList, markList, item);
        }
        dialogue(itemList, markList);
    }

    public static void add(ArrayList<String> itemList, ArrayList<String> markList, String item) {
            printLine();
            itemList.add(item);
            markList.add("[ ]");
            System.out.printf("     added: %s\n", item);
            printLine();
    }

    public static void list(ArrayList<String> itemList, ArrayList<String> markList) {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.printf("     %d.%s %s\n", i+1, markList.get(i), itemList.get(i));
        }
        printLine();
    }

    public static void mark(ArrayList<String> itemList, ArrayList<String> markList, int listIndex) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        markList.set(listIndex - 1, "[X]");
        System.out.printf("       %s %s\n", markList.get(listIndex - 1), itemList.get(listIndex - 1));
        printLine();
    }

    public static void unmark(ArrayList<String> itemList, ArrayList<String> markList, int listIndex) {
        printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        markList.set(listIndex - 1, "[ ]");
        System.out.printf("       %s %s\n", markList.get(listIndex - 1), itemList.get(listIndex - 1));
        printLine();
    }

    public static void main(String[] args) {
        printGreetingMessage();
        ArrayList<String> itemList = new ArrayList<String>();
        ArrayList<String> markList = new ArrayList<String>();
        dialogue(itemList, markList);
    }
}
