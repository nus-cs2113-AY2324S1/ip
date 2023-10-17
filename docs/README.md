
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
    ____________________________________________________________
    You have successfully added the task:
        [T][ ]eat breakfast
    Now you have 5 tasks in the list

    ____________________________________________________________

```

### `deadline` - Add deadline task

By using this command, you can add a deadline task by specifying a date

Example of usage:

`deadline eat breakfast /by 2023-10-10`

Expected outcome:

There will be a feedback showing that you have added a deadline task!

```
    ____________________________________________________________
    You have successfully added the task:
        [D][ ]eat breakfast by: Oct 10 2023
    Now you have 6 tasks in the list

    ____________________________________________________________

```

### `event` - Add event

By using this command, you can add an event by specifying a start date and end date

Example of usage:

`event eat breakfast /from 2023-10-10 /to 2023-10-10`

Expected outcome:

There will be a feedback showing that you have added an event!

```
    ____________________________________________________________
    You have successfully added the task:
        [E][ ]eat breakfast from: Oct 10 2023 to: Oct 10 2023
    Now you have 7 tasks in the list

    ____________________________________________________________

```

### `list` - list all tasks

By using this command, you see all the tasks you have added to FredBot

Example of usage:

`list`

Expected outcome:

The list of all tasks will be displayed cleanly

```
    ____________________________________________________________
    Here are the tasks in your list
    1.[E][X]hi from: Oct 11 2023 to: Oct 11 2023
    2.[D][ ] by: Dec 9 2023
    3.[T][ ]eat breakfast
    4.[T][ ]eat breakfast
    5.[T][ ]eat breakfast
    6.[D][ ]eat breakfast by: Oct 10 2023
    7.[E][ ]eat breakfast from: Oct 10 2023 to: Oct 10 2023

    ____________________________________________________________

```

### `mark` - mark a task as done

By using this command, you can mark a task as done by its index

Example of usage:

`mark 4`

Expected outcome:

There will be a feedback showing that you have marked the task as done

```
    ____________________________________________________________
    Nice! I've marked this task as done:
    [X] eat breakfast
    ____________________________________________________________

```

### `unmark` - mark a task as done

By using this command, you can unmark a task as done by its index

Example of usage:

`unmark 4`

Expected outcome:

There will be a feedback showing that you have marked the task as not done

```
    ____________________________________________________________
    Nice! I've marked this task as not done yet:
    [ ] eat breakfast
    ____________________________________________________________

```

### `delete` - delete a task from the list of tasks

By using this command, you can delete a task from FredBot by its index

Example of usage:

`delete 4`

Expected outcome:

There will be a feedback showing that you have delete the task from FredBot

```
    ____________________________________________________________
    Noted. I've removed this task:
    [T][ ]eat breakfast
    Now you have 6 tasks in the list

    ____________________________________________________________

```

### `find` - find a list of tasks that matches the task descriptions

By using this command, you can find the task you want to see based on the task description

Example of usage:

`find eat`

Expected outcome:

A list of tasks matching 'eat' will be displayed

```
    ____________________________________________________________
    Here are the matching tasks in your list:
    3.[T][ ]eat breakfast
    4.[T][ ]eat breakfast
    5.[D][ ]eat breakfast by: Oct 10 2023
    6.[E][ ]eat breakfast from: Oct 10 2023 to: Oct 10 2023

    ____________________________________________________________

```

### `bye` - exit from FredBot

By using this command, you can close the FredBot program cleanly

Example of usage:

`bye`

Expected outcome:

FredBot says bye :(

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________

```