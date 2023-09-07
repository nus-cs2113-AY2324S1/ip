import java.util.Scanner;

public class Zran {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________";

    public static void main(String[] args) {
        System.out.printf((outputFormat) + "%n",
                "hello! I'm Zran, your personal assistant:)! \n" +
                "    Type in your to dos for the day and press enter! \n " +
                "   Type 'list' to view your to do list. \n " +
                "   Type 'bye' to end my service. \n" +
                "    Type 'mark' followed by item number to mark item as done. \n" +
                "    Type 'unmark' followed by item number to unmark the item." );
        addItems();
    }

    //The function 'echo' is used to print any output to the user
    public static void echo(String input, Task[] items) {
        if(input.startsWith("mark ")) {
            int itemIndex = Integer.parseInt(input.substring(5)) - 1;
            System.out.printf((outputFormat) + "%n", "Congrats! :D \n    Task marked as done: " +
                    items[itemIndex].description +
                    " [" + items[itemIndex].getStatusIcon() + "]") ;
        } else if(input.startsWith("unmark ")) {
            int itemIndex = Integer.parseInt(input.substring(7)) - 1;
            System.out.printf((outputFormat) + "%n", "Oopsies! \n    Task unmarked: " +
                    items[itemIndex].description +
                    " [" + items[itemIndex].getStatusIcon() + "]") ;
        } else if(!input.equals("list")){
            System.out.printf((outputFormat) + "%n", "New task added: " + input + "\n" +
                    "    Type 'list' to view all your tasks :D");
        } else {
            System.out.println("    ____________________________________________________________");
            int index=0;
            for(Task item : items){
                if((item != null)) {
                    System.out.print("    "+ ++index + ". ");
                    System.out.println(item.toString());
                }
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    //The function 'addItems' is used to accept user's input and add it to their To Do list
    public static void addItems() {
        Scanner in = new Scanner(System.in);
        String input = "";

        Task[] items = new Task[100];
        int index = 0;

        while(!(input = in.nextLine()).equals("bye")){
            if(input.startsWith("mark ")) {
                items[Integer.parseInt(input.substring(5)) - 1].markAsDone();
            } else if(input.startsWith("unmark ")) {
                items[Integer.parseInt(input.substring(7)) - 1].unmarkTask();
            } else if(!input.equals("list")){
                Task task = addTaskByType(input);
                items[index++] = task;
            }
            echo(input, items);
        }

        System.out.printf((outputFormat) + "%n", "Goodbye <3 have a great day ahead!");
    }

    // The function 'addTaskByType' is used to add an item into the task list by their task type
    public static Task addTaskByType(String input) {
        Task task = null;
        char taskType = input.charAt(0);
        switch (taskType) {
        case 't':
            task = new ToDos(input.substring(5));
            break;
        case 'd':
            int byIndex = input.indexOf("/by");
            String description = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();
            task = new Deadline(description, by);
            break;
        case 'e':
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String descriptionE = input.substring(5, fromIndex).trim();
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            task = new Event(descriptionE, from, to);
            break;
        }
        return task;
    }

}
