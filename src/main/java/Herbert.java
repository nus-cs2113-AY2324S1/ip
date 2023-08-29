import java.util.ArrayList;

public class Herbert {

    private ArrayList<String> history;

    public Herbert() {
        this.history = new ArrayList<>();
        this.sayHello();
    }

    public void sayHello() {
        String logo = "(   )                           (   )                          (   )       \n"
                + " | | .-.     .--.    ___ .-.     | |.-.     .--.    ___ .-.     | |_       \n"
                + " | |/   \\   /    \\  (   )   \\    | /   \\   /    \\  (   )   \\   (   __)     \n"
                + " |  .-. .  |  .-. ;  | ' .-. ;   |  .-. | |  .-. ;  | ' .-. ;   | |        \n"
                + " | |  | |  |  | | |  |  / (___)  | |  | | |  | | |  |  / (___)  | | ___    \n"
                + " | |  | |  |  |/  |  | |         | |  | | |  |/  |  | |         | |(   )   \n"
                + " | |  | |  |  ' _.'  | |         | |  | | |  ' _.'  | |         | | | |    \n"
                + " | |  | |  |  .'.-.  | |         | '  | | |  .'.-.  | |         | ' | |    \n"
                + " | |  | |  '  `-' /  | |         ' `-' ;  '  `-' /  | |         ' `-' ;    \n"
                + "(___)(___)  `.__.'  (___)         `.__.    `.__.'  (___)         `.__.     \n";


        System.out.println(System.lineSeparator() + logo);
        System.out.println("___________________________________________________________________________");
        System.out.println("\tHello! I'm Herbert.\n\tWhat can I do for you?");
        System.out.println("___________________________________________________________________________");
    }

    public void sayGoodbye() {
        System.out.println("___________________________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________________");
    }

    public int processLine(String line) {
        if (line.equalsIgnoreCase("bye")) {
            sayGoodbye();
            return 1;
        } else if (line.equalsIgnoreCase("list")) {
            list();
        } else {
            this.history.add(line);
            System.out.println("___________________________________________________________________________");
            System.out.println("\tAdded: " + line);
            System.out.println("___________________________________________________________________________");
        }

        return 0;
    }

    public void list() {
        System.out.println("___________________________________________________________________________");
        for (int i = 0; i < history.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + history.get(i));
        }
        System.out.println("___________________________________________________________________________");
    }


}

