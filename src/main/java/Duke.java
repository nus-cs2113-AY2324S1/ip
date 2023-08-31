import java.util.Scanner;
public class Duke {
    static void printLine(String s){
        System.out.println("     " + s);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] inputs = new String[100];
        int inputs_size = 0;

        System.out.println("    ____________________________________________________________");
        printLine("Hello! I'm Nupjuk");
        printLine("What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        while(true){ // get input until bye
            String cmd = input.nextLine();
            System.out.println("    ____________________________________________________________");
            if(cmd.equals("bye")){ // bye command
                break;
            }
            else if(cmd.equals("list")){
                if(inputs_size == 0){
                    printLine("Nothing in the list");
                }
                else{
                    for(int i=0;i<inputs_size;i++){
                        printLine(String.format("%d: ", i+1) + inputs[i]);
                    }
                }
                System.out.println("    ____________________________________________________________\n");
            }
            else{
                printLine("added: " + cmd); // add list
                inputs[inputs_size++] = cmd;
                System.out.println("    ____________________________________________________________\n");
            }
        }

        // exit the program
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
