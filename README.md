# Tum ChatBot

The Tum ChatBot is an application that can be used as a memorandum through the Command Line Interface or JAR Files. If you have anything to do or some important appointments, you can store them in Tum Chat and a text file regarding your tasks will be automatically generated so that you can be aware of what has been done and what should be done.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
____________________________________________________________
 Hello! I'm TUM
 What can I do for you?
____________________________________________________________
   ```
## Quick Start
1. Ensure you have Java 11 installed in your Computer.
2. Download the latest IP.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Tum ChatBot.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar IP.jar command to run the application.
   In a few seconds, it should show that:
<img width="566" alt="Screenshot 2023-10-04 at 12 20 58" src="https://github.com/Haoyuli2002/ip/assets/139958049/cade8150-21a1-43be-8d82-6e59374f4c7f">
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
Some example commands you can try:

-list: Lists all contacts.

-todo: todo return book

-delete 3: Deletes the 3rd task shown in the current list.

-clear: Deletes all contacts.

-bye: Exits the app.







