Table Top Robot
================

Description
------------

TableTopRobot is a simulation of a toy robot moving on a square tabletop. Dimensions of table top can be specified by user.

Allowed Commands
-----------------

* ``PLACE X,Y,F`` - PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.
* ``LEFT`` - LEFT will rotate the robot 90 degrees LEFT without changing the position of the robot.
* ``RIGHT`` - RIGHT will rotate the robot 90 degrees RIGHT without changing the position of the robot.
* ``MOVE`` - MOVE will move the toy robot one unit forward in the direction it is currently facing.
* ``REPORT`` - REPORT will announce the X,Y and F of the robot.
* ``QUIT`` - Only works in case of reading inputs from user. QUIT will stop prompting for user input and will exit the application.

Constraints
------------

* There are no other obstructions on the table surface.
* The robot is free to roam around the surface of the table, but must be prevented from falling to destruction.
* Any movement that would result in the robot falling from the table must be prevented, however further valid movement commands must still be allowed.
* The origin (0,0) can be considered to be the SOUTH WEST most corner.
* The first valid command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application should discard all commands in the sequence until a valid PLACE command has been executed.
* A robot that is not on the table can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands.
* The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot. Any move that would cause the robot to fall must be ignored.

System Requirements
--------------------

* Java 1.8
* Maven 3.0

How To Build & Test
--------------------

This application uses maven for pulling all necessary dependencies. 
::
    mvn clean verify

``TabletopRobotApplicationTest`` will run as part of ``mvn verify``. It contains all the required unit tests in application. 

How To Run
-----------

Application provides 2 classes ``ReadInputFromFile`` and ``ReadInputFromUser`` under ``src/test/java/com/homes/tabletoprobot``.

1. ``ReadInputFromFile`` reads input from file located under ``src/test/java/resources/input.txt``. Edit ``input.txt`` as required. Run the corresponding main method to see output.
2. ``ReadInputFromUser`` is interactive way of communicating. Run the corresponding main method and input commands as per wish. Use command ``QUIT`` to exit the application.

Configurations
---------------

This application also has option of enabling detailed logging or capturing metrics. Those configs can be controlled from ``src/main/resources/config.properties``.

1. Setting ``enableLogging`` to ``true`` will log as each command is executed.
2. Setting ``enableMetrics`` to ``true`` will capture the count of each command executed and will report details on execution of ``REPORT`` command.
