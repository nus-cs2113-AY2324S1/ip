package kenergeticbot.task;
public class Task {
    protected String description;
        protected boolean isDone;
        protected String taskType;

        public Task(String description, String taskType) {
            this.description = description;
            this.isDone = false;
            this.taskType = taskType;
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
