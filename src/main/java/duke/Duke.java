package duke;

import duke.task.TaskList;

import java.util.Scanner;

public class Duke {
    static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Ui.intro(); //prints the intro

        Scanner scanner = new Scanner(System.in);
        Storage.restoreMemory(); //reload the list from previous iterations of running the program

        while (true) { //continuous loop unless "bye" is input
            String input = scanner.nextLine();
            String[] command = Parser.parseCommand(input);

            if (command[0].equals("bye")) { //exit when "bye"
                Ui.outro();
                break;
            }

            try{ //run main logic unless there is exceptions and print error message based on exceptions
                Ui.mainLogic(taskList, command, input);

            } catch (NoTaskNameException e) {
                Ui.handleNoTaskNameException();
            } catch (StringIndexOutOfBoundsException e) {
                Ui.handleStringIndexOutOfBoundsException(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.handleArrayIndexOutOfBoundsException();
            }
        }

    }


}


