package herbert;

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
            return "\tAdd a new deadline to your list of tasks."
                    + System.lineSeparator()
                    + "\tUsage: deadline <task description> /by <due date>";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "\tAdd a new event to your list of tasks."
                    + System.lineSeparator()
                    + "\tUsage: event <task description> /from <start> /to <end>";
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
