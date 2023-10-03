package duke;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Herbert herbert = new Herbert();

        HerbertReader.createSaveFileIfNotExists();
        HerbertReader.loadFromSaveFile(herbert);

        Scanner scan = new Scanner(System.in);

        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            System.out.println();

            int process = herbert.processLine(line);
            if (process == 1) {
                // User has inputted "bye"
                break;
            }
        }
    }

}
