# Bobby

## User Guide
Bobby is a `CLI` chatbot built for busy individuals who struggle to keep track
of their daily tasks.
Built for fast typists, it allows information allocation and retrival faster
an application with a traditional UI.

## Quick Start
1. Ensure the Java version on your computer is Java 11.
2. Download the latest `.jar` file from the GitHub [repo](https://github.com/farissirraj/ip/releases).
3. Navigate your terminal to the location of the `.jar` file and run: `java -jar ip.jar`
4. Type the commands and get started.

## Features
### Add a Task
Users can choose between creating a `todo`, `deadline` or an `event` object to add to their list.
#### Todo: `todo <task>`

    todo Gym Leg Day
        ____________________________________________________________
        Got it. I've added this task:
          [T][ ] Gym Leg Day
        Now you have 1 tasks in the list.
        ____________________________________________________________

#### Deadline: `deadline <task> /by <deadline>`

    deadline CS2113 iP /by Friday 2359
        ____________________________________________________________
        Got it. I've added this task:
          [D][ ] CS2113 iP (by:Friday 2359)
        Now you have 1 tasks in the list.
        ____________________________________________________________

#### Event: `event <task> /from <from_time> /to <to_time>`
    
    event CS2113 lecture /from 4pm /to 6pm
        ____________________________________________________________
        Got it. I've added this task:
          [E][ ] CS2113 lecture  (from: 4pm to: 6pm)
        Now you have 2 tasks in the list.
        ____________________________________________________________

### Delete a Task: `delete <index>`
Deletes a task based on the index entered.

    delete 1
        ____________________________________________________________
        Noted. I've removed this task:
          [D][ ] CS2113 iP (by:Friday 2359)
        Now you have 1 tasks in the list.
        ____________________________________________________________        

### Mark and Unmark a Task
Allows the user to check `[X]` or uncheck `[ ]` a task so he knows what is
completed and what is not.
- `mark 1` will check the first item on the list.


    mark 1
    ____________________________________________________________
    Nice! I've marked this task as done:
       [X] CS2113 HW
    ____________________________________________________________
- `unmark 2` will uncheck the second item on the list.

                                                               
    unmark 2
    ____________________________________________________________
    OK, I've marked this task as not done yet:
       [ ] CS2113 HW
    ____________________________________________________________

### List the tasks: `list`
Lists all the tasks in the list.

    list
        ____________________________________________________________
        1.[T][ ] CS2113 HW
        2.[E][ ] CS2113 Lecture  (from: 4pm to: 6pm)
        3.[D][ ] iP Final Submission (by:Friday 2359)
        ____________________________________________________________


### Find a task: `find <keyword>`
Finds all the task whose description contains the keyword entered.

    find CS2113
        ____________________________________________________________
        7.[T][ ] CS2113 HW
        ____________________________________________________________


### Exit the Chatbot
By exiting the chatbot, all the tasks added in this run of the chatbot will be
saved to a `.txt` file on the computer so that it will load up for the future runs.

    bye
        ____________________________________________________________
        Bye. Hope to see you again soon!
        ____________________________________________________________