# Lemon's User Guide

## Features 

### Help

Displays a list of available commands and their syntax format.

### View List

Displays the task list.

### Add Todo Task

Adds a new todo task to the task list.

### Add Deadline Task

Adds a new deadline task with a due date/time to the task list.

### Add Event Task

Adds a new event task with a start and end date/time to the task list.

### Mark Task

Marks a task as done.

### Unmark Task

Marks a task as not done.

### Find Task

Finds tasks by keyword.

### Delete Task

Deletes tasks from the task list.

### Exit Program

Exits the program.

## Usage

### `list` - Display all tasks

Lists all tasks in the task list.

Format: `list`

Example of usage: 

`list`

Expected outcome:

Displays the type of task, completion status, task description, and any applicable date/time.

```
Your basket is looking citrusy-fresh:
1. [T][X] Chat with Lemon
2. [D][ ] Submit Lemonade Stand Proposal (by: tomorrow)
3. [E][ ] Attend Lemon Festival (from: today to: tomorrow)
```

### `todo` - Add todo task

Adds a todo task to the task list. The todo task is also stored in a file.

Format: `todo <task description>`

Example of usage:

`todo Chat with Lemon`

Expected outcome:

Acknowledges that the task has been added. Displays the added task and the number of tasks in task list.

```
Got it! This task has been squeezed into your basket:
	[T][X] Chat with Lemon
Now you have 1 fruitful tasks in your basket!
```

### `deadline` - Add deadline task

Adds a deadline task to the task list. The deadline task is also stored in a file.

Format: `deadline <task description> /by <due date/time>`

Example of usage:

`deadline Submit Lemonade Stand Proposal /by tomorrow`

Expected outcome:

Acknowledges that the task has been added. Displays the added task and the number of tasks in task list.

```
Got it! This task has been squeezed into your basket:
	[D][ ] Submit Lemonade Stand Proposal (by: tomorrow)
Now you have 2 fruitful tasks in your basket!
```

### `event` - Add event task

Adds an event task to the task list. The event task is also stored in a file.

Format: `event <task description> /from <start date/time> /to <end date/time>`

Example of usage:

`event Attend Lemon Festival /from today /to tomorrow`

Expected outcome:

Acknowledges that the task has been added. Displays the added task and the number of tasks in task list.

```
Got it! This task has been squeezed into your basket:
	[E][ ] Attend Lemon Festival (from: today to: tomorrow)
Now you have 3 fruitful tasks in your basket!
```

### `mark` - Mark task as done

Marks a task in the task list as done. The completion status is also updated to the file.

Format: `mark <task number>`

Example of usage:

`mark 1`

Expected outcome:

Acknowledges that the task has been done. Displays the marked task.

```
Great job! This task is now juiced:
	[T][X] Chat with Lemon
```

### `unmark` - Mark task as not done

Unmarks a task in the task list to not done. The completion status is also updated to the file.

Format: `unmark <task number>`

Example of usage:

`unmark 1`

Expected outcome:

Acknowledges that the task has not been done. Displays the unmarked task.

```
No problem! This task is back into the basket:
	[T][ ] Chat with Lemon
```

### `find` - Find task by keyword

Finds tasks with the keyword in the task description.

Format: `find <keyword>`

Example of usage:

`find Lemon`

Expected outcome:

Displays the type of task, completion status, task description, and any applicable date/time of tasks that matches the specified keyword.

```
On it! Here are the tasks you are looking for:
1. [T][ ] Chat with Lemon
2. [E][ ] Attend Lemon Festival (from: today to: tomorrow)
```

### `delete` - Delete task

Deletes a task from the task list. The task is also removed from the file.

Format: `delete <task number>`

Example of usage:

`delete 3`

Expected outcome:

Acknowledges that the task has been deleted. Displays the deleted task and the number of tasks in task list.

```
Got it! This task has been squeezed out of your basket:
	[E][ ] Attend Lemon Festival (from: today to: tomorrow)
Now you have 2 fruitful tasks in your basket!
```

### `bye` - Exit program

Exits the program and stops accepting commands.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

Displays the farewell message and exits from the program.

```
Squeeze the day! Until we meet again, stay fresh and zesty!
```
