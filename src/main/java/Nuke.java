import java.util.Scanner;
import java.util.ArrayList;

public class Nuke {

    public static void greet() {
        line();
        System.out.println("     Hello! I'm Nuke\n" + "     What can I do for you?");
        line();
    }

    public static void exit() {
        line();
        System.out.println("     Bye. Hope to see you again soon!");
        line();
    }

    public static void line() {
        System.out.println("    ____________________________________________________________");
    }

    public static void echo() {
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
        if (item.equals("bye")) {
            exit();
            return;
        } else {
            line();
            System.out.println(item);
            line();
            echo();
        }
    }

    public static void dialogue(ArrayList<String> itemList, ArrayList<String> markList) {
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
         if (item.equals("bye")) {
            exit();
            return;
        } else if (item.equals("list")) {
            list(itemList, markList);
        } else if (item.length() > 4 && item.substring(0, 4).equals("mark")) {
            String[] splitItem = item.split(" ");
            int markNum = Integer.parseInt(splitItem[1]);
            mark(itemList, markList, markNum);
        } else if (item.length() > 6 && item.substring(0, 6).equals("unmark")) {
            String[] splitItem = item.split(" ");
            int markNum = Integer.parseInt(splitItem[1]);
            unmark(itemList, markList, markNum);
        } else {
            add(itemList, markList, item);
        }
        dialogue(itemList, markList);
    }

    public static void add(ArrayList<String> itemList, ArrayList<String> markList, String item) {
            line();
            itemList.add(item);
            markList.add("[ ]");
            System.out.printf("     added: %s\n", item);
            line();
    }

    public static void list(ArrayList<String> itemList, ArrayList<String> markList) {
        line();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.printf("     %d.%s %s\n", i+1, markList.get(i), itemList.get(i));
        }
        line();
    }

    public static void mark(ArrayList<String> itemList, ArrayList<String> markList, int markNum) {
        line();
        System.out.println("     Nice! I've marked this task as done:");
        markList.set(markNum - 1, "[X]");
        System.out.printf("       %s %s\n", markList.get(markNum - 1), itemList.get(markNum - 1));
        line();
    }

    public static void unmark(ArrayList<String> itemList, ArrayList<String> markList, int markNum) {
        line();
        System.out.println("     OK, I've marked this task as not done yet:");
        markList.set(markNum - 1, "[ ]");
        System.out.printf("       %s %s\n", markList.get(markNum - 1), itemList.get(markNum - 1));
        line();
    }

    public static void main(String[] args) {
        greet();
        ArrayList<String> itemList = new ArrayList<String>();
        ArrayList<String> markList = new ArrayList<String>();
        dialogue(itemList, markList);
    }
}
