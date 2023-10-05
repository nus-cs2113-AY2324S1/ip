# XiaoAi User Guide

## Features 

### Feature: Add tasks

You can add your tasks to the task list.

### Feature: Delete tasks

You can delete an existing task from the task list.

### Feature: List tasks

You can list all existing tasks.

### Feature: Mark task as done/not done

You can mark an existing task as done/not done.

### Feature: Find tasks

You can search for tasks using keywords.

## Usage

### `todo` - adds a todo task

usage: `todo <name...>`
example usage: `todo drink some coffee`

### `deadline` - adds a deadline task

usage: `deadline <name...> -by <by...>`
example usage: `deadline submit assignment -by today 7pm`

### `event` - adds an event task

usage: `event <name...> -from <from...> -to <to...>`
example usage: `event go to concert -from 11/7 8pm -to 11/7 10pm`

### `list` - list all existing tasks

usage: `list`

### `delete` - delete a task with index

usage: `delete <index>`
example usage: `delete 3`

### `mark` - mark a task as done

usage: `mark <index>`
example usage: `mark 3`

### `unmark` - mark a task as not done

usage: `unmark <index>`
example usage: `unmark 3`

### `find` - find all tasks with name containing the keywords

usage: `find <keyword...>`
example usage: `find read books`

### `bye` - stop the bot and quit

usage: `bye`

