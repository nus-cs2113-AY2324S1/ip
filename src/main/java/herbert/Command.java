package herbert;

/**
 * Stores all legal Herbert commands, mostly for documentation purposes.
 * The toString() method has been overridden with descriptive text for each command. This is printed when the
 * `help` command is input by the user into the chatbot.
 */
public enum Command {
    LIST {
        @Override
        public String toString() {
            return "\tList all current tasks."
                    + System.lineSeparator()
                    + "\tUsage: list";
        }
    },
    MARK {
        @Override
        public String toString() {
            return "\tMark a task as completed."
                    + System.lineSeparator()
                    + "\tUsage: mark <task number>";
        }
    },
    UNMARK {
        @Override
        public String toString() {
            return "\tMark a task as incomplete."
                    + System.lineSeparator()
                    + "\tUsage: unmark <task number>";
        }
    },
    DELETE {
        @Override
        public String toString() {
            return "\tDelete a task from your list."
                    + System.lineSeparator()
                    + "\tUsage: delete <task number>";
        }
    },
    TODO {
        @Override
        public String toString() {
            return "\tAdd a new todo to your list of tasks."
                    + System.lineSeparator()
                    + "\tUsage: todo <task description>";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "\tAdd a new deadline to your list of tasks. Due date must be specified in YYYY-MM-DD format."
                    + System.lineSeparator()
                    + "\tUsage: deadline <task description> /by <due date>";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "\tAdd a new event to your list of tasks. Start and end dates must be specified in YYYY-MM-DD format."
                    + System.lineSeparator()
                    + "\tUsage: event <task description> /from <start> /to <end>";
        }
    },
    FIND {
        @Override
        public String toString() {
            return "\tSearch for keywords amongst all tasks in your list."
                    + System.lineSeparator()
                    + "\tUsage: find <search query>";
        }
    },
    HELP {
        @Override
        public String toString() {
            return "\tShow this help menu."
                    + System.lineSeparator()
                    + "\tUsage: help";
        }
    },
    BYE {
        @Override
        public String toString() {
            return "\tExit the Herbert application."
                    + System.lineSeparator()
                    + "\tUsage: bye";
        }
    }
}
