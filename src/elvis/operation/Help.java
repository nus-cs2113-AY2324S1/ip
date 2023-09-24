package elvis.operation;

//This is the help page to be displayed
public class Help {
    public static void helper() {
        String helpPage =
                "\n\n" +
                "---------------------------HELP PAGE----------------------------\n\n"+
                "1. To add a todo, type \"todo + {description}\"\n" +
                "Example: todo borrow book\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][ ] borrow book\n" +
                "     Now you have 5 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "2. To list out all the tasks, type \"list\"\n" +
                "Example: list\n" +
                "    ____________________________________________________________\n" +
                "     Here are the tasks in your list:\n" +
                "     1.[T][X] read book\n" +
                "     2.[D][ ] return book (by: June 6th)\n" +
                "     3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)\n" +
                "     4.[T][X] join sports club\n" +
                "     5.[T][ ] borrow book\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "3. To add a deadline, type \"deadline + {description} + /by + {when}\"\n" +
                "Example: deadline return book /by Sunday\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [D][ ] return book (by: Sunday)\n" +
                "     Now you have 6 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "\n" +
                "4. To add an event, type \"deadline + {description} + /from + {when} + /to {when}\"\n" +
                "Example: event project meeting /from Mon 2pm /to 4pm\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [E][ ] project meeting (from: Mon 2pm to: 4pm)\n" +
                "     Now you have 7 tasks in the list.\n" +
                "    ____________________________________________________________\n\n" +
                "\n" +
                "5. To delete a task, type \"delete + {task number}\"\n" +
                "Example: delete 1\n" +
                "    ____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       [E][ ] project meeting (from: Mon 2pm to: 4pm)\n" +
                "     Now you have 6 tasks in the list.\n" +
                "    ____________________________________________________________\n\n" +

                "-------------------------HELP PAGE END--------------------------\n";
        System.out.println(helpPage);
    }
}
