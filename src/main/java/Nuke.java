import java.util.Scanner;
import java.util.ArrayList;

public class Nuke {

    public static void greet() {
        line();
        System.out.println("Hello! I'm Nuke\n" + "What can I do for you?");
        line();
    }

    public static void exit() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
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

    public static void add(ArrayList<String> itemList) {
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();
        
        if (item.equals("bye")) {
            exit();
            return;
        }if (item.equals("list")) {
            list(itemList);
        } else {
            line();
            itemList.add(item);
            System.out.printf("added: %s\n", item);
            line();
            add(itemList);
        }
    }

    public static void list(ArrayList<String> itemList) {
        line();
        for (int i = 0; i < itemList.size(); i++) {
            System.out.printf("%d. %s\n", i, itemList.get(i));
        }
        line();
        add(itemList);
    }

    public static void main(String[] args) {
        greet();
        ArrayList<String> itemList = new ArrayList<String>();
        add(itemList);
    }
}
