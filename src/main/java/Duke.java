import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static final String line = "__________________________________________";
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I'm Chat0PT");
        System.out.println("What can I do for you?");
        System.out.println(line);
        String input;
        ArrayList<Task> currentTask = new ArrayList<>();
        while(true){
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            System.out.println(line);
            switch(input){
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            case "list":
                int i = 1;
                for (Task t: currentTask){
                    System.out.println(i + ": " + t.getTasks());
                    i++;
                }
                System.out.println(line);

            default:
                Task t = new Task(input);
                currentTask.add(t);
                System.out.println("added: " + input);
                System.out.println(line);
            }

        }


    }

}
