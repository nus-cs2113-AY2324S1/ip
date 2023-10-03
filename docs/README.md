# User Guide

Chattie is a friendly bot that helps you keep track of your tasks with a list.

## Features


### List all tasks: `list`

Lists all tasks in the task list.

Format: `list`

Example of usage and expected outcome:

```
> list
	____________________________________________________________
	Here are the tasks in your list:
	1. [E][ ] lecture (from: 4 to: 6pm)
	2. [T][ ] read book          
	3. [D][ ] test (by: Mar 5 2011)
	4. [T][ ] CS2113 ip  
	____________________________________________________________
```

### Adding a Todo: `todo`

Adds a todo to the task list.

Format: `todo TASK_DESCRIPTION`

Example of usage and expected outcome:

```
> todo go for a walk
	____________________________________________________________
	Got it. I've added this task:
	  [T][ ] go for a walk
	Now you have 5 tasks in the list.
	____________________________________________________________
```

### Adding a Deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by TASK_DEADLINE`

Example of usage and expected outcome:

```
> deadline Assignment 3 /by Friday 2359
	____________________________________________________________
	Got it. I've added this task:
	  [D][ ] Assignment 3 (by: Friday 2359)
	Now you have 6 tasks in the list.
	____________________________________________________________
```

### Adding an Event: `event`

Adds an event to the task list.

Format: `event TASK_DESCRIPTION /from TASK_START_TIME /to TASK_END_TIME`

Example of usage and expected outcome:

```
> event exercise /from 10am /to 12pm
	____________________________________________________________
	Got it. I've added this task:
	  [E][ ] exercise (from: 10am to: 12pm)
	Now you have 7 tasks in the list.
	____________________________________________________________
```

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete INDEX`

Example of usage and expected outcome:

```
> delete 5
	____________________________________________________________
	Noted. I've removed this task:
	[T][ ] go for a walk
	Now you have 6 tasks in the list.
	____________________________________________________________
```

### Marking a task: `mark`

Sets a task as done.

Format: `mark INDEX`

Example of usage and expected outcome:

```
> mark 6
	____________________________________________________________
	Nice! I've marked this task as done:
	[E][X] exercise (from: 10am to: 12pm)
	____________________________________________________________
```

### Unmarking a task: `unmark`

Sets a task as not done.

Format: `unmark INDEX`

Example of usage and expected outcome:

```
> unmark 6
	____________________________________________________________
	OK, I've marked this task as not done yet:
	[E][ ] exercise (from: 10am to: 12pm)
	____________________________________________________________
```

### Find tasks: `find`

Finds all tasks that matches the query

Format: `find QUERY`

Example of usage and expected outcome:

```
> find book
	____________________________________________________________
	Here are the matching tasks in your list:
	1. [T][ ] read book           
	2. [T][ ] borrow book
	3. [D][ ] return book (by: Oct 11 2023)
	____________________________________________________________
```

### Exit Chattie: `bye`

Ends the chat dialogue.

Format: `bye`

Example of usage and expected outcome:

```
> bye
	____________________________________________________________
	Byeeeee. Hope to see you again soon! :)
	____________________________________________________________
```
