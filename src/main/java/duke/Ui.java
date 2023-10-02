    package duke;

    import java.util.Scanner;

    public class Ui {
        private final Scanner scanner;

        public Ui() {
            this.scanner = new Scanner(System.in);
        }

        public String readCommand() {
            return scanner.nextLine();
        }

        public void printWelcomeMessage() {
            System.out.println("____________________________________________________________");
            System.out.println("Hello! I'm");
            System.out.println(" ________  ________  ________  ________     \n" +
                    "|\\   ____\\|\\   __  \\|\\   __  \\|\\   __  \\    \n" +
                    "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\   \n" +
                    " \\ \\  \\    \\ \\   __  \\ \\   _  _\\ \\   __  \\  \n" +
                    "  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \n" +
                    "   \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\__\\ \\__\\\n" +
                    "    \\|_______|\\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\n" +
                    "                                            ");
            System.out.println("What can I do for you?");
        }

        public void printByeMessage() {
            System.out.println("Bye! Hope to see you again soon!");
            System.out.println("____________________________________________________________");
        }

        public void showLoadingError() throws DukeException {
            throw new DukeException("Error reading from file. Unable to load tasks.");
        }


        public void showLine() {
            System.out.println("____________________________________________________________");
        }

        public void showError(String errorMessage) {
            System.out.println("☹ OOPS!!! " + errorMessage);
        }


    }
