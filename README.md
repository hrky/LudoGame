Ludo Game Project
=======
Course project in SENG271 (Software Model Engineering) from University of Victoria. The project is contributed by a team of four students. 

The Ludo board consists of a directed 40 field ring in form of a cross (See below)


![LudoBoard](https://raw.github.com/hrky/readme-image/master/images/ludoboard.png)

### Game Description
The rules are as follows:

1. There are four players: traditionally, red, blue, yellow and green. Every player has four pawns, which are not in the game initially (they are “at home”).
2.	Every 10th field serves as entry field for a player. Note that this imposes a cyclic order over the players. In addition, directly preceding each entry field is a junction to four consecutive goal fields of the same player.
3.	At every point in time, it is the turn of one of the players. Turns rotate according to the cyclic order of players.
4.	The player whose turn it is throws a six-sided die, and moves one of his pawns according to one of the following rules, if any is applicable. If no rule is applicable, no pawn is moved.

5. Entry: If the die shows a six and the player still has pawns at home, and the player’s entry field is not already occupied by a pawn of his own, he must put one pawn from his home to his entry field.  
Forward: If no entry move is possible, the player must select one of his pawns on the board and move it forward by the exact number of eyes on the die. In doing so he may not pass (i.e., overtake) or end on his own entry field (instead he must take the junction to his goal fields) and may not end on a field that is already occupied by a pawn of his own. Moreover, a forward move may not pass any pawn already on a goal field.  
If there is already a pawn (of another player) on the target field of a move, then this pawn is kicked and returns to the other player’s home.
6.	If the die roll was a six, the same player takes another turn; otherwise, the next player (in the given order) gets his turn.
The game ends when one of the players has occupied all his goal fields. This player has won the game.


### Objective
Create a simple board game simulation with Java/Swing. You will learn to apply some of the design patterns (Strategy, MVC, ...) and gain working knowledge of GUI programming with Java Foundation Classes/Swing.

### Task Description
Your overall goal is to design and implement a simulation for the LUDO board game in Java / Swing.

You are asked to implement the following requirements:

* The software should allow a human and computer players. Users can choose the “strategy” of the computer players (see above).  
* The Java UI should be graphical, based on Swing (Java GUI).

### Strategies
As with any game, an interesting question is to determine strategies for playing that are likely to win the game. Without going into formal game theory, one can easily identify several global strategies (global in the sense that they do not change during the game).

* Aggressive: Give preference to a move that kicks a pawn;
* Cautious: Give low priority to a move that kicks a pawn (so as not to anger the other player);
* Defensive: Give preference to a move to a target field where the pawn cannot be kicked;
* Move-first: Give preference to moving the foremost pawn; • Move-last: Give preference to moving the hindmost pawn.

More sophisticated strategies can be defined by taking the moves (or the strategies) of other players into account.


### Running the Project
Most group members used Netbeans 7.3 and JRE 1.7 to code this project, although the code should be IDE-independent. Please follow these instructions to get it to run:

1. Clone this Repo or [download it](https://github.com/bxio/SEng271/archive/master.zip) in some other fashion.
2. CD to the download location and run `ant jar` to compile the project. This requires the [Apache Ant](http://ant.apache.org/) utility
3. Double-click the SEng271.jar file under `./dist`

Or Open this folder directly in Netbeans

### Writeups
The Writeups for the milestones and the source file for the diagram is in the folders `Milestone 1 Deliverables` and `Milestone 2 Deliverambles`.

### Group members:
* Greg Richardson
* Bill Xiong
* Alastair Fehr
* Hiroki Yun

### Further details
Interested? Learn more about this project at GameDocumentation_MS2.pdf  
Note: This repository is detached from the original repository. The original version is available as "SEng271". 