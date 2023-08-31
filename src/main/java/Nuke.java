import java.util.Scanner;
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

    public static void main(String[] args) {
        greet();
        echo();
    }
}
