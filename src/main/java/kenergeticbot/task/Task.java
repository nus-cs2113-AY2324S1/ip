package kenergeticbot.task;

/**
 * Represents an executable Task
 */
public class Task {
    protected String description;
        protected boolean isDone;
        protected String taskType;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        public String getTaskType() {
            return taskType;
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String toString() {
            return getStatusIcon() + " " + description;
        }

        public String printTaskToSave() {
            int mark = isDone ? 1 : 0;
            return  " | " + mark + " | " + description;
        }
}
