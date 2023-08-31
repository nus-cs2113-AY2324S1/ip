import java.util.Scanner;

/**
 * Task class
 * including description and check whether it has been completed
 */
class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String getDescription(){
        return description;
    }

    public void doMark(){
        isDone = true;
    }

    public void unMark(){
        isDone = false;
    }

}

public class Duke {
    /* print a line starts with four spaces*/
    static void printLine(String s){
        System.out.println("     " + s);
    }

    /* simple function that returns if the string is numeric*/
    static boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int tasks_size = 0;

        System.out.println("    ____________________________________________________________");
        printLine("Hello! I'm Nupjuk");
        printLine("What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        while(true){ // get input until bye
            String cmd = input.nextLine();
            String[] tokens = cmd.split(" ", 2);
            System.out.println("    ____________________________________________________________");
            //System.out.println(tokens[0]);
            if(cmd.equals("bye")){ // bye command
                break;
            }
            else if(cmd.equals("list")){
                if(tasks_size == 0){
                    printLine("Nothing in the list");
                }
                else{
                    for(int i=0;i<tasks_size;i++){
                        printLine(String.format("%d.[", i+1) + tasks[i].getStatusIcon() + "] "  + tasks[i].getDescription());
                    }
                }
                System.out.println("    ____________________________________________________________\n");
            }
            else if(tokens[0].equals("mark")){

                // error handling
                if(tokens.length !=2 || !isNumeric(tokens[1])){
                    printLine("unexpected behavior: 'mark' needs one integer parameter");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                printLine("Nice! I've marked this task as done:");
                int mark_idx = Integer.parseInt(tokens[1])-1; // index starts from 0

                // if idx not in proper range
                if(mark_idx <0 || mark_idx >= tasks_size){
                    printLine("unexpected behavior: index out of range");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }
                // do mark
                tasks[mark_idx].doMark();
                printLine(" [" + tasks[mark_idx].getStatusIcon() + "] " + tasks[mark_idx].getDescription());
                System.out.println("    ____________________________________________________________\n");
            }
            else if(tokens[0].equals("unmark")){
                if(tokens.length !=2 || !isNumeric(tokens[1])){
                    printLine("unexpected behavior: 'mark' needs one integer parameter");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                printLine("OK, I've marked this task as not done yet:");
                int mark_idx = Integer.parseInt(tokens[1])-1; // index starts from 0

                // if idx not in proper range
                if(mark_idx <0 || mark_idx >= tasks_size){
                    printLine("unexpected behavior: index out of range");
                    System.out.println("    ____________________________________________________________\n");
                    continue;
                }

                // unmark
                tasks[mark_idx].unMark();
                printLine(" [" + tasks[mark_idx].getStatusIcon() + "] " + tasks[mark_idx].getDescription());
                System.out.println("    ____________________________________________________________\n");
            }
            else{
                printLine("added: " + cmd); // add list
                tasks[tasks_size++] = new Task(cmd);
                System.out.println("    ____________________________________________________________\n");
            }
        }

        // exit the program
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
