# User Guide

CSGPT is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CSGPT can get your task management done faster than traditional GUI apps.

## Features 

> [!NOTE]
> **Notes about the command format:**
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.\
> e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

### `todo` - add a todo task

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Example input: `todo read book`

Expected outcome:

```
Added: [T][ ] read book
Now you have (x) tasks in the list.
```

### `deadline` - add a deadline task

Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DATE`

> [!NOTE]
> - `DATE` must be in the format `YYYY-MM-DD`.

Example input: `deadline return book /by 2020-09-18`

Expected outcome:

```
Added: [D][ ] return book (by: Sep 18 2020)
Now you have (x) tasks in the list.
```

### `event` - add an event task

Adds an event task to the task list.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`

> [!NOTE]
> - `START_DATE` and `END_DATE` must be in the format `YYYY-MM-DD`.
> - `START_DATE` must be before `END_DATE`.

Example input: `event project meeting /from 2020-09-18 /to 2020-09-19`

Expected outcome:

```
Added: [E][ ] project meeting (from: Sep 18 2020 to: Sep 19 2020)
Now you have (x) tasks in the list.
```

### `list` - list all tasks

Lists all tasks in the task list.

Format: `list`

Expected outcome:

```
These are the chores you have at hand, mortal:
1. [D][ ] return book (by: Sep 18 2020)
2. [T][ ] read book
3. [E][ ] project meeting (from: Sep 18 2020 to: Sep 19 2020)
```

### `mark` - mark a task as done

Marks a task as done in the task list.

Format: `mark INDEX`

> [!NOTE]
> - `INDEX` must not be larger than size of task list.

Example input: `mark 2`

Expected outcome:

```
Consider it done:
[D][X] return book (by: Sep 18 2020)
```

### `unmark` - mark a task as not done

Marks a task as not done in the task list.

Format: `unmark INDEX`

> [!NOTE]
> - `INDEX` must not be larger than size of task list.

Example input: `unmark 2`

Expected outcome:

```
Consider it done:
[D][ ] return book (by: Sep 18 2020)
```

### `delete` - delete a task

Deletes a task from the task list.

Format: `delete INDEX`

> [!NOTE]
> - `INDEX` must not be larger than size of task list.

Example input: `delete 2`

Expected outcome:

```
Noted. I've removed this task. Now you have (x) tasks in the list.
```

### `find` - find tasks by a keyword

Finds tasks in the task list by a keyword.

Format: `find KEYWORD`

Example input: `find book`

Expected outcome:

```
These are the matching chores you have at hand, mortal:
1. [D][ ] return book (by: Sep 18 2020)
3. [T][ ] read book
```

### `bye` - exit the program

Exits the program.

Format: `bye`