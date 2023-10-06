# User Guide for Luke

## Features 

### Task List

Luke keeps a task list of all the todo, deadlines and events.

### Mark as Completed/Incomplete

You can mark tasks as completed or incompleted.

### Store Memory

When closing Luke, all current tasks are stored in a txt file.
The next time Luke is opened, all tasks in the txt file is loaded back into Luke.

## Usage


### `todo` - Add todo

Add new task (todo) to your list.

Format of usage:

`todo DESCRIPTION`

Example of usage:

`todo read book`

Expected outcome:

New todo task added, so there will be a confirmation message.

```
    Got it. I've added this task:
	[T][ ] read book
	Now you have 1 tasks in the list.
```

### `deadline` - Add deadline

Add new task (deadline) to your list.

Format of usage:

`deadline DESCRIPTION /by DATE`

Example of usage:

`deadline return book /by next saturday`

Expected outcome:

New deadline task added, so there will be a confirmation message.

```
    Got it. I've added this task:
	[D][ ] return book (do by: next saturday)
	Now you have 2 tasks in the list.
```

### `event` - Add event

Add new task (event) to your list.

Format of usage:

`event DESCRIPTION /from STARTDATE /to ENDDATE`

Example of usage:

`event reading club session /from friday 4 /to 6pm`

Expected outcome:

New event task added, so there will be a confirmation message.

```
    Got it. I've added this task:
    [E][ ] reading club session (from: friday 4 to: 6pm)
    Now you have 1 tasks in the list.
```

### `list` - Lists all tasks

Lists all tasks in the task list.

Example of usage: 

`list`

Expected outcome:

This will display all your tasks, including their type (todo, deadline, event), their task description and their status (completed or not).

```
    Here are the tasks in your list:
    1.	[T][ ] read book
    2.	[D][ ] return book (do by: next saturday)
    3.	[E][ ] reading club session (from: friday 4 to: 6pm)
```

### `mark` - Mark a task as completed

Mark a task as completed using the "mark" command followed by the task number.

Format of usage:

`mark INDEX`

Example of usage:

`mark 1`

Expected outcome:

This will mark the task with index 1 as completed.

```
    Woohoo! You have accomplished:
    [T][X] read book
```

### `unmark` - Mark a task as incomplete

Mark a task as incomplete using the "unmark" command followed by the task number.

Format of usage:

`unmark INDEX`

Example of usage:

`unark 1`

Expected outcome:

This will mark the task with index 1 as incomplete.

```
    HA! You still have to complete:
    [T][ ] read book
```


### `find` - Find user's input in tasks descriptions

Show the corresponding tasks.

Format of usage:

`find KEYWORD`

Example of usage:

`find book`

Expected outcome:

List of tasks that have descriptions matching the user's input.

```
    Here are the matching tasks in your list:
    1.	[T][ ] read book
    2.	[D][ ] return book (do by: next saturday)
```

### `delete` - Remove a task from the task list

To delete a task from your list, use the "delete" command followed by the task number.

Format of usage:

`delete INDEX`

Example of usage:

`delete 2`

Expected outcome:

This will remove the task with index 2 from your list.


```
    Noted. I've removed this task:
    [D][ ] return book (do by: next saturday)
    Now you have 2 tasks in the list.
```

### `bye` - Close Luke

Close Luke and store memory to a txt file.

Example of usage:

`bye`

Expected outcome:

Message whether memory is stored safely, and Luke is closed.

```
    Memory Stored Safely!
    Bye. Hope to see you again soon!
```
