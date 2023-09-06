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

        Input input = new Input();
        Tasklist tasks = new Tasklist();

        while (!input.getInput().equals("bye")) {
            if (input.getCommand().equals("bye")) { // exit
                printSeperatorLine();
                System.out.println("\tFarewell, my friend! Until our laughter intertwines again");
                printSeperatorLine();
                break;
            } else if (input.getCommand().equals("todo")) { // add todo task
                String taskName = input.getLine().substring(5);
                tasks.addTask(new Todo(taskName));

                printSeperatorLine();
                System.out.println("\tBehold, a new endeavor enters the realm: " + taskName);
                System.out.println("\tThe grand tally of tasks has reached a harmonious count of " + tasks.getTaskListSize() + " in all.");
                printSeperatorLine();
                continue;
            } else if (input.getCommand().equals("deadline")) { // add deadline task
                String taskName = input.getLine().substring(9, input.getLine().indexOf("/by") - 1);
                String deadline = input.getLine().substring(input.getLine().indexOf("/by") + 4);
                tasks.addTask(new Deadline(taskName, deadline));

                printSeperatorLine();
                System.out.println("\tBehold, a new endeavor enters the realm: " + taskName);
                System.out.println("\tThe grand tally of tasks has reached a harmonious count of " + tasks.getTaskListSize() + " in all.");
                printSeperatorLine();
                continue;
            } else if (input.getCommand().equals("event")) { // add event task
                String taskName = input.getLine().substring(6, input.getLine().indexOf("/from") - 1);
                String startTime = input.getLine().substring(input.getLine().indexOf("/from") + 6, input.getLine().indexOf("/to") - 1);
                String endTime = input.getLine().substring(input.getLine().indexOf("/to") + 4);
                tasks.addTask(new Event(taskName, startTime, endTime));

                printSeperatorLine();
                System.out.println("\tBehold, a new endeavor enters the realm: " + taskName);
                System.out.println("\tThe grand tally of tasks has reached a harmonious count of " + tasks.getTaskListSize() + " in all.");
                printSeperatorLine();
                continue;
            } else if (input.getCommand().equals("list")) { // list tasks
                printSeperatorLine();
                System.out.println("\tAllow me to unveil the tasks dwelling within your list:");
                tasks.printTaskList();
                printSeperatorLine();
                continue;
            } else if (input.getCommand().equals("unmark")) { // mark task as done
                int taskNumber = Integer.parseInt(input.getLine().substring(7));
                tasks.markTaskAsUndone(taskNumber);
                printSeperatorLine();
                System.out.println("\tAh, chuckles! I've playfully returned this task to its untamed state:");
                tasks.getTask(taskNumber).printTask();
                printSeperatorLine();
                continue;
            } else if (input.getCommand().equals("mark")) { // mark task as done
                int taskNumber = Integer.parseInt(input.getLine().substring(5));
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
