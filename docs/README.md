# BotBuddy

This is a simple CLI todo app written in Java.
## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

1. Download the latest ip.jar from the Releases page.

1. Copy the file to the folder you want to use as the home folder for this application.

1. Open a terminal, cd into the folder you put the jar file in, and use the java -jar ip.jar command to run the application.
 
```
____________________________________________________________
Hello from BotBuddy!
What can I do for you?
____________________________________________________________
   ```
5. Refer to the Features below for details of each command.

## Features
Words in UPPER_CASE are the parameters to be supplied by the user.

Typing any invalid command will show a list of valid commands.

### 1. Adding a todo
Adds a todo to the task list. 

Fields: description

Syntax:
```
todo DESCRIPTION
```
Example:
```
____________________________________________________________
todo buy milk
____________________________________________________________
Got it, I've added this task:
[T][ ] buy milk
____________________________________________________________
```

### 2. Adding an event
Adds an event to the task list.

Fields: description, from, to

Syntax:
```
todo DESCRIPTION /from FROM_WHEN /to TO_WHEN
```
Example:
```
____________________________________________________________
event My Holiday /from Wednesday /to Friday
____________________________________________________________
Got it, I've added this task:
[E][ ] My Holiday (from: Wednesday to: Friday)
____________________________________________________________
```

### 3. Adding a deadline
Adds a deadline to the task list.

Fields: description, by

Syntax:
```
todo DESCRIPTION /by BY_WHEN
```
Example:
```
____________________________________________________________
deadline Submit Project /by this Friday
____________________________________________________________
Got it, I've added this task:
[D][ ] Submit Project (by: this Friday)
____________________________________________________________
```

### 4. List tasks
List all the tasks currently in the task list

Fields: none

Syntax:
```
list
```
Example:
```
____________________________________________________________
list
____________________________________________________________
1. [T][X] this is a todo
2. [E][X] an event (from: 1 oct to: 2 oct)
3. [D][ ] submit project (by: friday)
4. [T][ ] buy milk
5. [E][ ] My Holiday (from: Wednesday to: Friday)
6. [D][ ] Submit Project (by: this Friday)
____________________________________________________________
```

The first column indicates if the task is a `[T]` todo, `[E]` event, or a `[D]` deadline.

An `[X]` in the second column indicates that the task is marked as done.

### 5. Mark a task as done
Marks a task in the task list as done.

Fields: task number

Syntax:
```
mark TASK_NUMBER
```
Example:
```
____________________________________________________________
mark 4
____________________________________________________________
I've marked this task as done:
[T][X] buy milk
____________________________________________________________
```

### 6. Unmark a task
Unmarks a task in the task list i.e. it is undone.

Fields: task number

Syntax:
```
unmark TASK_NUMBER
```
Example:
```
____________________________________________________________
unmark 4
____________________________________________________________
I've unmarked this task:
[T][ ] buy milk
____________________________________________________________
```

### 7. Delete a task
Deletes a task from the task list.

Fields: task number

Syntax:
```
delete TASK_NUMBER
```
Example:
```
____________________________________________________________
delete 4
____________________________________________________________
I've deleted this task:
[T][ ] buy milk
____________________________________________________________
```

### 8. Find a task
Searches for a task in the task list. Case sensitive.

Fields: search string

Syntax:
```
find SEARCH_STRING
```
Example:
```
____________________________________________________________
find book
____________________________________________________________
Here are the found tasks for 'book':
____________________________________________________________
____________________________________________________________
1. [T][X] read a book
5. [D][ ] return book (by: 7 oct)
____________________________________________________________
```

### 9. Exit program
Exits the program

Syntax:
```
bye
```
Example: 
```
____________________________________________________________
bye
____________________________________________________________
Goodbye, hope to see you again soon!
____________________________________________________________
```

### Saving data
Task list data is saved in a file automatically after any command that changes the data.

There is no need to save manually.

### Editing the data file
AddressBook data are saved automatically as a txt file [JAR file location]/data/taskfile.txt. 
Advanced users are welcome to update data directly by editing that data file.