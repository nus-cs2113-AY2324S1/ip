import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "  ____  _____   ___    _____ _____ __    __   ___   __   __\n"
            + "/     /|  __ \\ /   \\  /   __|     |  \\  /  | /   \\ |  \\ |  |\n"
            + "\\   __\\| |__) |  _  \\|   /  |   __|   \\/   |/  _  \\|   \\|  |\n"
            + " \\__   |  ___/  |_|  |  |   |   __|        |  |_|  |       |\n"
            + "/      | |   |   _   |   \\__|     |   __   |   _   |       |\n"
            + "|____ /|_|   |__| |__|\\_____|_____|__|  |__|__| |__|__|\\___|\n";

        String[] itemList = new String[100];
        int count = 0;

        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("Hello! I'm Spaceman");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                printList(itemList, count);
            } else{
                addToList(itemList, text, count);
                count++;
            }
            text = sc.nextLine();
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------");
    }

    public static void addToList(String[] itemList, String item, int count){
        itemList[count] = item;
        System.out.println("------------------------------------------------------------");
        System.out.println("added: " + item);
        System.out.println("------------------------------------------------------------");
    }

    public static void printList(String[] items, int count){
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < count; i++){
            System.out.println(i + 1 + ". " + items[i]);
        }
        System.out.println("------------------------------------------------------------");
    }

}
