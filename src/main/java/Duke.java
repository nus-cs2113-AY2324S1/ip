import java.util.Scanner;

public class Duke {
    private static boolean isFinished = false;

    private static final Scanner in = new Scanner(System.in);

    public static String getInput() {
        String line;
        line = in.nextLine();
        if (line.equals("bye")) {
            exit();
        }
        return line;
    }

    public static void exit() {
        System.out.println("Squeeze the day! Until we meet again, stay fresh and zesty!");
        System.out.println("\uD83C\uDF4B \uD83C\uDF4B \uD83C\uDF4B");
        isFinished = true;
    }

    public static void main(String[] args) {
        String logo = "                      .--:::.                      \n"
                + "                .:-----::::.:-.                    \n"
                + "            .----:::::::::..:=:                    \n"
                + "         .---:::::::::::::::-*.                    \n"
                + "       .=-::::::::::::::::::::=.                   \n"
                + "      :=:::::::::::::::::::::::+.                  \n"
                + "     .=:::::::::::::::::::::::::=                  \n"
                + "     +.:::::::::::::::::::::::::=                  \n"
                + "    -:::::::::::::::::::::::::----:.               \n"
                + "    =:::::::::::::::::::::::-:.   .:---:           \n"
                + "    =:::::::::::::::::::::=* :.:: -  .:.--         \n"
                + "    =::::::::::::::::::::+--..   :::. .-.:=-       \n"
                + "    :-::::::::::::::::::=.:-.:    .:-.--. ::=      \n"
                + "     +:::::::::::::::::=:  + .:::::.-:-....: +     \n"
                + "     --::::::::-:::::::+   .+ -.....--:-..... =    \n"
                + "     :=::::::::::::::::=.   .=:.   .-: :-.  .:=.   \n"
                + "     --::::=--------=----.    --..:-:   :.: .:-.   \n"
                + "      .---=.    ...     =..    .-: .:.  .: .. =    \n"
                + "                        -   .    .-::-:..  .::     \n"
                + "                        .:::::.       .:=+=:       \n"
                + "                              .:::::::-:.          \n"
                + "    ______   ________  ________  ________  ________ \n"
                + "  ╱╱      ╲ ╱        ╲╱        ╲╱        ╲╱    ╱   ╲\n"
                + " ╱╱       ╱╱         ╱         ╱         ╱         ╱\n"
                + "╱        ╱╱        _╱         ╱         ╱         ╱ \n"
                + "╲________╱╲________╱╲__╱__╱__╱╲________╱╲__╱_____╱  \n";

        System.out.println(logo);

        System.out.println("Hey there! I'm Lemon \uD83C\uDF4B");
        System.out.println("How can I add some zest to your day?");
        System.out.println("\uD83C\uDF4B \uD83C\uDF4B \uD83C\uDF4B \n");

        String[] tasks = new String[100];
        int tasksNum = 0;

        String input;
        input = getInput();

        while (!isFinished) {
            if (input.equals("list")) {
                if (tasksNum == 0) {
                    System.out.println("Your list is on a lemonade break right now.");
                } else {
                    int index = 1;
                    for (int i = 0; i < tasksNum; i++) {
                        System.out.println(index + ". " + tasks[index - 1]);
                        index++;
                    }
                }
                System.out.println("\uD83C\uDF4B \n");
                input = getInput();
            } else {

                tasks[tasksNum] = input;
                System.out.println(input + " has been squeezed into your list!");
                System.out.println("\uD83C\uDF4B \n");
                tasksNum++;
                input = getInput();
            }
        }
    }
}
