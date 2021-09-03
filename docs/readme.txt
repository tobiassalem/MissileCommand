##################################################################################
### Subject: Work sample
### Application: Simple Java implementation of the classic game Missile Command
### Original game: http://www.klov.com/game_detail.php?letter=M&game_id=8715
###
### Author: Tobias Salem
### Version: 1.0
###
##################################################################################


REQUIREMENTS
=============
Any system with Java Runtime 1.4 or later installed.
Java can be found at https://www.java.com/


HOW TO RUN THE GAME
====================
Note: Ant is a very useful build tool which can be found at https://ant.apache.org/

Alternative 1: if you have Ant installed:
----------------------------------------
1) Unzip the file missilecommand.zip to any given directory.
2) In a console window, go to the base directory (where the file build.xml resides).
3) Type "ant run"
4) Ctrl-c to exit the game, or simply close the game window.

Alternative 2: if you do NOT have Ant installed:
------------------------------------------------
1) Unzip the file missilecommand.zip to any given directory.
2) In a console window, go to the /dist directory.
3) Type "java -classpath MissileCommand.jar MissileCommandApplication"
4) Ctrl-c to exit the game, or simply close the game window.


OBJECT OF THE GAME
===================
-Defend your cities from incoming missiles by shooting them.
-You target and shoot missiles with your mouse, and don't worry your defending explosion 
 will not harm your buildings.
-You can repair your buildings by clicking inside them, but note that it will cost points.


LIMITATIONS
============
-Simple graphics.
-Missile branching, space ships and levels are not implemented in this version.
-Start screen, end screen and high score is not implemented in this version.


Added non-required functionality
================================= 
Assumption: it is OK to add functionality to make the game a little more interesting.
I added the following: 

-Points, given for exploding incoming missiles, deducted for repairing buildings.
-Recharge for the defense system (thus making defender explosions smaller and limited over a specific period of time).
-The possibility to repair your buildings by clicking them (which costs you points).


DOCUMENTATION
==============
Documentation of the assignment can be found in the /docs directory.
The javadoc can be found under /docs/api
The source code can be found under /src
The generated jar-file can be found under /dist

Any questions or comments can be directed to the author.

Tobias Salem
E-mail: tobias.salem@bredband.net
Voice: +46 (0)8-752 05 18

