import java.util.Scanner;

public class List {
    public static void List() {
        String[] list = new String[100];
        int listCount = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            list[listCount] = in.nextLine().trim();
            if (list[listCount].contains("bye")) {
                break;
            } else if (list[listCount].contains("list")) {
                Duke.printHorizontalLines();
                for (int i = 0; i < listCount; i++) {
                    System.out.println(i+1 + ". " + list[i]);
                }
                Duke.printHorizontalLines();
            } else {
                Duke.printHorizontalLines();
                System.out.println("added: " + list[listCount]);
                Duke.printHorizontalLines();
                listCount++;
            }
        }

        return;
    }
}


