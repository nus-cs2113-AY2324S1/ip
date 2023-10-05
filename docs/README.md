# Doli: User Guide

>Doli is a simple **Personal Assistant Chatbot** that helps you keep track of
various things. It can **help setting up an agenda containing todos, deadlines
and events** on which the user is able to perform actions such as marking them as done.
The agenda is stored in a file allowing the user to keep editing a previously
created agenda even after exiting the program.

## Features 

### Adding a todo: `todo`

Adds a task of type todo to the agenda.  
Format: `todo DESCRIPTION`  

Examples:   
- `todo grocery shopping for dinner`
- `todo software engineering assessment`

```
Got it! I've added the following task to your agenda:
	[T] [ ] grocery shopping for dinner
Now you have a total of 1 tasks in your agenda.
```

### Adding a deadline: `deadline`

Adds a task of type deadline to the agenda.  
Format: `deadline DESCRIPTION /DEADLINE`  
- DEADLINE **must be of format yyyy-MM-dd**

>[!NOTE]  
> DEADLINE must be of format yyyy-MM-dd

Examples:
- `deadline leftovers expire /2023-10-10`
- `deadline individual project /2023-10-06`

```
Got it! I've added the following task to your agenda:
	[D] [ ] individual project  (by: Oct 06 2023)
Now you have a total of 2 tasks in your agenda.
```

### Adding an event: `event`

Adds a task of type event to the agenda.  
Format: `event DESCRIPTION /STARTDATE /ENDDATE`
- STARTDATE **must be of format yyyy-MM-dd**
- ENDDATE **must be of format yyyy-MM-dd**

Examples:
- `event trip to Bali /2023-09-10 /2023-09-23`
- `event singapore exchange /2023-08-06 /2023-12-14`

```
Got it! I've added the following task to your agenda:
	[E] [ ] trip to bali  (from: Sep 10 2023, to: Sep 23 2023)
Now you have a total of 3 tasks in your agenda.
```

### Deleting a task: `delete`

Deletes a task from the agenda.  
Format: `delete INDEX`  
- The index needs to be a number contained within the agenda
- You can only delete one task at a time
- **Indexing starts from 1** (not 0)       

Examples:  
- `delete 1`

```
Got it! I've deleted task 1
```

### Deleting all entries: `clear`

Deletes all tasks from the agenda.   
Format: `clear`

```
Got it! I've deleted all tasks. Your agenda is now empty.
```

### Marking a task as done: `mark`

Marks a specific task as done by setting a cross (X).  
Format: `mark INDEX`  
- The index needs to be a number contained within the agenda
- You can only mark one task at a time
- **Indexing starts from 1** (not 0)

Examples:
- `mark 2`

```
I've successfully marked task 2 as done.
Would you like to mark/unmark something else?
```

### Marking a task as not done: `unmark`

Marks a specific task as not done by removing an eventual cross.  
Format: `unmark INDEX`
- The index needs to be a number contained within the agenda
- You can only unmark one task at a time
- **Indexing starts from 1** (not 0)

Examples:
- `unmark 2`

```
I've unmarked task 2. You better get it done soon!
Would you like to mark/unmark something else?
```

### Listing all entries: `list`

Shows a list of all the entries in the agenda.  
Format: `list`

```
Here is an overview of your agenda:
   1. [D] [ ] individual project  (by: Oct 06 2023)
   2. [E] [ ] trip to bali  (from: Sep 10 2023, to: Sep 23 2023)
```

### Listing all entries up to a specific day: `overview`

Shows a list of all entries in the agenda up to a specific date.  
Format: `overview DATE_LIMIT`  
- DATE_LIMIT **must be of format yyyy-MM-dd**  

Examples:
- `overview 2023-10-01`
- `overview 1999-04-28`

```
Here is an overview of your agenda:
   1. [E] [ ] trip to bali  (from: Sep 10 2023, to: Sep 23 2023)
```

### Listing all past due commands: `late`

Shows a list of all the deadlines in the agenda which were already due and all the past events.  
Format: `late`

```
Here's an overview of tasks that were due and past events
   1. [E] [ ] trip to bali  (from: Sep 10 2023, to: Sep 23 2023)
```

### Finding specific tasks: `find`

Searches for a specific keyword within the descriptions of all the elements contained within the agenda.  
Format: `find KEYWORD`
- KEYWORD can potentially range from a single character to multiple words
- The search is **case insensitive**, e.g. `food` will match `Food`
- The order of the keywords does matter

Examples:
- `find project`
- `find math homework`
- `find trip to japan`

```
Here are the tasks matching your input keyword:
   1. [D] [ ] individual project  (by: Oct 06 2023)
```

### Exiting the program: `bye`

Exits the program.  
Format: `bye`

```
Alright, I hope I was able to help you out.
==============================
Thank you for your patience, hope to see you soon! Bye!
```

## Saving the data

The tasks added to the agenda are saved automatically after each command is successfully carried out as a **.txt file**.  
There is no need to save the data manually.
