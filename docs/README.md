# User Guide
Nuke is a desktop app for managing tasks using CLI (Command Line Interface).

## Features 

### Exiting: `bye`

Exits program while saving the tasks on the file system.

### Adding a task: `todo`

Adds a task to the list.

### Adding a task with a deadline: `deadline`

Adds a task with a deadline to the list.

### Adding an event: `event`

Adds an event to the list.

### Viewing all tasks: `list`

Shows a list of tasks in the list.

### Marking a task as done: `mark`

Marks a task as done.

### Marking a task as not done: `unmark`

Marks a task as not done.

### Deleting a task: `delete`

Deletes a task in the list.

### Finding a task by keyword: `find`

Finds a task in the list by a keyword.


## Usage

### `bye` - Exits program

Exits program while saving the tasks on the file system.

Example of usage:

`bye`

Expected outcome:

```
[@] Bye. Hope to see you again soon!
```

### `todo` - Adds a task

Adds a task to the list.

Example of usage:

`todo <NAME>`

Expected outcome:

If input is `todo read book`,

```
[@] Got it. I've added this task:
  [T][ ] read book
[@] Now you have 1 task in the list.
```

### `deadline` - Adds a task with a deadline

Adds a task with a deadline to the list.

Example of usage:

`deadline <NAME> /by <DEADLINE>`

Expected outcome:

If input is `deadline return book /by June 6th`,

```
[@] Got it. I've added this task:
  [D][ ] return book (by: June 6th)
[@] Now you have 2 tasks in the list.
```

### `event` - Adds an event

Adds an event to the list.

Example of usage:

`event <NAME> /from <START> /to <END>`

Expected outcome:

If input is `event project meeting /from Aug 6th 2pm /to 4pm`,

```
[@] Got it. I've added this task:
  [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
[@] Now you have 3 tasks in the list.
```

### `list` - Views all tasks

Shows a list of tasks in the list.

Example of usage:

`list`

Expected outcome:

```
[@] Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
```

### `mark` - Marks a task as done

Marks a task as done.

Example of usage:

`mark <INDEX>`

Expected outcome:

If input is `mark 1`,

```
[@] Nice! I've marked this task as done:
  [T][X] read book
```

### `unmark` - Marks a task as not done

Marks a task as not done.

Example of usage:

`unmark <INDEX>`

Expected outcome:

If input is `unmark 3`,

```
[@] OK, I've marked this task as not done yet:
  [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
```

### `delete` - Deletes a task

Deletes a task in the list.

Example of usage:

`delete <INDEX>`

Expected outcome:

If input is `delete 3`,

```
[@] Noted. I've removed this task:
  [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
[@] Now you have 2 tasks in the list.
```

### `find` - Finds a task by keyword

Finds a task in the list by a keyword.

Example of usage:

`find <KEYWORD>`

Expected outcome:

If input is `find book`,

```
[@] Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
```
