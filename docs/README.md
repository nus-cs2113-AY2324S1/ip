# User Guide for Neo Chatbot

Neo Chatbot is a desktop app for **keeping track of tasks**, optimised for use via a **Command Line Interface (CLI)**.

## Features 
### Notes about the command format
- Words in `UPPER_CASE` are parameters to be supplied by the user.
  
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.  
- Items in square brackets are optional.

    e.g. `/by DATE [TIME]` can be used as `/by 26/09/2001` or `/by 26/09/2001 2119`.


### Adding Task of type **todo**: `todo`

Adds a task to the list. Todo is a type of task that is not time-sensitive.

Format: `todo DESCRIPTION`

Example: `todo read book`

Expected outcome: 
```
Got it. I've added this task:
    [T][ ] read book
Now you have 2 tasks in the list.
```

- The number of tasks in the list shown above is just an example. Your actual output may differ depending on the number of tasks you have added.

### Adding Task of type **deadline**: `deadline`

Adds a task to the list. Deadline is a type of task that has a due date.

Format: `deadline DESCRIPTION /by DATE [TIME]`

- The date follows DD/MM/YYYY format. e.g. 26/09/2023 represents 26 September 2023.
- The time follows HHMM in 24-Hour format. e.g. 1700 represents 5:00 PM.

Example: `deadline read book /by 26/09/2023`

Expected outcome:
```
Got it. I've added this task:
    [D][ ] read book (by: Tuesday, 26 September 2023)
Now you have 2 tasks in the list.
```

Example: `deadline read book /by 26/09/2023 1700`

Expected outcome:
```
Got it. I've added this task:
    [D][ ] read book (by: Tuesday, 26 September 2023, 5:00 PM)
Now you have 2 tasks in the list.
```
- The number of tasks in the list shown above is just an example. Your actual output may differ depending on the number of tasks you have added.

### Adding Task of type **event**: `event`

Adds a task to the list. Event is a type of task that has a start and end date.

Format: `event DESCRIPTION /from DATE [TIME] /to DATE [TIME]`

- The date follows DD/MM/YYYY format. e.g. 26/09/2023 represents 26 September 2023.
- The time follows HHMM in 24-Hour format. e.g. 1700 represents 5:00 PM.

Example: `event read book /from 26/09/2023 /to 28/09/2023`

Expected outcome:
```
Got it. I've added this task:
    [E][ ] read book (from: Tuesday, 26 September 2023 to: Thursday, 28 September 2023)
Now you have 2 tasks in the list.
```
Example: `event read book /from 26/09/2023 1700 /to 28/09/2023 1900`

Expected outcome:
```
Got it. I've added this task:
    [E][ ] read book (from: Tuesday, 26 September 2023, 5:00 PM to: Thursday, 28 September 2023, 7:00 PM)
Now you have 2 tasks in the list.
```
- The number of tasks in the list shown above is just an example. Your actual output may differ depending on the number of tasks you have added.

### Listing all tasks: `list`

Shows a list of all tasks.

Format: `list`

### Mark a task: `mark`

Marks the task at the specified index in the list as done.

Format: `mark INDEX`

- The index refers to the index number shown in the displayed task list.

Example: `mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
    [T][X] read book
```
- The task shown above is just an example. Your actual output may differ depending on the task you have added.

### Unmark a task: `unmark`

Marks the task at the specified index in the list as undone.

Format: `unmark INDEX`

- The index refers to the index number shown in the displayed task list.

Example: `unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
    [T][ ] read book
```

- The task shown above is just an example. Your actual output may defer depending on the task you have added.

### Delete a task: `delete`

Deletes the task at the specified index in the list.

Format: `delete INDEX`

- The index refers to the index number shown in the displayed task list.

Example: `delete 1`

Expected outcome:
```
Noted. I've removed this task:
    [T][ ] read book
Now you have 4 tasks in the list.
```
- The task shown above is just an example. Your actual output may defer depending on the task you have added.
- The number of tasks in the list shown above is just an example. Your actual output may defer depending on the number of tasks you have added.

### Find a task: `find`

Finds all tasks with descriptions that match the keyword.

Format: `find KEYWORD`

- The search is case-insensitive. e.g `read book` will match `Read Book`.

Example: `find read book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][ ] read book (by: Tuesday, 26 September 2023)
```
- The task shown above is just an example. Your actual output may defer depending on the task you have added.

### Exiting the program: `bye`

Exits the program.

Format: `bye`
