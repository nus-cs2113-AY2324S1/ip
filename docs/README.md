# Chatty User Guide

Chatty is a CLI chatbot designed to help you manage your daily tasks and deadlines effectively.

## Contents
* [Quick Start](#quick-start)
* [Features](#features)
    * [Add a Task: `todo`, `deadline`, `event`](#add-a-task-todo-deadline-event)
    * [List Tasks: `list`](#list-tasks-list)
    * [Mark a Task as Done: `mark`](#mark-a-task-as-done-mark)
    * [Mark a Task as Not Done: `unmark`](#mark-a-task-as-not-done-unmark)
    * [Delete a Task: `delete`](#delete-a-task-delete)
    * [Find a Task by Keyword: `find`](#find-a-task-by-keyword-find)
    * [Print Deadlines/Events on a Specific Date: `showdate`](#print-deadlines-and-events-on-a-specific-date-showdate)
    * [Exiting the Program: `bye`](#exiting-the-program-bye)
* [Known Issues](#known-issues)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java 11 or above installed on your computer.
   - [How to Check Your Java Version](https://www.java.com/en/download/help/version_manual.html)
2. Upon successful launch of the chatbot, it would display:
```
____________________________________________________________
Hello! I'm Chatty!
What can I do for you?
____________________________________________________________
```

## Features | [Return to contents](#Contents)
> Note:
> - Items in square brackets `[]` are optional arguments.
> - Items with `...` after them can be used multiple times.

### Add a Task: `todo`, `deadline`, `event`
Adds a new task to your list. You can create three types of tasks:
- To-Do (`todo`)
- Deadline (`deadline`)
- Event (`event`)

**Formats:**
- To-Do: `todo DESCRIPTION`
   - Example: `todo Buy groceries`
- Deadline: `deadline DESCRIPTION /by DATE`
   - Example: `deadline Submit report /by 2023-08-31`
- Event: `event DESCRIPTION /from DATE /to DATE`
   - Example: `event Team meeting /from 2023-09-01 /to 2023-09-01`

> Note:
> - DATE in the format of YYYY-MM-DD

### List Tasks: `list`
Displays your list of tasks. You can filter tasks by their status or date.

**Format:** `list`

**Example**
```
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
3.[T][ ] buy bread
____________________________________________________________
```

### Mark a Task as Done: `mark`
Marks a task as done by specifying its index in the list.

**Format:** `mark INDEX`

**Example:** 
```
mark 2
____________________________________________________________
Nice! I've marked this task as done:
[D][X] return book (by: June 6th)
____________________________________________________________
```

### Mark a Task as Not Done: `unmark`
Marks a completed task as not done by specifying its index in the list.

**Format:** `unmark INDEX`

**Example:** 
```
unmark 2
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] return book (by: June 6th)
____________________________________________________________
```

### Delete a Task: `delete`
Removes a task from your list by specifying its index.

**Format:** `delete INDEX`

**Example:**
```
delete 3
____________________________________________________________
Noted. I've removed this task:
[T][ ] buy bread
Now you have 2 tasks in the list.
____________________________________________________________
```

### Find a Task by Keyword: `find`
Searches for tasks containing a specific keyword in their descriptions.

**Format:** `find KEYWORD`

**Example:**
```
find book
____________________________________________________________
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
____________________________________________________________
```

### Print Deadlines and Events on a Specific Date: `showdate`
Displays tasks (deadlines and events) occurring on a specific date.

**Format:** `showdate DATE`

**Example:** `showdate 2023-08-31`

### Exiting the Program: `bye`
Displays a farewell message and exits the program.
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

**Format:** `bye`

## Known Issues | [Return to contents](#Contents)
* Unable to add time to Deadlines and Events

## Command Summary | [Return to contents](#Contents)

| Action                      | Command                |
| ----------------------------| -----------------------|
| Add a Task                  | `todo DESCRIPTION`<br>`deadline DESCRIPTION /by DATE `<br>`event DESCRIPTION /from DATE /to DATE` |
| List all Tasks                  | `list` |
| Mark a Task as Done         | `mark INDEX` |
| Mark a Task as Not Done     | `unmark INDEX` |
| Delete a Task               | `delete INDEX` |
| Find a Task by Keyword      | `find KEYWORD` |
| Print Deadlines/Events on a Specific Date | `showdate DATE` |
| Exit                        | `bye` |

For more information about each command, please refer to the corresponding section in the user guide.