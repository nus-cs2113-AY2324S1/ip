import java.util.Scanner;

public class Chattie {

    public static void chattieGreeting(String line) {
        line = line.toLowerCase();

        System.out.println("\t____________________________________________________________");
        if(line.contains("good")) {
            System.out.println("\tGreat to hear that! :D");
        } else if (line.contains("bad")) {
            System.out.println("\tI'm sorry to hear that :(");
        } else {
            System.out.println("\tSounds like you had quite a day");
        }
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public static void chattieList(String[] list, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("\t" + (i+1) + ". " + list[i]);
        }
    }
    public static void main(String[] args) {

        int count = 0;
        String[] list = new String[100];
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Chattie! How was your day?");
        System.out.println("\t____________________________________________________________");

        line = in.nextLine();
        chattieGreeting(line);

        line = in.nextLine();

        while(!line.equals("bye")) {
            System.out.println("\t____________________________________________________________");

            if(line.equals("list")) {
                chattieList(list, count);
            } else {
                list[count] = line;
                count++;
                System.out.println("\tadded: " + line);
            }

            System.out.println("\t____________________________________________________________");
            line = in.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tByeeeee. Hope to see you again soon! :)");
        System.out.println("\t____________________________________________________________");
    }
}
