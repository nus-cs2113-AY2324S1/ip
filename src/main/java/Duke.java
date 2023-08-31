import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Nupjuk");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        while(true){ // get input until bye
            String cmd = input.nextLine();
            System.out.println("     ____________________________________________________________");
            if(cmd.equals("bye")){ // bye command
                break;
            }
            else{
                System.out.println("      " + cmd); // echo
                System.out.println("     ____________________________________________________________\n");
            }
        }

        // exit the program
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("     ____________________________________________________________\n");
    }
}
