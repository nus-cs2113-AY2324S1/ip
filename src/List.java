import java.util.Scanner;

public class List {
    public static void makeList() {
        String[] list = new String[100];
        int listCount = 0;
        Scanner in = new Scanner(System.in);

        Elvis.bootUp();
        while (true) {
            String inputBuffer = in.nextLine().trim();
            Scanner bufferScanner = new Scanner(inputBuffer);
            if (!bufferScanner.hasNext()) {
                System.out.println("Please input a valid input");
                continue;
            } else {
                list[listCount] = inputBuffer;
            }

            if (list[listCount].contains("bye")) {
                break;
            } else if (list[listCount].contains("list")) {
                Elvis.printHorizontalLines();
                for (int i = 0; i < listCount; i++) {
                    System.out.println(i+1 + ". " + list[i]);
                }
                Elvis.printHorizontalLines();
            } else {
                Elvis.printHorizontalLines();
                System.out.println("added: " + list[listCount]);
                Elvis.printHorizontalLines();
                listCount++;
            }
        }
        Elvis.shutDown();
        return;
    }
}