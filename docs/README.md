
# FredBot's User Guide

## Features 

### Feature-Add Todo

Add a generic to-do task that you plan to do in the future

### Feature-Add deadline

Add a deadline task that you wish to complete by a specific date.
Date has to be in the format of YYYY-MM-DD

### Feature-Add event

Add an upcoming event that you are having in the future
You will need to specify a start date and end date.
Date has to be in the format of YYYY-MM-DD

### Feature-List all tasks

List all tasks that you have added to FredBot (todo, deadline, event)

### Feature-Mark tasks as done

You can mark a certain task as done by specifying which task you want to be mark as done

### Feature-Unmark tasks as done

If you have made a mistake of marking a task as done, you can "unmark" it.

### Feature-Find tasks

You can search for any task you want using the task description.

### Feature-Delete tasks

YOu can completely delete any tasks that you have added by accident

## Usage

### `todo` - Add todo task

By using this command, you can add a generic todo task

Example of usage: 

`todo eat breakfast`

Expected outcome:

There will be a feedback showing that you have added a todo task!

```
expected output
```

### `deadline` - Add deadline task

By using this command, you can add a deadline task by specifying a date

Example of usage:

`todo eat breakfast /by 2023-10-10`

Expected outcome:

There will be a feedback showing that you have added a deadline task!

```
expected output
```

### `event` - Add event

By using this command, you can add an event by specifying a start date and end date

Example of usage:

`todo eat breakfast /from 2023-10-10 /to 2023-10-10`

Expected outcome:

There will be a feedback showing that you have added an event!

```
expected output
```

### `list` - list all tasks

By using this command, you see all the tasks you have added to FredBot

Example of usage:

`list`

Expected outcome:

The list of all tasks will be displayed cleanly

```
expected output
```

### `mark` - mark a task as done

By using this command, you can mark a task as done by its index

Example of usage:

`mark 1`

Expected outcome:

There will be a feedback showing that you have marked the task as done

```
expected output
```

### `unmark` - mark a task as done

By using this command, you can unmark a task as done by its index

Example of usage:

`unmark 1`

Expected outcome:

There will be a feedback showing that you have marked the task as not done

```
expected output
```

### `delete` - delete a task from the list of tasks

By using this command, you can delete a task from FredBot by its index

Example of usage:

`delete 1`

Expected outcome:

There will be a feedback showing that you have delete the task from FredBot

```
expected output
```

### `find` - find a list of tasks that matches the task descriptions

By using this command, you can find the task you want to see based on the task description

Example of usage:

`find eat`

Expected outcome:

A list of tasks matching 'eat' will be displayed

```
expected output
```

### `bye` - exit from FredBot

By using this command, you can close the FredBot program cleanly

Example of usage:

`bye`

Expected outcome:

FredBot says bye :(

```
expected output
```