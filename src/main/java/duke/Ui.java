package duke;

public class Ui {

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void printGreeting() {
        String logo = "______       _     _\n"
                + "| ___ \\     | |   | |\n"
                + "| |_/ / ___ | |__ | |__  _   _\n"
                + "| ___ \\/ _ \\| '_ \\| '_ \\| | | |\n"
                + "| |_/ | (_) | |_) | |_) | |_| |\n"
                + "\\____/ \\___/|_.__/|_.__/ \\__, |\n"
                + "                          __/ |\n"
                + "                         |___/";
        System.out.println("    Hello from\n" + logo);
        printLine();
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        printLine();
    }

    public void printBye(){
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    public void printIndentTask(){
        System.out.print("      ");
    }

    public void printMarked(){
        System.out.println("    Nice! I've marked this task as done:");
    }

    public void printUnmarked(){
        System.out.println("    OK, I've marked this task as not done yet:");
    }

    public void echo(String text){
        System.out.print(text);
    }

}
