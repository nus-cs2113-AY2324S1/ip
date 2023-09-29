package elvis.operation;
/**
 * This is the help page to be displayed
 */
public class Help {
    /**
     * Counts how many times simpleHelper was called
     */
    private static int simpleHelperCounter = 0;

    /**
     * Prints out help page
     */
    public static void helper() {
        String helpPage =
                "\n\n" +
                "---------------------------HELP PAGE----------------------------\n\n"+
                "1. To add a todo, type \"todo + {description}\"\n" +
                "     Example: todo borrow book\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][ ] borrow book\n" +
                "     Now you have 5 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "2. To list out all the tasks, type \"list\"\n" +
                "     Example: list\n" +
                "    ____________________________________________________________\n" +
                "     Here are the tasks in your list:\n" +
                "     1.[T][X] read book\n" +
                "     2.[D][ ] return book (by: June 6th)\n" +
                "     3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)\n" +
                "     4.[T][X] join sports club\n" +
                "     5.[T][ ] borrow book\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "3. To add a deadline, type \"deadline + {description} + /by + {date time}\"\n" +
                "     Example: deadline return book /by Sunday\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [D][ ] return book (by: 2023-09-23 18:00)\n" +
                "     Now you have 6 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "4. To add an event, type \"deadline + {description} + /from + {date time} + /to {date time}\"\n" +
                "     Example: event project meeting /from Mon 2pm /to 4pm\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [E][ ] project meeting (from: 2023-09-23 18:00 to: 2023-09-23 19:00)\n" +
                "     Now you have 7 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "5. {date time} should be written in this format:\n" +
                "    ____________________________________________________________\n" +
                "     Date: dd/mm/yyyy OR yyyy-mm-dd\n" +
                "     Example: 09/11/2023 OR 2023-11-09\n" +
                "     NOTE: There should be a spacing between the date and time\n" +
                "     Time: HHmm OR HH:mm in 24-Hr format\n" +
                "     Example: 1800 OR 18:00\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "6. To delete a task, type \"delete + {task number}\"\n" +
                "     Example: delete 1\n" +
                "    ____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       [E][ ] project meeting (from: Mon 2pm to: 4pm)\n" +
                "     Now you have 6 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "-------------------------HELP PAGE END--------------------------\n";
        System.out.println(helpPage);
        simpleHelperCounter = 0;
    }

    public static void simpleHelper() {
        String simpleHelp =
                "\n" +
                "Please type \"help\" if you need help" +
                "\n";
        String seriousHelp =
                "\n" +
                "😭 No seriously, get some \"help\"" +
                "\n";

        if (simpleHelperCounter == 0) {
            System.out.println(simpleHelp);
        } else {
            System.out.println(seriousHelp);
        }
        simpleHelperCounter++;
    }
}
