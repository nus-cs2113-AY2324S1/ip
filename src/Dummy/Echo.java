import elvis.Elvis;

import java.util.Scanner;

public class Echo {

    public static void shoutEcho() {
        String line;
        Scanner in = new Scanner(System.in);

        Elvis.bootUp();
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
            Elvis.printHorizontalLines();
            System.out.println(line);
            Elvis.printHorizontalLines();
        }
        Elvis.shutDown();
    }
}
