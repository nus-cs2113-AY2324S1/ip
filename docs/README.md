# User Guide for Hilary chatbot
Hilary chatbot is a **task management** chatbot that use to manage different types of tasks via a **Command Line Interface**.

## Features 
This robot can identify in 3 different tasks, **todo**, **event** and **deadline**
It can also **record** and **display** the tasks user input, **modify the status**, **search task by name** and **retrieve tasks** from the disk.

### Viewing help: `help`
- Shows how to use the program in the terminal
- Format: `help`

### Adding a todo: `todo`
- Adds a todo task to the list
Format: `todo taskName`
Example: `todo CS2113 iP`

### Adding a deadline: `deadline`
- Adds a deadline task with deadline time to the list
Format: `deadline taskName /by dd/MM/yyyy hhmm`
Example: `deadline CS2113 iP /by 05/10/2023 2359`

### Adding a event: `event`
- Adds a event task to the list
Format: `event taskName /from dd/MM/yyyy hhmm /to dd/MM/yyyy hhmm`
Example: `event CS2113 iP /from 10/08/2023 0000 /to 05/10/2023 2359`

### Delete a task: `delete`
- Delete a task by it's index
- The index must be a positive integer 1, 2, 3, …​
- The index refers to the index number shown in the displayed task list.
Format: `delete indexOfTheTask`
Example: `delete 1`

### Mark a task as done: `mark`
- Mark a task as done by it's index
- The index must be a positive integer 1, 2, 3, …​
- The index refers to the index number shown in the displayed task list.
Format: `mark indexOfTheTask`
Example: `mark 1`

### Unmark a task as done: `unmark`
- mark a task as not done by it's index
- The index must be a positive integer 1, 2, 3, …​
- The index refers to the index number shown in the displayed task list.
Format: `unmark indexOfTheTask`
Example: `unmark 1`

### Search for tasks by keyword: `find`
- Find tasks in the taskList with the keyword
- The search is case insensitive e.g CS2113 will match cs2113
- The order of the keywords does matter. e.g. cs2113 ip will not match ip cs2113
- Only the name is searched.
- Any task contain the keyword will be match e.g. 2113 will match with CS2113
Format: `find taskName`
Example: `find CS2113`

### Listing all tasks: `list`
Shows a list of all tasks in the task list
Format: `list`

### Exiting the program: `bye`
Exit the program
Format: `bye`

### Exiting the program: `bye`
Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### FAQ
**Q**: How do I transfer my data to another Computer?
**A**: Send the Duke.txt inside the JAR file to another computer's JAR file location for the Duke



