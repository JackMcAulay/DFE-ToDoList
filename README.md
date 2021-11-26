# DFE-ToDoList
## Why are we doing this?
- Create a Simple To Do List project to demonstrte the use of Java and Springboot.
## How I expected the challenge to go.
- I Expected it to go okay as I had experice with programming and the MVP I set was reasonable.
- But I am relitivly new to Java and Springboot, along woth their surrounding paradigms.
  - In particular JUnit Tests.
## What went well? / What didn't go as planned?
- Creating the project went well and it functions as intended.
- All API Calls work as intended.
- Tests have to be run on a separate branch as a line in the Item service has to be ommited for it to run.
  - This issue could be solved but I choose to focus on other issues.
- Combining a command line deployable .jar had multiple issues relating to dependancies and Java JDK/JRE versions.
## Possible improvements for future revisions of the project.
- A GUI to allow a user to easily interact with their To Do List.
- Add security features.
- More Functionality.
  - Add Time.
  - Days/Time until a thing.
  - Allow a user to have Multiple separate Lists.

## API Calls & Screenshots of Postman Requests and Output.
- /createUser
  - Post - Sent with User in JSON format.

![Create User](https://i.imgur.com/I3C5etB.png)

- /createUsers
  - Post - Sent with Users in JSON format.

![Create Usera](https://i.imgur.com/78xAqyp.png)

- /readAllUsers
  - Get.

![Read All Users](https://i.imgur.com/Pd0Qxtm.png)

- /readUserById/{userId}
  - Get - Sent with UserId.

![Read User By Id](https://i.imgur.com/TrRnkb1.png)

- /updateUser/{userId}
  - Post - Sent with UserId.

![Update User](https://i.imgur.com/B8Zry93.png)

- /deleteUser/{userId}
  - Post - Sent with UserId.

![Delete User](https://i.imgur.com/8i9ITck.png)
![Delete User Result](https://i.imgur.com/o8WRjgs.png)

## Screenshots of Database

## Screenshots of Test results and coverage report.
![Test Results](https://i.imgur.com/B8ciKky.png)
![Coverage Report](https://i.imgur.com/SpDhS8N.png)

## Jira Board
[Jira Board](https://jackmcaulay.atlassian.net/jira/software/projects/DT/boards/2)
