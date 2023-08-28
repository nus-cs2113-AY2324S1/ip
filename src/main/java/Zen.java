import java.util.Scanner;

public class Zen {
    public static void main(String[] args) {
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
        System.out.println("    Greetings, dear traveler! I am ZEN JESTER");
        System.out.println("    How may I bring mirth to your day?");
        System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
        String line = "";
        String[] tasks = new String[100];
        int taskCount = 0;
        do {
            line = input.nextLine();
            if (line.equals("bye")) { // exit
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                System.out.println("    Farewell, my friend! Until our laughter intertwines again");
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                break;
            } else if (line.equals("list")) { // list tasks
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println("    " + (i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                continue;
            } else { // add task
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                System.out.println("    added: " + line);
                System.out.println(System.lineSeparator() + "   -------------------------------------------------------------");
                tasks[taskCount] = line;
                taskCount++;
            }
        } while (!line.equals("bye"));
    }
}
