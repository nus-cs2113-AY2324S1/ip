package dummy;

import elvis.Elvis;
import elvis.SystemOperation;
import java.util.Scanner;

//This was for WEEK 1, Code is not used
public class List {
    public static void makeList() {
        String[] list = new String[100];
        int listCount = 0;
        Scanner in = new Scanner(System.in);

        SystemOperation.bootUp();
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
                SystemOperation.printHorizontalLines();
                for (int i = 0; i < listCount; i++) {
                    System.out.println(i+1 + ". " + list[i]);
                }
                SystemOperation.printHorizontalLines();
            } else {
                SystemOperation.printHorizontalLines();
                System.out.println("added: " + list[listCount]);
                SystemOperation.printHorizontalLines();
                listCount++;
            }
        }
        SystemOperation.shutDown();
    }
}