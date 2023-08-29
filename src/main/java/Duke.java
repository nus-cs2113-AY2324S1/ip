import java.util.Scanner;

public class Duke {

    final static String BOT_NAME= "Elgin";
    final static String SEPARATOR = "____________________________________________________________";

    static TaskManager tasks = new TaskManager();

    public static void formatPrint(String[] lines){
        System.out.println("\t"+SEPARATOR);
        for (String line:lines){
            System.out.println("\t"+line);
        }
        System.out.println("\t"+SEPARATOR);
    }

    public static void formatPrint(String line){
        System.out.println("\t"+SEPARATOR);
        System.out.println("\t"+line);
        System.out.println("\t"+SEPARATOR);
    }
    public static void sayGreeting(){
        String[] toPrint = new String[]{
                "Hello! I'm " + BOT_NAME,
                "What can I do for you?"
        };
        formatPrint(toPrint);
    }

    public static void sayBye(){
        formatPrint("Bye. Hope to see you again soon!");
    }

    public static boolean handleCommand(String command){
        String[] parsedCommand = parseCommand(command);
        switch(parsedCommand[0].toLowerCase()){
        case "bye":
            sayBye();
            return false;
        case "list":
            tasks.listTasks();
            break;
        case "mark":
            tasks.setTaskIsDone(Integer.parseInt(parsedCommand[1]), true);
            break;
        case "unmark":
            tasks.setTaskIsDone(Integer.parseInt(parsedCommand[1]), false);
            break;
        default:
            tasks.addTask(parsedCommand[0]);
            break;
        }
        return true;
    }

    public static String[] parseCommand(String command){
        String[] commandArray = command.split(" ");
        String arg1 = commandArray[0].toLowerCase();
        if(arg1.equals("mark") || arg1.equals("unmark")){
            return commandArray;
        }
        else{
            return new String[]{command};
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        sayGreeting();
        String command;

        do {
            command = scanner.nextLine();
        } while(handleCommand(command));


    }
}
