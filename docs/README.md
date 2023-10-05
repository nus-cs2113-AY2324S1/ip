# User Guide for Duchess Task Manager

## Features 

### Task Management:

The following features are available in Duchess:
* [Add a task](#add-a-task)
* [Delete a task](#delete-a-task)
* [Mark a task as done](#mark-a-task-as-done)
* [Unmark a task as done](#unmark-a-task-as-done)
* [List all tasks](#list-all-tasks)
* [Find a task](#find-a-task)
* [Exit the program](#exit-the-program)
* Save data
* Load data

### Task Types:

Duchess allows you to manage your tasks by adding, deleting, and marking them as done.
Tasks can be of three types (as of this version): 
- [x] [Todo](#todo)
- [x] [Deadline](#deadline)
- [x] [Event](#event)


## Usage:

> [!NOTE]
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
> - Parameters must be in the order specified.
>   e.g. `deadline` must be supplied with both `/by` and `DESCRIPTION` parameters.
> - Parameters must be supplied in full.
>   e.g. `event` must be supplied with both `/from` and `/to` parameters.
> - Commands are case-sensitive.
>   e.g. `todo` must be in all lowercase.
> - Duchess will create a new save file if it cannot find an existing one.
> - Duchess will load the save file if it exists.
> - Duchess will only save the task list when the user exits the program with the `bye` command.


### Add a task

Adds a task to the task list depending on the command.

### todo
### `todo DESCRIPTION` - Adds a simple to-do task to the task list

Example of usage:
`todo read book`

Expected outcome:
```
        ____________________________________________________________

        ____________________________________________________________
         At your command, goshujin-sama:
[T][ ] read book
        ____________________________________________________________

        ____________________________________________________________

         You have 1 tasks to do, haiyaku!!!
```

### deadline
### `deadline DESCRIPTION /by TIME` - Adds a deadline task to the task list

Example of usage:
`deadline return book /by 2020-09-18`

Expected outcome:
```
        ____________________________________________________________

        ____________________________________________________________
         At your command, goshujin-sama:
[D][ ] return book (by: Sep 18 2020 0)
        ____________________________________________________________

        ____________________________________________________________

         You have 2 tasks to do, haiyaku!!!
```

### event
### `event DESCRIPTION /from START_TIME /to END_TIME` - Adds an event task to the task list

Example of usage:
`event project meeting /from 2020-09-18 14:00 /to 2020-09-18 16:00`

Expected outcome:
```
        ____________________________________________________________

        ____________________________________________________________
         At your command, goshujin-sama:
[E][ ] project meeting (from: 2020-09-18 14:00 to: 2020-09-18 16:00)
        ____________________________________________________________

        ____________________________________________________________

         You have 3 tasks to do, haiyaku!!!
```

### Delete a task
### `delete TASK_INDEX` - Deletes a task from the task list

Example of usage:
`delete 1`

Expected outcome:
```
        ____________________________________________________________

        ____________________________________________________________
         I've removed this task for you, goshujin-sama:
         [T][ ] read book
        ____________________________________________________________

        ____________________________________________________________

         You have 2 tasks to do, haiyaku!!!
```


### Mark a task as done
### `mark TASK_INDEX` - Marks a task as done

Example of usage:
`mark 1`

Expected outcome:
```
        ____________________________________________________________
         Congwats on finishing za tawsk: 1.
[D][X] return book (by: Sep 18 2020 0)
        ____________________________________________________________

```

### Unmark a task as undone
### `unmark TASK_INDEX` - Unmarks a task as done

Example of usage:
`unmark 1`

Expected outcome:
```
        ____________________________________________________________
         WHY HAVE YOU FORSAKEN ME
         I mean, I've unmarked this task for you, goshujin-sama: 1.
[D][ ] return book (by: Sep 18 2020 0)
        ____________________________________________________________

        ____________________________________________________________
```

### List all tasks
### `list` - Lists all tasks

Example of usage:
`list`

Expected outcome:
```
        ____________________________________________________________
1. [D][ ] return book (by: Sep 18 2020 0)
2. [E][ ] project meeting (from: 2020-09-18 14:00 to: 2020-09-18 16:00)
        ____________________________________________________________

        ____________________________________________________________
```

### Find a task
### `find DESCRIPTOR` - Finds a task

Example of usage:
`find book`

Expected outcome:
```
        ____________________________________________________________
         At your command, goshujin-sama: [D][ ] return book (by: Sep 18 2020 0)
        ____________________________________________________________

        ____________________________________________________________
```

### Exit the program
### `bye` - Exits the program

Example of usage:
`bye`

Expected outcome:
```
        ____________________________________________________________
         Bye. I know you don't want me. Never come back.
        ____________________________________________________________

        ____________________________________________________________
```
