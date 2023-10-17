# User Guide
## Hi there! I am KenergeticBot
I am a personal Task managing chatbot, able to store tasks such as To dos, Deadlines and Events.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/KenergeticBot.java` file, right-click it, and choose `Run KenergeticBot.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
    
       ____________________________________________________________
        Hello! I'm KenergeticBot
        What can I do for you?
       ____________________________________________________________


## Features 

> **Notes about command format:**
> - Words encased in `{ }` are the arguments to be supplied by user. <br>
    e.g. `deadline {Description} /by {Deadline Date & Time}`, `Description` and `Deadline Date & Time` are arguments to be specified by user.
> - Parameters need to be in the exact format as specified. <br>
    e.g. `event {Description} /from {Event Start Date & Time} /to {Event End Date & Time}`, `/from` must come before `/to`.
### Here are some of the things I can do!

### Task Creation

Create 3 types of Tasks:

1. Todo: Tasks which are not time sensitive.
   
    Format: `todo {Description}`

    Example: <br> `todo read book`
2. Deadline: Tasks which are time sensitive, able to indicate deadline using /by.

   Format: `deadline {Description} /by {Deadline Date & Time}`
   
   Example: <br> `deadline return book /by Sunday`
3. Event: Tasks which are happening at a specific time frame, using /from and /to.

   Format: `event {Description} /from {Event Start Date & Time} /to {Event End Date & Time}`

   Example: <br> `event project meeting /from Mon 2pm /to 4pm`


### Task Viewing

Lists all the task in the task list.

Format: `list`


    list    
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
    ____________________________________________________________


### Task Marking

Keep track of Task completion.
1. '[X]' Indicates Tasks that are completed, using mark command.
2. '[ ]' Indicates Tasks that are not completed, using unmark command.

Format: `mark {task index}`

Example: 

    mark 2
    ____________________________________________________________
     Nice! I've marked this task as done:
       [D][X] return book (by: Sunday)
    ____________________________________________________________


Format: `unmark {task index}`

Example:

    unmark 2
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [D][ ] return book (by: Sunday)
    ____________________________________________________________


### Task Saving
Store and retrieve your tasks into a dedicated save file.
```
   Loading previous list
   Successfully wrote to the file.
```

### Task Finding

Search for keyword using the Find function.

Format: `find {keyword}`

    find book
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sunday)
    ____________________________________________________________


### Task Deletion

Remove stored Tasks using the Delete function.

Format: `delete {task index}`

    delete 2
    ____________________________________________________________
     Noted. I've removed this task:
       [D][ ] return book (by: Sunday)
     Now you have 1 tasks in the list.
    ____________________________________________________________


### End Program
Terminates the chatbot program.

Format: `bye`

