/* */
import java.util.List;
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
                    if (t.isMarked()){
                        System.out.println(i + ". [X] " + t.getTasks());
                    }
                    else{
                        System.out.println(i + ". [ ] " + t.getTasks());
                    }
                    i++;
                }
                System.out.println(line);
                break;
            default:
                String[] splitString = input.split(" ");
                if (splitString.length > 1) {
                    if (splitString[0].equals("mark")) {
                        int num = Integer.parseInt(splitString[1]) - 1;
                        if (num < currentTask.size()) {
                            currentTask.get(num).setMarked(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[X] " + currentTask.get(num).getTasks());
                        } else {
                            System.out.println("Are you sure that task exists?");
                        }
                        System.out.println(line);
                    } else if (splitString[0].equals("unmark")) {
                        int num = Integer.parseInt(splitString[1]) - 1;
                        if (num < currentTask.size()) {
                            currentTask.get(num).setMarked(false);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[ ] " + currentTask.get(num).getTasks());
                        } else {
                            System.out.println("Are you sure that task exists?");
                        }
                        System.out.println(line);
                    }
                    else{
                        Task t = new Task(input);
                        currentTask.add(t);
                        System.out.println("added: " + input);
                        System.out.println(line);
                    }
                }
                else {
                    Task t = new Task(input);
                    currentTask.add(t);
                    System.out.println("added: " + input);
                    System.out.println(line);
                }
            }

        }


    }

}
