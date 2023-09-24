# User Guide for Soccat

## List of features in Soccat Version 1.0:

* [Adding a todo task](#adding-a-todo-task-todo): `todo`

* [Adding a deadline task](#adding-a-deadline-task-deadline): `deadline`

* [Adding an event task](#adding-an-event-task-event): `event`

* [Show all the tasks](#showing-all-the-tasks-list): `list`

* [Finding a task by keyword](#finding-a-task-by-keyword-find): `find`

* [Marking a task as done](#marking-a-task-as-done-mark): `mark`

* [Marking a task as not done](#marking-a-task-as-not-done-unmark): `unmark`

* [Deleting a task](#deleting-a-task-delete): `delete`

* [Exiting the program](#exiting-the-program-exit): `exit`

* [Editing tasks stored in the data file](#editing-tasks-stored-in-the-data-file)

## Quick Start
1. Ensure you have `Java-11` installed on your computer
2. Download the `Soccat.jar` from the project GitHub
3. Copy the jar file to your desired folder
4. Open a command prompt in the same directory as the jar file
5. Run `java -jar Soccat.jar` to run the application.

## Features

Notes on the program usage:
* The format of the commands must be followed
* The parameter of the commands must be entered in sequence
* Only modify the data file after exiting the program

### Adding a todo task: `todo`

Adds a todo task to the list of tasks.
* The task will be displayed after being successfully added.
* Tasks will automatically be saved in the task file after this operation.

Format: `todo TASK_NAME`

Example: `todo CS2113 Individual Project`

Expected output: 
```
Got it. I've added this todo:
        [T][ ] CS2113 Individual Project
Now you have 1 tasks in the list.
```

### Adding a deadline task: `deadline`

Adds a deadline task to the list of tasks.
* The task will be displayed after being successfully added.
* Tasks will automatically be saved in the task file after this operation.

Format: `deadline TASK_NAME /by DUE_DATE`

Example: `deadline CS2113 Quiz /by 2023-10-06`

Expected output:
```
Got it. I've added this deadline:
        [D][ ] CS2113 Quiz (by: 2023-10-06)
Now you have 2 tasks in the list.
```

### Adding an event task: `event`

Adds an event task to the list of tasks.
* The task will be displayed after being successfully added.
* Tasks will automatically be saved in the task file after this operation.

Format: `event EVENT /from START_DATE /to END_DATE`

Example: `event CS2113 IP /from 2023-09-22 /to 2023-10-06`

Expected output:
```
Got it. I've added this event:
        [E][ ] CS2113 IP (from: 2023-09-22 to: 2023-10-06)
Now you have 3 tasks in the list.
```

### Showing all the tasks: `list`

List all the tasks currently stored in the task file.

Format: `list`

Expected output:
```
1. [T][ ] CS2113 Individual Project
2. [D][ ] CS2113 Quiz (by: 2023-10-06)
3. [E][ ] CS2113 IP (from: 2023-09-22 to: 2023-10-06)
```

### Finding a task by keyword: `find`

Attempts to find tasks in which the name contains the keyword.
* This command will return a list of questions that match the query.
* The index of the tasks returned corresponds to their original index, 
similar to their index in the `list` command.
* The keyword is **not case-sensitive.**
    
Format: `find KEY_WORD`

Example: `find Quiz` or `find qUiZ` or `find quiz`

Expected output:
```
2. [D][ ] CS2113 Quiz (by: 2023-10-06)
```

### Marking a task as done: `mark`

Marks a task as done based on the provided **1-based task index**.
* The task index could be found using the previous `list` or `find` command.
* Tasks will automatically be saved in the task file after this operation.

Format: `mark TASK_INDEX`

Example: `mark 1`

Expected output:
```
Nice! I've marked this task as done:
        [T][X] CS2113 Individual Project
```

### Marking a task as not done: `unmark`

Marks a task as not done based on the provided **1-based task index**.
* The task index could be found using the previous `list` or `find` command.
* Tasks will automatically be saved in the task file after this operation.

Format: `unmark TASK_INDEX`

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
        [T][ ] CS2113 Individual Project
```

### Deleting a task: `delete`

Deletes a task from the task file based on the provided **1-based task index**.
* The task index could be found using the previous `list` or `find` command.
* Tasks will automatically be saved in the task file after this operation.

Format: `delete TASK_INDEX`

Example: `delete 1`

Expected output:
```
Noted, I have removed this task:
        [T][ ] CS2113 Individual Project
Now you have 2 tasks in the list.
```

### Exiting the program: `exit`

Quits the program

Format: `exit`

### Editing tasks stored in the data file

The data would be stored in the following format
* Task: `T|DONE/NOT_DONE|TASK_NAME`
* Deadline: `D|DONE/NOT_DONE|TASK_NAME|DUE_DATE`
* Event: `E|DONE/NOT_DONE|EVENT_NAME|START|END`

An example of the file is as follows:
```
D|0|CS2113 Quiz|2023-10-06
E|0|CS2113 IP|2023-09-22|2023-10-06
T|0|CS2113 Invididual Project
```
You may edit the file to edit or add tasks of your choosing as well.

