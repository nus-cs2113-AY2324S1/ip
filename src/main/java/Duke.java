import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void printTasks(List tasks){

        System.out.println("____________________________________________________________");

        for(int i =0; i< tasks.size();i++){
            System.out.println(i+1+"." + tasks.get(i));
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
            }
            else{
                tasks.add(input);
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

        List<String> tasks = new ArrayList<String>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magpie");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");

        echoInput(tasks);

    }

}
