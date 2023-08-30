import java.util.Scanner;

public class Duke {
    private static boolean isFinished = false;

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

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {
            exit();
        }

        while (!isFinished) {
            System.out.println(line);
            System.out.println("\uD83C\uDF4B \n");
            line = in.nextLine();
            if (line.equals("bye")) {
                exit();
            }
        }
    }
}
