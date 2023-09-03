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
        Task[] tasks = new Task[100];
        int taskCount = 0;
        do {
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
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        if (tasks[i].getIsDone() == true) {
                            System.out.println("\t" + (i + 1) + ". [X] " + tasks[i].getTaskName());
                        } else {
                            System.out.println("\t" + (i + 1) + ". [ ] " + tasks[i].getTaskName());
                        }
                    }
                }
                printSeperatorLine();
                continue;
            } else if (inputWords[0].equals("unmark")) { // mark task as done
                int taskNumber = Integer.parseInt(line.substring(7));
                printSeperatorLine();
                tasks[taskNumber - 1].markAsUndone();
                printSeperatorLine();
                continue;
            } else if (inputWords[0].equals("mark")) { // mark task as done
                int taskNumber = Integer.parseInt(line.substring(5));
                printSeperatorLine();
                tasks[taskNumber - 1].markAsDone();
                printSeperatorLine();
                continue;
            } else { // add task
                printSeperatorLine();
                System.out.println("\tadded: " + line);
                printSeperatorLine();
                tasks[taskCount] = new Task(line);
                taskCount++;
            }
        } while (!line.equals("bye"));
    }
}
