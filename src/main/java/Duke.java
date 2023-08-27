import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void printTasks(List tasks){

        System.out.println("____________________________________________________________");

        for(int i =0; i< tasks.size();i++){

            Task item = (Task) tasks.get(i);

            System.out.println(i+1+".[" + item.isDone() + "] "  + item.getDescription());
        }

        System.out.println("____________________________________________________________\n");
    }

    public static void echoInput(List tasks){


        String input = "";
        Scanner in = new Scanner(System.in);

        input = in.nextLine();

        while(!(input.equals("bye"))){


            if (input.equals("list")){
                printTasks(tasks);
            } else if (input.contains("unmark") || input.contains("mark")) {
                String[] splitInput = input.split(" ");
                int index = Integer.parseInt(splitInput[1]) - 1;
                Task item = (Task) tasks.get(index);

                System.out.println("____________________________________________________________");

                if (input.contains("unmark")){
                    System.out.print("OK, I've marked this task as not done yet:\n");
                    item.setDone(false);
                }
                else{
                    System.out.print("Nice! I've marked this task as done:\n");
                    item.setDone(true);
                }

                System.out.println("[" + item.isDone() + "] "  + item.getDescription());
                System.out.println("____________________________________________________________\n");

            }

            else{

                Task newTask = new Task(input, false);
                tasks.add(newTask);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________\n");

            }

            input = in.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");



    }



    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magpie");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");

        echoInput(tasks);

    }

}
