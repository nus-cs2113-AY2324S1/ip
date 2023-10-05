# KevBot Guide

## Features 

### List

List all outstanding todos, deadlines, and events.

### Find

Filter the listed tasks by a specific keyword(s).

### Mark

Mark a task as completed/incomplete.

### Delete

Delete a specific task.

### Add

Add a new todo, deadline, or event task.

## Usage

### `list` - List all tasks

Example of usage: 

`list`

### `find` - Match tasks by keyword

Example of usage:

`find (keyword)`

### `mark` - Mark a task
Marks an undone task as done and vice versa. Task index is 1-based as displayed in the list command.

Example of usage:

`mark (task index)`

### `delete` - Remove a task
Task index is 1-based as displayed in the list command.

Example of usage:

`delete (task index)`

### `todo` - Add a todo

Example of usage:

`todo (description)`

### `deadline` - Add a deadline

Example of usage:

`deadline (description) /by (end time)`

### `event` - Add an event

Example of usage:

`event (description) /from (start time) /to (end time)`