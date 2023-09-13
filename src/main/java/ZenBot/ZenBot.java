package zenbot;

import commands.Command;
import commands.CommandParser;
import commands.Farewell;
import exceptions.OutOfRangeException;
import exceptions.TaskEmptyDescriptionException;
import exceptions.UnknownCommandException;
import tasks.Tasklist;

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
    public static void main(String[] args) throws UnknownCommandException, TaskEmptyDescriptionException {
        printInitializeTitle();
        System.out.println("\tGreetings, dear traveler! I am ZEN JESTER");
        System.out.println("\tHow may I bring mirth to your day?");
        printSeperatorLine();

        Input input = new Input();
        Tasklist tasks = new Tasklist();
        CommandParser commandParser = new CommandParser();
        Command nextCommand = null;

        while (!(nextCommand instanceof Farewell)) {
            try {
                nextCommand = commandParser.parse(input.getInput(), tasks);
                nextCommand.execute();
            } catch (UnknownCommandException e) {
                System.out.println("\tUnknown command, please try again");
                continue;
            } catch (TaskEmptyDescriptionException e) {
                System.out.println("\tTask description cannot be empty, please try again");
                continue;
            } catch (OutOfRangeException e) {
                System.out.println("\tTask number is out of range, please try again");
                continue;
            }
        }
    }
}
