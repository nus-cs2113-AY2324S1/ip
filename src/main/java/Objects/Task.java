package Objects;
public class Task {
        protected String description;
        protected boolean isDone;
        protected char type;

        private static int numberOfTasks = 0;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            this.type = 'T';
            numberOfTasks += 1;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public char getType(){
            return this.type;
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

    @Override
     public String toString() {
         return String.format(this.description);
     }
    }

