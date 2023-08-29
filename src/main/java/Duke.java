import java.util.Scanner;

public class Duke {

    static String lineDivider = "    _____________________________________";

    public static void echo(String in) {
        System.out.println(lineDivider + "\n"
        + in
        + "\n" + lineDivider);
    }

    public static void addItem(String in, Task[] itemList, int i) {
        itemList[i] = new Task(in);

        System.out.println(lineDivider + "\n"
        + "added: " + in
        + "\n" + lineDivider);
    }

    public static void listItems(Task[] itemList, int i) {
        System.out.println(lineDivider);
        for(int j = 0; j < i; j += 1) {
            System.out.println((j + 1)
                    + ". [" + (itemList[j].getDone() ? "X" : "") + "]"
                    + itemList[j].getName());
        }
        System.out.println(lineDivider);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Axel!\n"
                + "What can I do for you?\n"
                + "    _____________________________________");

        Scanner in = new Scanner(System.in);
        String buf = in.nextLine();
        Task[] itemList = new Task[100];
        int i = 0; //current index in itemlist

        while(!buf.toLowerCase().equals("bye")){
            switch (buf.toLowerCase()) {

                case "list":
                    listItems(itemList, i);
                    break;

                default:
                    addItem(buf, itemList, i);
                    i += 1;
                    break;
            }
            buf = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n"
                + "    _____________________________________");
    }
}

