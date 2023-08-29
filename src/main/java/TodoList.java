public class TodoList {
    private final String[] Todos = new String[100];
    private int totalTodos = 0;
    private final String solidLine = "\n------------------------------------------------------------------------------------------------------------------------------\n";

    public void printTodos() {
        System.out.println(solidLine);
        for(int i = 0; i < totalTodos; i++) {
            System.out.println((i+1) + ". " + Todos[i]);
        }
        System.out.println(solidLine);
    }

    public void addTodo(String Todo) {
        Todos[totalTodos] = Todo;
        totalTodos++;
        System.out.println(solidLine + "added: " + Todo + solidLine);
    }
}
