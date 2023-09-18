package dummy;

import elvis.Elvis;
import elvis.SystemOperation;
import java.util.Scanner;

//This was for WEEK 1, Code is not used
public class Echo {

    public static void shoutEcho() {
        String line;
        Scanner in = new Scanner(System.in);

        SystemOperation.bootUpOne();
        SystemOperation.bootUpTwo();
        while (true) {
            String inputBuffer = in.nextLine().trim();
            Scanner bufferScanner = new Scanner(inputBuffer);
            if (!bufferScanner.hasNext()) {
                System.out.println("Please input a valid input");
                continue;
            } else {
                line = inputBuffer;
            }

            if (line.contains("bye")) {
                break;
            }
            SystemOperation.printHorizontalLines();
            System.out.println(line);
            SystemOperation.printHorizontalLines();
        }
        SystemOperation.shutDown();
    }
}
