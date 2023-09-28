# User Guide

DumpGPT is a **cross-platform application that allows users to track their tasks, optimized for use via a command-line
interface**.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding a todo: `todo`](#adding-a-todo-todo)
    * [Adding a task with Deadline: `deadline`](#adding-a-task-with-deadline-deadline)
    * [Adding a event: `event`](#adding-an-event-event)
    * [Marking task as done: `mark`](#marking-task-as-done-mark)
    * [Marking task as undone: `unmark`](#marking-task-as-undone-unmark)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Finding tasks: `find`](#finding-tasks-find)
    * [Exiting the program: `exit`](#exiting-the-program-bye)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 installed on your Computer.

2. Download the latest `Duke.jar` from [here](https://github.com/Jonoans/iP/releases).

3. Copy the `Duke.jar` file to the folder you want to use as your home folder for DumpGPT.

4. Open a command terminal, `cd` into the directory you have placed `Duke.jar` in and use the
   command `java -jar Duke.jar` to start the application.<br><br>
   An initial output should be printed with a `User: ` prompt.

    ```
    ____________________________________________________________
    Hello I'm DumbGPT
    What can I do for you?
    You currently have 0 tasks!
    ____________________________________________________________
    User: 
    ```

5. Refer to the [Features](#features) section below for the details of each command.

## Features

{: .important }
> * Words in `UPPER_CASE` are argument and parameters to be supplied by the user.<br>
    e.g. in `deadline TASK_NAME /by BY_DATE`, `TASK_NAME` is an argument while `BY_DATE` is a parameter
    which can be used as `deadline CS2113 Work /by 12/01/2024 10:00`.
>
> * Argument must come before parameters.<br>
    e.g. if the command specifies `deadline TASK_NAME /by BY_DATE`, `deadline /by BY_DATE TASK_NAME` is not acceptable.
>
> * Parameters (to be specified after arguments) can be in any order.<br>
    e.g. if the command specifies `/from FROM_DATE /to TO_DATE`, `/to TO_DATE /from FROM_DATE` is also acceptable.
>
> * Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.<br>
    e.g. if the command specifies `list 123`, it will be interpreted as `list`.
>
> * If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple
    lines
    as space characters surrounding line-breaks may be omitted when copied over to the application.

### Adding a todo: `todo`

Adds a todo task.

Format: `todo TASK_NAME`

* `TASK_NAME` cannot be empty

Examples:

* `todo Buy groceries`<br>
  Adds a task "Buy groceries" as todo

### Adding a task with deadline: `deadline`

Adds a task with a deadline.

Format: `deadline TASK_NAME /by BY_DATE`

* `TASK_NAME` cannot be empty
* `BY_DATE` expects the following format `dd/mm/yyyy hh:mm` where:
    - `dd`: Day of month
    - `mm`: Month
    - `yyyy`: Year
    - `hh`: Hour in 24-hour format
    - `mm`: Minutes

Examples:

* `deadline Complete CS2113 /by 12/01/2024 10:00`<br>
  Adds a task named "Complete CS2113" with a deadline of 12 January 2024 at 10am.

### Adding an event: `event`

Adds an event which spans a given duration FROM_DATE and TO_DATE.

Format: `event TASK_NAME /from FROM_DATE /to TO_DATE`

* `TASK_NAME` cannot be empty.
* `FROM_DATE` cannot be after `TO_DATE`<br>
  e.g. `/from 12/01/2024 12:00 /to 12/01/2024 10:00` is not valid
* `FROM_DATE` and `TO_DATE` expect the following format `dd/mm/yyyy hh:mm` where:
    - `dd`: Day of month
    - `mm`: Month
    - `yyyy`: Year
    - `hh`: Hour in 24-hour format
    - `mm`: Minutes

Examples:

* `event Graduation /from 12/01/2024 10:00 /to 12/01/2024 12:00`<br>
  Adds an event named "Graduation" which starts at 12 January 2024 10am and ends at 12 January 2024 12pm.

### Marking task as done: `mark`

Marks a task as done.

Format: `mark TASK_IDX`

* `TASK_IDX` expects an positive integer
* `TASK_IDX` specifies index of task to be marked as done.
* `TASK_IDX` references task indexes of most recently ran `list` or `find` command.

Examples:

* `mark 1`<br>
  Marks first task in list as done

### Marking task as undone: `unmark`

Marks a task as undone.

Format: `unmark TASK_IDX`

* `TASK_IDX` expects an positive integer
* `TASK_IDX` specifies index of task to be marked as undone.
* `TASK_IDX` references task indexes of most recently ran `list` or `find` command.

Examples:

* `unmark 1`<br>
  Marks first task in list as undone

### Deleting a task: `delete`

Deletes a given task.

Format: `delete TASK_IDX`

* `TASK_IDX` expects an positive integer
* `TASK_IDX` specifies index of task to be deleted.
* `TASK_IDX` references task indexes of most recently ran `list` or `find` command.

Examples:

* `delete 1`<br>
  Deletes first task in list

### Listing all tasks: `list`

Lists all tasks.

Format: `list`

* This command expects no argument or parameters
* This command affects `TASK_IDX` parameter for `mark`, `unmark` and `delete`.<br>
  e.g. if this command is run, then all subsequent `TASK_IDX` specified in `mark`, `unmark` and `delete` command will
  use the index output from this command as reference.

Example Output:

```
____________________________________________________________
1. [T][ ] Buy groceries
2. [D][ ] Complete CS2113 (by: 12/01/2024 - 10:00AM)
3. [E][ ] Graduation (from: 12/01/2024 - 10:00AM | to: 12/01/2024 - 12:00PM)
____________________________________________________________
User:
```

### Finding tasks: `find`

Find a task with substring of name

Format: `find PART_OF_NAME`

* `PART_OF_NAME` is case-sensitive
* All tasks containing `PART_OF_NAME` in their `TASK_NAME` will be outputted.
* This command affects `TASK_IDX` parameter for `mark`, `unmark` and `delete`.<br>
  e.g. if this command is run, then all subsequent `TASK_IDX` specified in `mark`, `unmark` and `delete` command will
  use the index output from this command as reference.

Example Output:

```
____________________________________________________________
User: find Compl
____________________________________________________________
1. [D][ ] Complete CS2113 (by: 12/01/2024 - 10:00AM)
____________________________________________________________
```

{: .warning }
> Subsequent calls to `mark`, `unmark` and `delete` will use this output as reference for `TASK_IDX` until `list`
> or `find` is executed again.
> <br><br>
> Thus, running `delete 1` in reference to the output above will cause the only task in the list to be deleted.
> <br><br>
> Any other calls to `mark`, `unmark` or `delete` will fail even if there are still tasks remaining until `list`
> or `find` is executed and return non-empty list of tasks.

### Exiting the program: `bye`

Exits the program

Format: `bye`

* This command expects no argument or parameters

Example Output:

```
____________________________________________________________
User: bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Command Summary

| Action                                | Format                                        | Example                                                        |
|---------------------------------------|-----------------------------------------------|----------------------------------------------------------------|
| Add todo                              | `todo TASK_NAME`                              | `todo Buy groceries`                                           |
| Add task with deadline                | `deadline TASK_NAME /by BY_DATE`              | `deadline Complete CS2113 /by 12/01/2024 10:00`                |
| Add an event                          | `event TASK_NAME /from FROM_DATE /to TO_DATE` | `event Graduation /from 12/01/2024 10:00 /to 12/01/2024 12:00` |
| Mark task as done                     | `mark TASK_IDX`                               | `mark 1`                                                       |
| Mark task as undone                   | `unmark TASK_IDX`                             | `unmark 1`                                                     |
| Delete a task                         | `delete TASK_IDX`                             | `delete 1`                                                     |
| List all tasks                        | `list`                                        | `list`                                                         |
| Find task with name containing a term | `find PART_OF_NAME`                           | `find Compl`                                                   |
| Exit the program                      | `exit`                                        | `exit`                                                         |

