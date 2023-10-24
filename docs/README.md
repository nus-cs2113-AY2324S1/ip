# User Guide for Hilary chatbot
Hilary chatbot is a **task management** chatbot that use to manage different types of tasks via a **Command Line Interface**.

## Features 
This robot can identify in 3 different tasks, **todo**, **event** and **deadline**<br>
It can also **record** and **display** the tasks user input, **modify the status**, **search task by name** and **retrieve tasks** from the disk.

### Viewing help: `help`
Shows how to use the program in the terminal<br><br>
Format: `help`

### Adding a todo: `todo`
Adds a todo task to the list<br><br>
Format: `todo taskName`<br>

Example:
- `todo CS2113 iP` add a todo task call CS2113 iP into the task list

### Adding a deadline: `deadline`
Adds a deadline task with deadline time to the list<br><br>
Format: `deadline taskName /by dd/MM/yyyy hhmm` <br>

Example:
- `deadline CS2113 iP /by 05/10/2023 2359` add a deadline task  CS2113 iP with the deadline time is 5th Oct 2023 at 11:59pm

### Adding a event: `event`
Adds a event task to the list<br><br>
Format: `event taskName /from dd/MM/yyyy hhmm /to dd/MM/yyyy hhmm` <br>

Example: 
- `event CS2113 iP /from 10/08/2023 0000 /to 05/10/2023 2359` add a event task CS2113 iP with the starting time of 10th Aug 2023 12:00am to 5th Oct 2023 11:59pm

### Delete a task: `delete`
Delete a task by it's index<br><br>
Format: `delete indexOfTheTask`<br>
- The index must be a positive integer 1, 2, 3, …​
- The index refers to the index number shown in the displayed task list.<br>

Example:
- `delete 1` delete the 1st task in the task list

### Mark a task as done: `mark`
Mark a task as done by it's index<br><br>
Format: `mark indexOfTheTask`<br>
- The index must be a positive integer 1, 2, 3, …​
- The index refers to the index number shown in the displayed task list.<br>

Example:
- `mark 1`  mark the 1st task in the task list as done

### Unmark a task as done: `unmark`
mark a task as not done by it's index<br><br>
Format: `unmark indexOfTheTask`<br>
- The index must be a positive integer 1, 2, 3, …​
- The index refers to the index number shown in the displayed task list.<br>

Example:
- `unmark 1` mark the 1st task in the task list as not done

### Search for tasks by keyword: `find`
Find tasks in the taskList with the keyword<br><br>
Format: `find taskName`<br>
- The search is case insensitive e.g CS2113 will match cs2113
- The order of the keywords does matter. e.g. cs2113 ip will not match ip cs2113
- Only the name is searched.
- Any task contain the keyword will be match e.g. 2113 will match with CS2113<br>

Example:
- `find CS2113` return the task name include CS2113 with case insensitive

### Listing all tasks: `list`
Shows a list of all tasks in the task list<br><br>
Format: `list`

### Exiting the program: `bye`
Exit the program<br><br>
Format: `bye`

### Editing the data file
Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### FAQ
**Q**: How do I transfer my data to another Computer?<br>
**A**: Send the Hilary.txt inside the JAR file location to another computer's JAR file location for the duke



