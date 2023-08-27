import java.util.Scanner;

public class Duke {

    public static void echoInput(){


        String input = "";
        Scanner in = new Scanner(System.in);

        input = in.nextLine();

        while(!(input.equals("bye"))){

            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________\n");

            input = in.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");



    }



    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magpie");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");


        echoInput();

    }

}
