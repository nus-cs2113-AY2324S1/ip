# User Guide: Zran
Zran acts as your personal assistant and keeps track of your task list!

# Features
### Adding a ToDo Task: `todo`

Adds a todo task to the task list.
Format: `todo TASK_DESCRIPTION`
Expected Output:
```
todo Cook lunch
    ____________________________________________________________
    Noted! Task added: Cook lunch [ ]
    Number of Tasks: 1
    ____________________________________________________________
    List of Tasks:
    1. [T][ ] Cook lunch
    ____________________________________________________________
```

### Adding a Deadline Task: `deadline`

Adds a deadline task to the task list.
Format: `deadline TASK_DESCRIPTION /by TASK_DEADLINE`
Expected Output:
```
deadline Submit ST2334 quiz /by 2023-10-04
    ____________________________________________________________
    Noted! Task added: Submit ST2334 quiz [ ]
    Number of Tasks: 2
    ____________________________________________________________
    List of Tasks:
    1. [T][ ] Cook lunch
    2. [D][ ] Submit ST2334 quiz (by: 04 Oct 2023)
    ____________________________________________________________
```

### Adding a Event Task: `event`

Adds an event task to the task list.
Format: `event EVENT_DESCRIPTION /from START_OF_EVENT /to END_OF_EVENT`
Expected Output:
```
event Rugby competition /from 2023-10-14 /to 2023-10-15
    ____________________________________________________________
    Noted! Task added: Rugby competition [ ]
    Number of Tasks: 3
    ____________________________________________________________
    List of Tasks:
    1. [T][ ] Cook lunch
    2. [D][ ] Submit ST2334 quiz (by: 04 Oct 2023)
    3. [E][ ] Rugby competition (from: 14 Oct 2023 to: 15 Oct 2023)
    ____________________________________________________________
```

### List all Tasks: `list`

Lists all tasks in the task list.
Format: `list`
Expected Output:
```
list
    ____________________________________________________________
    List of Tasks:
    1. [T][ ] Cook lunch
    2. [D][ ] Submit ST2334 quiz (by: 04 Oct 2023)
    3. [E][ ] Rugby competition (from: 14 Oct 2023 to: 15 Oct 2023)
    ____________________________________________________________
```

### Marking a Task: `mark`

Marks the task corresponding to provided task index as done on the task list.
Format: `mark TASK_INDEX`
Expected Output:
```
mark 1
    ____________________________________________________________
    Congrats! :D Task marked as done: Cook lunch [X]
    ____________________________________________________________
```

### Unmarking a Task: `unmark`

Marks the task corresponding to provided task index as NOT done on the task list.
Format: `unmark TASK_INDEX`
Expected Output:
```
unmark 1
    ____________________________________________________________
    Oopsies! Task unmarked: Cook lunch [ ]
    ____________________________________________________________
```

### Deleting a task: `delete`

Removes the task corresponding to provided task index from the task list.
Format: `delete TASK_INDEX`
Expected Output:
```
delete 1
    ____________________________________________________________
    Noted! Task deleted: Cook lunch
    Number of Tasks: 2
    ____________________________________________________________
    List of Tasks:
    1. [D][ ] Submit ST2334 quiz (by: 04 Oct 2023)
    2. [E][ ] Rugby competition (from: 14 Oct 2023 to: 15 Oct 2023)
    ____________________________________________________________
```

### Adding a Event Task: `find`

Filters out tasks containing user's input of choice/keyword.
Format: `find KEYWORD`
Expected Output:
```
find quiz
    ____________________________________________________________
    List of Tasks:
    1. [D][ ] Submit ST2334 quiz (by: 04 Oct 2023)
    2. [D][ ] Submit IE quiz (by: 08 Oct 2023)
    ____________________________________________________________
```

### Adding a Event Task: `help`

Outputs a list of commands and its format of usage.
Format: `help`
Expected Output:
```
help
    ____________________________________________________________
    Help is here! :) 
    Listed below are the valid commands: 
    - todo 
    - deadline 
    - event 
    - mark 
    - unmark 
    - delete 
    - find 
    - help 
    - bye 
    For the formats below, replace *field* with your input 
    TODO: todo *task name* 
    DEADLINE: deadline *task name* /by *deadline* 
    EVENT: event *event name* /from *start date* /to *end date* 
    LIST: list
    MARK: mark *existing task index* 
    UNMARK: unmark *existing task index* 
    DELETE: delete *existing task index* 
    FIND: find *keyword*
    HELP: help
    BYE: bye
    ____________________________________________________________
```

### Adding a Event Task: `bye`

Exits the chatbot.
Format: `bye`
Expected Output:
```
bye
    ____________________________________________________________
    Goodbye <3 Have a great day ahead!
```

## Command Summary
| Action       | Format, Examples                                                                                                                        |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| Add todo     | `todo TASK_DESCRIPTION`  <br/>example: `todo Cook lunch`                                                                                |
| Add deadline | `deadline TASK_DESCRIPTION /by TASK_DEADLINE`  <br/>example: `deadline Submit ST2334 quiz /by 2023-10-04`                               |
| Add event    | `event EVENT_DESCRIPTION /from START_OF_EVENT /to END_OF_EVENT` <br/>example: `event Rugby competition /from 2023-10-14 /to 2023-10-15` |
| List         | `list`               <br/>example: `list`                                                                                               |
| Mark task    | `mark TASK_INDEX`    <br/>example: `mark 1`                                                                                             |
| Unmark task  | `unmark TASK_INDEX`  <br/>example: `unmark 1`                                                                                           |
| Delete task  | `delete TASK_INDEX`  <br/>example: `delete 1`                                                                                           |
| Find         | `find KEYWORD`       <br/>example: `find quiz`                                                                                          |
| Help         | `help`               <br/>example: `help`                                                                                               |
| Exit         | `bye`                <br/>example: `bye`                                                                                                |

