import java.util.Scanner;

public class ZenBot {

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

            if (line.startsWith("bye")) { // exit
                printSeperatorLine();
                System.out.println("\tFarewell, my friend! Until our laughter intertwines again");
                printSeperatorLine();
                break;
            } else if (line.startsWith("todo")) { // add todo task
                String taskName = line.substring(5);
                tasks.addTask(new Todo(taskName));

                printSeperatorLine();
                System.out.println("\tBehold, a new endeavor enters the realm: " + taskName);
                System.out.println("\tThe grand tally of tasks has reached a harmonious count of " + tasks.getTaskListSize() + " in all.");
                printSeperatorLine();
                continue;
            } else if (line.startsWith("deadline")) { // add deadline task
                String taskName = line.substring(9, line.indexOf("/by") - 1);
                String deadline = line.substring(line.indexOf("/by") + 4);
                tasks.addTask(new Deadline(taskName, deadline));

                printSeperatorLine();
                System.out.println("\tBehold, a new endeavor enters the realm: " + taskName);
                System.out.println("\tThe grand tally of tasks has reached a harmonious count of " + tasks.getTaskListSize() + " in all.");
                printSeperatorLine();
                continue;
            } else if (line.startsWith("event")) { // add event task
                String taskName = line.substring(6, line.indexOf("/from") - 1);
                String startTime = line.substring(line.indexOf("/from") + 6, line.indexOf("/to") - 1);
                String endTime = line.substring(line.indexOf("/to") + 4);
                tasks.addTask(new Event(taskName, startTime, endTime));

                printSeperatorLine();
                System.out.println("\tBehold, a new endeavor enters the realm: " + taskName);
                System.out.println("\tThe grand tally of tasks has reached a harmonious count of " + tasks.getTaskListSize() + " in all.");
                printSeperatorLine();
                continue;
            } else if (line.startsWith("list")) { // list tasks
                printSeperatorLine();
                System.out.println("\tAllow me to unveil the tasks dwelling within your list:");
                tasks.printTaskList();
                printSeperatorLine();
                continue;
            } else if (line.startsWith("unmark")) { // mark task as done
                int taskNumber = Integer.parseInt(line.substring(7));
                tasks.markTaskAsUndone(taskNumber);
                printSeperatorLine();
                System.out.println("\tAh, chuckles! I've playfully returned this task to its untamed state:");
                tasks.getTask(taskNumber).printTask();
                printSeperatorLine();
                continue;
            } else if (line.startsWith("mark")) { // mark task as done
                int taskNumber = Integer.parseInt(line.substring(5));
                tasks.markTaskAsDone(taskNumber);
                printSeperatorLine();
                System.out.println("\tHuzzah! I've adorned this task with the badge of completion:");
                tasks.getTask(taskNumber).printTask();
                printSeperatorLine();
                continue;
            } else {
                System.out.println("\tAh, my apologies, but the riddles of your words elude my jestful grasp.");
            }
        }
    }
}
