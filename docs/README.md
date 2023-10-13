# List Whisper

List Whisper is your ultimate solution for effortless task management. Whether you're organizing your workday, planning personal projects, or just need a simple way to keep track of your to-do list, List Whisper has you covered.

## Setting up List Whisper

Prerequisites: JDK 11

1. Download the jar file
2. Open command prompt (windows) or terminal (macOS)
3. Move your directory to the jar file
4. run ```java -jar ip.jar```
5. If the setup is correct, you should see something like the below as the output:
   ```
   -----------------------------------------------------
   Hello! I'm ListWhisper
   What can I do for you?
   -----------------------------------------------------
   ```
   
## Vocabulary

| Vocabulary          | Description                                       |
|---------------------|---------------------------------------------------|
| task                | An item representing a specific activity or work to be completed. A task can be a todo, deadline or an event. |
| todo       | A task with no deadline |
| deadline       | A task with a deadline |
| event            | An event with a start and end date and time |

## Features 

### <mark>Important Note</mark>
Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in ```todo TASK_DESCRIPTION```, TASK_DESCRIPTION is a parameter which can be used as add n/assignment

### Listing All Tasks: ```list```
Shows a list of all persons in the address book.
Format: ```list```
Example:
```
    list
    -----------------------------------------------------------
    Here are the tasks in your list:
    1.[T][X] read book
    2.[D][X] return book (by: June 6th)
    ------------------------------------------------------------
```

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
