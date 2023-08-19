import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String chatbotName="Andrew Tate";
    static ArrayList<String> tasks = new ArrayList<String>();

    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true){
            String commandGiven = myScanner.nextLine();
            if (commandGiven.equals("bye")) {
                break;
            }else if(commandGiven.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i=0;i<tasks.size(); i++){
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            }else{
                tasks.add(commandGiven);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + commandGiven);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}


