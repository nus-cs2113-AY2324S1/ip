# Dude Chatbot User Guide

Hello from
   
 /$$$$$$$                  /$$          
| $$__  $$                | $$          
| $$  \ $$ /$$   /$$  /$$$$$$$  /$$$$$$ 
| $$  | $$| $$  | $$ /$$__  $$ /$$__  $$
| $$  | $$| $$  | $$| $$  | $$| $$$$$$$$
| $$  | $$| $$  | $$| $$  | $$| $$_____/
| $$$$$$$/|  $$$$$$/|  $$$$$$$|  $$$$$$$
|_______/  \______/  \_______/ \_______/

Welcome to Dude, your trusty task management chatbot. Dude helps you stay organized by keeping track of your tasks, whether they're to-dos, deadlines, or events. This user guide will provide you with the essential information to make the most of Dude's features.


## Getting Started

To get started with Dude, follow these steps:

1. Ensure you have Java Development Kit (JDK) 11 installed on your computer.
2. Make sure you have the latest version of IntelliJ IDEA installed.
3. Clone or download the Dude project repository.

## Setting Up Dude in IntelliJ IDEA

1. Open IntelliJ IDEA.
2. If you have an existing project open, close it.
3. Click on "File" > "Open" and select the Dude project directory.
4. Configure the project to use JDK 11:
   - Click on "File" > "Project Structure."
   - Under "Project," set the "Project SDK" to JDK 11.
   - Set the "Project language level" to "SDK default."

## Running Dude

1. Locate the `Dude.java` file in the `src/main/java` directory of the Dude project.
2. Right-click on `Dude.java` and select "Run Dude.main()".
3. If everything is set up correctly, Dude will start, and you'll see a greeting message.

## Using Dude

Dude understands various commands to manage your tasks. Here are some essential commands:

- `list`: List all tasks in your task list.
- `todo <description>`: Add a to-do task.
- `deadline <description> /by <due_date>`: Add a deadline task.
- `event <description> /from <start_date> /to <end_date>`: Add an event task.
- `mark <task_index>`: Mark a task as done.
- `unmark <task_index>`: Mark a task as not done.
- `delete <task_index>`: Delete a task from the list.
- `find <keyword>`: Find tasks containing a specific keyword.
- `bye`: Exit Dude.

## Examples:

- To add a to-do task: `todo Buy groceries`
- To add a deadline task: `deadline Submit report /by 2023-12-31`
- To add an event task: `event Team meeting /from 2023-09-30 /to 2023-10-01`
- To mark a task as done: `mark 1`
- To delete a task: `delete 2`
- To find tasks containing a keyword: `find book`

Remember to replace `<description>`, `<due_date>`, `<start_date>`, `<end_date>`, `<task_index>`, and `<keyword>` with your specific task details.

## Conclusion

You're now ready to start using Dude to manage your tasks efficiently. If you have any questions or encounter issues, feel free to reach out to us. Dude is here to make task management a breeze!

Happy tasking!
