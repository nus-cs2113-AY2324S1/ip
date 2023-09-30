# User Guide

## Features 

### Listing All Tasks: ```list```
Shows a list of all persons in the address book.
Format: ```list```

### Adding Todo
Adds a new todo task in your list.
Format: ```todo TASK_DESCRIPTION```

### Adding Deadline
Adds a new task with deadline in your list.
Format: ```deadline TASK_DESCRIPTION /by DEADLINE```

### Adding Event
Adds a new event in your list.
Format: ```event EVENT_DESCRIPTION /from START_TIME /to END_TIME```

### Marking Task
Marks a task in your list as done.
Format: ```mark TASK_INDEX```

### Unmarking Task
Unmarks a task in your list as done.
Format: ```unamrk TASK_INDEX```

### Deleting Task
Deletes a task from your list.
Format: ```delete TASK_INDEX```

### Find Task
Find tasks with matching keywords in your list.
Format: ```find KEY_WORD```

Example:
```
    find book
    -----------------------------------------------------------
    Here are the matching tasks in your list:
    1.[T][X] read book
    2.[D][X] return book (by: June 6th)
    ------------------------------------------------------------
```

### Exiting
Exit the application.
Format: ```bye```
