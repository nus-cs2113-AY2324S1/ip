# LinguoBot's User Guide
**_LinguoBot_** represents a simple command-line task management application. It allows users to interact with tasks, such as adding, listing, marking, unmarking, and deleting tasks. Tasks are stored in a task list and can be loaded from and saved
to a file.
## Features 

- Adding todo task: `todo`

- Adding deadline task: `deadline`

- Listing all tasks: `list`

- Marking a task: `mark`

- Unmarking a task: `unmark`

- Deleting a task: `delete`

- Locating tasks by name: `find`

- Exiting the program: `bye`

## Usage

### <span style="color: green"> Adding todo task: `todo` </span>
Adds a task [of type: _todo_] to the task list.

Format: `todo DESCRIPTION`

Example: todo read book

```
===================================================
Got it. I've added this task:
[T][ ] read book
Now you have 1 task(s) in the list.
===================================================
```

### <span style="color: green"> Adding deadline task: `deadline` </span>
Adds a task [of type: _deadline_] to the task list.

Format: `deadline DESCRIPTION by DATE TIME(optional)`

- `DATE` should be in format D/M/YYYY
- Optional `TIME` should be in format H:MM AM/PM

Examples:
- deadline return book by 25/10/2023
```
===================================================
Got it. I've added this task:
[D][ ] return book (by: Oct 25 2023)
Now you have 2 task(s) in the list.
===================================================
```
- deadline return book by 25/10/2023 6:00 PM
```
===================================================
Got it. I've added this task:
[D][ ] return book (by: Oct 25 2023 1800)
Now you have 3 task(s) in the list.
===================================================
```

### <span style="color: green"> Adding event task: `event` </span>
Adds a task [of type: _event_] to the task list.

Format: `event DESCRIPTION from TIME to TIME`

Example: event go to school from 12pm to 6pm

```
===================================================
Got it. I've added this task:
[E][ ] go to school (from: 12pm to: 6pm)
Now you have 4 task(s) in the list.
===================================================
```

### <span style="color: green"> Listing all tasks: `list` </span>
Shows a list of all tasks in the task list.

Format: `list`

```
===================================================
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Oct 25 2023)
3.[D][ ] return book (by: Oct 25 2023 1800)
4.[E][ ] go to school (from: 12pm to: 6pm)
===================================================
```
### <span style="color: green"> Marking a task: `mark` </span>
Marks a task as done.

Format: `mark INDEX`

- Marks the task at the specified `INDEX` by displaying status icon as 'X'.
- The `INDEX` refers to the index number shown in the displayed task list.
- The `INDEX` must be a positive integer 1,2,3,...

Example: mark 1
```
===================================================
Nice! I've marked this task as done:
[T][X] read book
===================================================

```
### <span style="color: green"> Unmarking a task: `unmark` </span>
Marks a task as undone.

Format: `unmark INDEX`

- Unmarks the task at the specified `INDEX` by displaying status icon as ' '.
- The `INDEX` refers to the index number shown in the displayed task list.
- The `INDEX` must be a positive integer 1,2,3,...

Example: unmark 1
```
===================================================
OK, I've marked this task as not done yet:
[T][ ] read book
===================================================
```
### <span style="color: green"> Deleting a task: `delete` </span>
Deletes the specified task from the task list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list.
- The `INDEX` must be a positive integer 1,2,3,...

Example: delete 2
```
===================================================
Noted. I've removed this task:
[D][ ] return book (by: Oct 25 2023)
Now you have 3 task(s) in the list.
===================================================
```
### <span style="color: green"> Locating tasks by name: `find` </span>
Finds tasks whose description contain the provided keyword.

Format: `find KEYWORD`

Example: find book
```
===================================================
Here are the matching tasks in your list:
[T][ ] read book
[D][ ] return book (by: Oct 25 2023 1800)
===================================================
```

### <span style="color: green"> Exiting the program: `bye` </span>
Exits the program.

Format: `bye`
```
===================================================
Bye. Hope to see you again soon!
===================================================
```
