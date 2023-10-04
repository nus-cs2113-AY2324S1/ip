# User Guide
PLOC is a desktop app for managing tasks using CLI (Command Line Interface). Note commands are case-sensitive. So 'Bye' will exit the programme while 'bye' will not

## Features

### Exiting: `Bye` 

Exits program while saving the tasks on the file system. 

Format: `Bye`

### Adding a task: `todo`

Adds a single task to the list.

Format: `todo <NAME>`

### Adding a task with a deadline: `deadline`

Adds a single task with a deadline to the list.

Format: `deadline <NAME OF TASK> /by <DEADLINE>`

### Adding an event: `event`

Adds a single event to the list.

Format: `event <NAME> /from <START> /to <END>`

### Viewing all tasks: `list`

Shows a list of tasks in the list.

Format: `list`

### Marking a task as done: `mark`

Marks one task as done.

Format: `mark <INDEX>`

### Marking a task as not done: `unmark`

Marks one task as not done.

Format: `unmark <INDEX>`

### Deleting a task: `delete`

Deletes one task in the list.

Format: `delete <INDEX>`

### Finding a task by keyword: `find`

Finds one task in the list by a keyword. Only works for single keywords and not phrases.

Format: `find <KEYWORD>`
