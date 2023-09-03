import java.util.Scanner;

public class Zen {

    public static void printSeperatorLine() {
        System.out.println(System.lineSeparator() + "\t-------------------------------------------------------------");
    }

    public static void printEnclosedText() {
        // To be implemented, after I figure out how to use higher order function in java
    }

    public static void printInitializeTitle() {
        System.out.println(System.lineSeparator() + "        ,----,                   ,--.                 ,---._                             ,/   .`|                     ");
        System.out.println("      .'   .`|    ,---,.       ,--.'|               .-- -.' \\     ,---,.  .--.--.      ,`   .'  :   ,---,.,-.----.    ");
        System.out.println("   .'   .'   ;  ,'  .' |   ,--,:  : |               |    |   :  ,'  .' | /  /    '.  ;    ;     / ,'  .' |\\    /  \\   ");
        System.out.println(" ,---, '    .',---.'   |,`--.'`|  ' :               :    ;   |,---.'   ||  :  /`. /.'___,/    ,',---.'   |;   :    \\  ");
        System.out.println(" |   :     ./ |   |   .'|   :  :  | |               :        ||   |   .';  |  |--` |    :     | |   |   .'|   | .\\ :  ");
        System.out.println(" ;   | .'  /  :   :  |-,:   |   \\ | :               |    :   ::   :  |-,|  :  ;_   ;    |.';  ; :   :  |-,.   : |: |  ");
        System.out.println(" `---' /  ;   :   |  ;/||   : '  '; |               :         :   |  ;/| \\  \\    `.`----'  |  | :   |  ;/||   |  \\ :  ");
        System.out.println("   /  ;  /    |   :   .''   ' ;.    ;               |    ;   ||   :   .'  `----.   \\   '   :  ; |   :   .'|   : .  /  ");
        System.out.println("  ;  /  /--,  |   |  |-,|   | | \\   |           ___ l         |   |  |-,  __ \\  \\  |   |   |  ' |   |  |-,;   | |  \\  ");
        System.out.println(" /  /  / .`|  '   :  ;/|'   : |  ; .'         /    /\\    J   :'   :  ;/| /  /`--'  /   '   :  | '   :  ;/||   | ;\\  \\ ");
        System.out.println("./__;       : |   |    \\|   | '`--'          /  ../  `..-    ,|   |    \\'--'.     /    ;   |.'  |   |    \\:   ' | \\.' ");
        System.out.println("|   :     .'  |   :   .''   : |              \\    \\         ; |   :   .'  `--'---'     '---'    |   :   .':   : :-'   ");
        System.out.println(";   |  .'     |   | ,'  ;   |.'               \\    \\      ,'  |   | ,'                          |   | ,'  |   |.'     ");
        System.out.println("`---'         `----'    '---'                  ---....--'    `----'                            `----'    `---'       ");
        printSeperatorLine();
    }
    public static void main(String[] args) {
        printInitializeTitle();
        System.out.println("\tGreetings, dear traveler! I am ZEN JESTER");
        System.out.println("\tHow may I bring mirth to your day?");
        printSeperatorLine();

        Scanner input = new Scanner(System.in);
        String line = "";
        Tasklist tasks = new Tasklist();

        while (!line.equals("bye")) {
            line = input.nextLine();
            String[] inputWords = line.split(" ");

            if (inputWords[0].equals("bye")) { // exit
                printSeperatorLine();
                System.out.println("\tFarewell, my friend! Until our laughter intertwines again");
                printSeperatorLine();
                break;
            } else if (inputWords[0].equals("list")) { // list tasks
                printSeperatorLine();
                System.out.println("\tHere are the tasks in your list:");
                tasks.printTaskList();
                printSeperatorLine();
                continue;
            } else if (inputWords[0].equals("unmark")) { // mark task as done
                int taskNumber = Integer.parseInt(line.substring(7));
                printSeperatorLine();
                System.out.println("\tNice! I've marked this task as undone:");
                printSeperatorLine();
                tasks.markTaskAsUndone(taskNumber);
                continue;
            } else if (inputWords[0].equals("mark")) { // mark task as done
                int taskNumber = Integer.parseInt(line.substring(5));
                printSeperatorLine();
                System.out.println("\tNice! I've marked this task as done:");
                printSeperatorLine();
                tasks.markTaskAsDone(taskNumber);
                continue;
            } else { // add task
                printSeperatorLine();
                System.out.println("\tadded: " + line);
                printSeperatorLine();
                tasks.addTask(new Task(line));
            }
        }
    }
}
