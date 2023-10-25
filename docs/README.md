# User Guide - Cheezeblokz

Cheezeblokz is an app for management and recording of simple tasks required by the user.

## Features 

> [!NOTE]
> - Words in UPPER_CASE are the parameters to be supplied by the user. 
> e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.
> - Parameters must be in the specified order.

- [Features](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#features)
  - [Adding a todo: `todo`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#adding-a-todo-todo)
  - [Adding a deadline: `deadline`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#adding-a-deadline-deadline)
  - [Adding an event: `event`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#adding-an-event-event)
  - [Deleting a task: `delete`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#deleting-a-task-delete)
  - [Listing all tasks: `list`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#listing-all-tasks-list)
  - [Marking a task: `mark`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#marking-a-task-mark)
  - [Unmarking a task: `unmark`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#unmarking-a-task-unmark)
  - [Searching for tasks: `find`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#searching-for-tasks-find)
  - [Exiting the program: `bye`](https://github.com/Cheezeblokz/ip/blob/master/docs/README.md#exiting-the-program-bye)

### Adding a todo: `todo`

Add a todo task to the task list.

Format: `todo TODO_NAME`

Example:
- `todo CS2113 Assigment`

### Adding a deadline: `deadline`

Add a deadline task to the task list.

Format: `deadline DEADLINE_NAME /by DEADLINE_BY`

Example:
- `deadline CS2113 Assigment /by 20th Oct 12pm`

### Adding an event: `event`

Add an event task to the task list.

Format: `event EVENT_NAME /from EVENT_FROM /to EVENT_TO`

Example:
- `event CS2113 Tutorial /from 20th Oct 10am /to 20th Oct 12pm`

### Deleting a task: `delete`

Delete a task in the task list.

Format: `delete TASK_INDEX`

- Deletes the task at the specified `TASK_INDEX`.
- The index refers to the assigned list number in the displayed list for command `list`.

Example:
- `delete 2`

### Listing all tasks: `list`

List out all tasks in the task list.

Format: `list`

### Marking a task: `mark`

Mark a task in the task list.

Format: `mark TASK_INDEX`

- Marks the task at the specified `TASK_INDEX`.
- The index refers to the assigned list number in the displayed list for command `list`.

Example:
- `mark 2`

### Unmarking a task: `unmark`

Unmark a task in the task list.

Format: `unmark TASK_INDEX`

- Unmarks the task at the specified `TASK_INDEX`.
- The index refers to the assigned list number in the displayed list for command `list`.

Example:
- `unmark 2`

### Searching for tasks: `find`

Look for a task in the task list by name.

Format: `find NAME`

- The search is case sensitive and finds the tasks in the list with the exact name as the input.
- Only the name is searched.

Example:
- `find CS2113 Assigment`

### Exiting the program: `bye`

Format: `bye`

Exit the program.