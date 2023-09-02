import java.util.Scanner;

public class Zen {

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
        System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
    }
    public static void main(String[] args) {
        printInitializeTitle();
        System.out.println("    Greetings, dear traveler! I am ZEN JESTER");
        System.out.println("    How may I bring mirth to your day?");
        System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
        String line = "";
        Task[] tasks = new Task[100];
        int taskCount = 0;
        do {
            line = input.nextLine();
            String[] inputWords = line.split(" ");
            if (inputWords[0].equals("bye")) { // exit
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                System.out.println("    Farewell, my friend! Until our laughter intertwines again");
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                break;
            } else if (line.equals("list")) { // list tasks
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        if (tasks[i].getIsDone() == true) {
                            System.out.println("    " + (i + 1) + ". [X] " + tasks[i].getTaskName());
                        } else {
                            System.out.println("    " + (i + 1) + ". [ ] " + tasks[i].getTaskName());
                        }
                    }
                }
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                continue;
            } else if (line.contains("unmark")) { // mark task as done
                int taskNumber = Integer.parseInt(line.substring(7));
                tasks[taskNumber - 1].setIsDone(false);
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                System.out.println("    Nice! I've marked this task as undone:");
                System.out.println("      [ ] " + tasks[taskNumber - 1].getTaskName());
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                continue;
            } else if (line.contains("mark")) { // mark task as done
                int taskNumber = Integer.parseInt(line.substring(5));
                tasks[taskNumber - 1].setIsDone(true);
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      [X] " + tasks[taskNumber - 1].getTaskName());
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                continue;
            } else { // add task
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                System.out.println("    added: " + line);
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                tasks[taskCount] = new Task(line);
                taskCount++;
            }
        } while (!line.equals("bye"));
    }
}
