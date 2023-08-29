public class TodoList {
    private final Todo[] Todos = new Todo[100];
    private int totalTodos = 0;
    private final String solidLine = "\n------------------------------------------------------------------------------------------------------------------------------\n";

    public void printTodos() {
        System.out.print(solidLine);
        for(int i = 0; i < totalTodos; i++) {
            System.out.println((i+1) + ". " + "[" +
                    Todos[i].getStatusIcon() + "] " +
                    Todos[i].getDescription());
        }
        System.out.print(solidLine);
    }

    public void markTodo(int index, boolean IsDone) {
        System.out.println(solidLine + "Tres Bien! I have marked this as " +
                (IsDone ? "done: " : "not done yet: "));
        Todos[index-1].setIsDone(IsDone);
        System.out.println("[" + Todos[index-1].getStatusIcon() + "] " +
                Todos[index-1].getDescription() + solidLine);
    }

    public void addTodo(String description) {
        Todo newTodo = new Todo(description);
        Todos[totalTodos] = newTodo;
        totalTodos++;
        System.out.println(solidLine + "added: " + description + solidLine);
    }
}
