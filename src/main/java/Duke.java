import java.util.Scanner;
public class Duke {

    public static void exit(){
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void echo(String input){
        System.out.println("    ____________________________________________________________");
        System.out.println("    "+input);
        System.out.println("    ____________________________________________________________");
    }
    public static void main(String[] args) {
        String logo = "______       _     _\n"
                + "| ___ \\     | |   | |\n"
                + "| |_/ / ___ | |__ | |__  _   _\n"
                + "| ___ \\/ _ \\| '_ \\| '_ \\| | | |\n"
                + "| |_/ | (_) | |_) | |_) | |_| |\n"
                + "\\____/ \\___/|_.__/|_.__/ \\__, |\n"
            + "                          __/ |\n"
            + "                         |___/";
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String[] itemList = new String[100];
        int itemCount = 0;

        while (true){
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")){
                exit();
                break;
            } else {
                echo(input);
            }
        }
    }
}
