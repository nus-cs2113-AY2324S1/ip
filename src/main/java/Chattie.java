import java.util.Scanner;

public class Chattie {
    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Chattie! How was your day?");
        System.out.println("\t____________________________________________________________");

        line = in.nextLine();
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

        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + line);
            System.out.println("\t____________________________________________________________");
            line = in.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
