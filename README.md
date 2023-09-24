# Remy

## Setting up in your IDE

Prerequisites: JDK 11.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   __________                      
    \______   \ ____   _____ ___.__.
     |       _// __ \ /     <   |  |
     |    |   \  ___/|  Y Y  \___  |
     |____|_  /\___  >__|_|  / ____|
            \/     \/      \/\/     

    Hello! I'm Remy
    What can I do for you?
   ```
## Features

Remy is a task tracker that will help you with managing your tasks as well as being able to mark them as complete/incomplete.

### Bye
Command: `bye`
This command tells Remy goodbye and exits the application.

### List
Command: `list`
This command lists out all your current tasks for you to view.

### Tasks
There are 3 different types of tasks, each with their own unique way of creation
Command: `todo [TASK_DESCRIPTION]`
Example: `todo Wash Laundry`
This command creates a "todo" task with everything following the word `todo` being the description of the task

Command: `deadline [TASK_DESCRIPTION] /by [DEADLINE_DATE]`
Example: `deadline Finish Assignment 1 /by January 7`
This command creates a "deadline" task with everything between the words `deadline` and `/by` consisting of the task description
and everything following the word `/by` being the deadline date of the task.

Command `event [TASK_DESCRIPTION] /from [DATE_FROM] /to [DATE_TO]`
Example: `event Music Concert /from August 16 /to August 18`
This command creates an "event" task with everything between the words `event` and `/from` consisting of the task description,
everything between the words `/from` and `/to` consisting of the starting date of the event, and everything following the word
`/to` consisting of the end date of the event.

### Marking Tasks
All tasks can either be marked as done, or marked as undone.
Command: `mark [TASK_NUMBER]`
Marks the nth task in the list as done.

Command: `unmark [TASK_NUMBER]`
Marks the nth task in the list as not done.

### Deleting Tasks
Command: `delete [TASK_NUMBER]`
Deletes the nth task in the list.

### Finding Tasks
Command: `find [KEYWORD]`
Finds all Tasks in the list whose description contains the specified KEYWORD and returns them all to the user.

### All other commands
Remy will let the user know an invalid command is input by explaining that the user's input was not understood.
