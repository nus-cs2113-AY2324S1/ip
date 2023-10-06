# Nupjuk User Guide

Nupjuk is a desktop app for schedule management.

It uses Command Line Interface.

Your tasks(todo, deadline, event) are added in a list.

Updates in list are automatically saved in `nupjuk.txt`.

## Features

### Adding a task: `todo`

Adds a task to the list.

Format: `todo DESCRIPTION`

> Tip: `DESCRIPTION` can contains multiple words

Examples: 

- `todo borrow book`

### Adding a task with deadline: `deadline`

Adds a task with deadline to the list.

Format: `deadline DESCRIPTION /by TIME`

> Tip: `TIME` should be in the format: `dd-MM-yyyy HHmm`

Examples:

- `deadline return book /by 09-10-2023 1800`


### Adding a task with start and end: `event`

Adds a task with start and end time to the list.

Format: `event DESCRIPTION /from START /to END

> Tip: `START` and `END` should be in the format: `dd-MM-yyyy HHmm`

Examples:

- `event CS2113 Project /from 23-09-2023 1200 /to 12-11-2023 2359`

### Showing the list of tasks: `list`

Shows all the tasks in the list.

Format: `list`

### Marking the finished task: `Mark`
