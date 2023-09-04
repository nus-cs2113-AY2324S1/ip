public class Task {
        protected String description;
        protected boolean isDone;

        private static int numberOfTasks = 0;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            numberOfTasks += 1;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return this.description;
        }

        public void markStatus() {
            this.isDone = true;
        }

        public void unmarkStatus() {
            this.isDone = false;
        }

        public int getTotalNumberOfBicycles() {
            return numberOfTasks;
        }
    }

