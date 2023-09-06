import java.util.Scanner;
public class Frank {
    public static void main(String[] args) {

        String solidLine = "\n------------------------------------------------------------------------------------------------------------------------------\n";
        String logo =
                "FFFFFFFFFFFFFFFFFFFFFFRRRRRRRRRRRRRRRRR                  AAA               NNNNNNNN        NNNNNNNNKKKKKKKKK    KKKKKKK" + System.lineSeparator() +
                "F::::::::::::::::::::FR::::::::::::::::R                A:::A              N:::::::N       N::::::NK:::::::K    K:::::K" + System.lineSeparator() +
                "F::::::::::::::::::::FR::::::RRRRRR:::::R              A:::::A             N::::::::N      N::::::NK:::::::K    K:::::K" + System.lineSeparator() +
                "FF::::::FFFFFFFFF::::FRR:::::R     R:::::R            A:::::::A            N:::::::::N     N::::::NK:::::::K   K::::::K" + System.lineSeparator() +
                "F:::::F       FFFFFF  R::::R     R:::::R           A:::::::::A           N::::::::::N    N::::::NKK::::::K  K:::::KKK" + System.lineSeparator() +
                "F:::::F               R::::R     R:::::R          A:::::A:::::A          N:::::::::::N   N::::::N  K:::::K K:::::K" + System.lineSeparator() +
                "F::::::FFFFFFFFFF     R::::RRRRRR:::::R          A:::::A A:::::A         N:::::::N::::N  N::::::N  K::::::K:::::K" + System.lineSeparator() +
                "F:::::::::::::::F     R:::::::::::::RR          A:::::A   A:::::A        N::::::N N::::N N::::::N  K:::::::::::K" + System.lineSeparator() +
                "F:::::::::::::::F     R::::RRRRRR:::::R        A:::::A     A:::::A       N::::::N  N::::N:::::::N  K:::::::::::K" + System.lineSeparator() +
                "F::::::FFFFFFFFFF     R::::R     R:::::R      A:::::AAAAAAAAA:::::A      N::::::N   N:::::::::::N  K::::::K:::::K" + System.lineSeparator() +
                "F:::::F               R::::R     R:::::R     A:::::::::::::::::::::A     N::::::N    N::::::::::N  K:::::K K:::::K" + System.lineSeparator() +
                "F:::::F               R::::R     R:::::R    A:::::AAAAAAAAAAAAA:::::A    N::::::N     N:::::::::NKK::::::K  K:::::KKK" + System.lineSeparator() +
                "FF:::::::FF           RR:::::R     R:::::R   A:::::A             A:::::A   N::::::N      N::::::::NK:::::::K   K::::::K" + System.lineSeparator() +
                "F::::::::FF           R::::::R     R:::::R  A:::::A               A:::::A  N::::::N       N:::::::NK:::::::K    K:::::K" + System.lineSeparator() +
                "F::::::::FF           R::::::R     R:::::R A:::::A                 A:::::A N::::::N        N::::::NK:::::::K    K:::::K" + System.lineSeparator() +
                "FFFFFFFFFFF           RRRRRRRR     RRRRRRRAAAAAAA                   AAAAAAANNNNNNNN         NNNNNNNKKKKKKKKK    KKKKKKK ";

        System.out.println(solidLine + logo + solidLine);
        System.out.println("Hello user, I'm FRANK! Nice to meet you!\n" + solidLine);

        // Add, List
        TaskList user = new TaskList();
        Scanner input = new Scanner(System.in);
        String command, description, startDate, endDate;
        do{
            System.out.println("Available Commands: list, deadline, event, mark <index>, unmark <index>, <Todo> (default), bye");
            command = input.nextLine();
            String[] commands = command.split(" "); // if mark or unmark will be followed by an int
            switch (commands[0]) {
            case "bye":
                System.out.println(solidLine + "\nGoodbye User, zai jian yong ze, sampai jumpa lagi user \n" + solidLine);
                break;
            case "list":
                user.printTasks();
                break;
            case "deadline":
                System.out.println("Bisa! What is the task?");
                description = input.nextLine();
                System.out.println("Ke Yi! When is it due?");
                // Due Date same as StartDate
                startDate = input.nextLine();
                user.addTask(new Deadline(description, startDate));
                break;
            case "event":
                System.out.println("Boleh! What is the event?");
                description = input.nextLine();
                System.out.println("When does it start?");
                startDate = input.nextLine();
                System.out.println("When does it end?");
                endDate = input.nextLine();
                user.addTask(new Event(description, startDate, endDate));
                break;
            case "mark":
                user.markTask(Integer.parseInt(commands[1]),true);
                break;
            case "unmark":
                user.markTask(Integer.parseInt(commands[1]),false);
                break;
            default:
                user.addTask(new Todo(command));
                break;
            }
        } while(!command.equals("bye"));
    }
}
