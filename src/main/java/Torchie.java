import java.util.Arrays;
import java.util.Scanner;
public class Torchie {

    private static Task[] taskStore = new Task[100];
    private static int taskStoreSpace = 0; // keep track of next null space in array
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Torchie!");
        System.out.println("What can I do for you?");
        System.out.println("Let's play storetorchie today! You say something and I ll store it!");

        String input;
        do {
            input = scanner.nextLine();
            String firstWord = input.split(" ")[0];

            switch(firstWord) {
            case "list":
                showList(taskStore);
                break;
            case "mark":
                taskStore = markItem(taskStore, Integer.parseInt(input.split(" ")[1]));
                break;
            case "unmark":
                taskStore = unmarkItem(taskStore, Integer.parseInt(input.split(" ")[1]));
                break;
            case "bye":
                System.out.println("Awww bye :( Let's play again soon!");
                break;
            default:
                Task t = new Task(input);
                setTaskStore(t);
                System.out.println("added: " + input);
            }

        } while (!input.equals("bye"));

    }

    public static Task[] getTaskStore() {
        return taskStore;
    }

    public static void setTaskStore(Task t) {
        taskStore[taskStoreSpace] = t;
        taskStoreSpace += 1;
    }

    public static void showList(Task[] list){
        System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<list.length; i++){
            if (list[i] == null){
                // only print valid tasks
                break;
            }
            System.out.println(( (i+1) + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription()));
        }
    }

    public static Task[] markItem(Task[] list, int itemNum){
        int index = itemNum - 1; // obtain index of item to mark
        list[index].setDone(true);
        System.out.println("Nice! I've marked this task as done: ");
        list[index].printTask();
        return list;
    }

    public static Task[] unmarkItem(Task[] list, int itemNum){
        int index = itemNum - 1; // obtain index of item to unmark
        list[index].setDone(false);
        System.out.println("Ok, I've marked this task as not done yet: ");
        list[index].printTask();
        return list;
    }


}
