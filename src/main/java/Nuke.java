public class Nuke {

    public static void greet() {
        System.out.println("Hello! I'm Nuke\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        line();
        greet();
        line();
        exit();
        line();
    }
}
