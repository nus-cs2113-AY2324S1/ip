import java.util.Scanner;

public class Duke {

    final static String BOT_NAME= "Elgin";
    final static String SEPARATOR = "____________________________________________________________";

    public static void formatPrint(String[] lines){
        System.out.println("\t"+SEPARATOR);
        for(String line:lines){
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
        formatPrint(new String[]{"Hello! I'm " + BOT_NAME, "What can I do for you?"});
    }

    public static void sayBye(){
        formatPrint("Bye. Hope to see you again soon!");
    }

    public static boolean handleCommand(String command){
        switch(command.toLowerCase()){
        case "bye":
            sayBye();
            return false;
        default:
            formatPrint(command);
        }
        return true;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        sayGreeting();
        String command;

        do {
            command= scanner.nextLine();
        } while(handleCommand(command));

    }
}
