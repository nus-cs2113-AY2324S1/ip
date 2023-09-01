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
    public static void echo(String input){
        printLine();
        System.out.println("    "+input);
        printLine();
    }
    public static void listItems(Task[] itemList, int itemCount){
        printLine();
        for (int i  = 0; i < itemCount; i++){
            int indexNo = i+1;
            System.out.println("    "+indexNo+"."+itemList[i].getStatusIcon()+" "+itemList[i].description);
        }
        printLine();
    }

    public static void markItem(Task[] itemList, int itemCount, boolean isMark){
        printLine();
        if (isMark) {
            itemList[itemCount].isDone = true;
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("       " + itemList[itemCount].getStatusIcon() + " " + itemList[itemCount].description);
        } else {
            itemList[itemCount].isDone = false;
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("       " + itemList[itemCount].getStatusIcon() + " " + itemList[itemCount].description);
        }
        printLine();
    }
    public static void main(String[] args) {
        greeting();

        Scanner scanner = new Scanner(System.in);
        Task[] itemList = new Task[100];
        int itemCount = 0;

        while (true){
            String input = scanner.nextLine();
            if (input.startsWith("mark ")){
                int indexPosition = Integer.parseInt(input.substring(5));
                markItem(itemList, indexPosition-1, true);
            } else if (input.startsWith("unmark ")){
                int indexPosition = Integer.parseInt(input.substring(7));
                markItem(itemList, indexPosition-1, false);
            }else {
                if (input.equalsIgnoreCase("bye")) {
                    exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listItems(itemList, itemCount);
                } else {
                    Task task = new Task(input);
                    itemList[itemCount] = task;
                    echo("added: " + task.description);
                    itemCount++;
                }
            }
        }
    }
}
