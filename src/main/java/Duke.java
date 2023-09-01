import java.util.Scanner;
public class Duke {
    public static void greeting(){
        String logo = "______       _     _\n"
                + "| ___ \\     | |   | |\n"
                + "| |_/ / ___ | |__ | |__  _   _\n"
                + "| ___ \\/ _ \\| '_ \\| '_ \\| | | |\n"
                + "| |_/ | (_) | |_) | |_) | |_| |\n"
                + "\\____/ \\___/|_.__/|_.__/ \\__, |\n"
                + "                          __/ |\n"
                + "                         |___/";
        System.out.println("    Hello from\n" + logo);
        printLine();
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        printLine();
    }

    public static void exit(){
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine(){
        System.out.println("    ____________________________________________________________");
    }
        printLine();
        printLine();
        printLine();
        printLine();
        printLine();
        printLine();
        greeting();

        Scanner scanner = new Scanner(System.in);
        Task[] itemList = new Task[100];
        int itemCount = 0;

        while (true){
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")){
                exit();
                break;
            } else if (input.equalsIgnoreCase("list")){
                listItems(itemList, itemCount);
            } else {
                itemList[itemCount] = input;
                echo("added: "+input);
                itemCount++;
            }
        }
    }
}
