# Dots and Boxes
[![Build Status](https://travis-ci.com/Ivan-Zennaro/dots_and_boxes.svg?branch=main)](https://travis-ci.com/Ivan-Zennaro/dots_and_boxes)

Dots and Boxes is a pencil-and-paper game for two players. It was first published in the 19th century by French mathematician Édouard Lucas, who called it la pipopipette. It has gone by many other names, including the game of dots, dot to dot grid, boxes, and pigs in a pen.

## Rules

  1. Choose the players' identity and color, and the grid size;
  2. At each Player's turn, a line from the game grid must be selected;
  3. The player who completes a box gets a point and has an extra turn;
  4. Two players compete to complete most boxes in the game grid\sand to get more points!
  5. You can also choose to host or join a server-based game on the main menu.
                        
  ### Enjoy the game!

## Cli version
You can insert which game mode you want to start from the main menu.

The following is the representation of the board in the Command Line version.

To insert a line there are 3 parameters [*x*, *y*, *side*]:
- *x*,*y* are the coordinates of one of the two adjacent boxes composed by that line
- *side* = [U, D, L, R] is the position of the line with respect to that box: Up, Down, Left, Right.

##Note: 
This is what most resembles the alpha version of the game for which we planned a pvp game via cli.
In order to play this game, either go to the unify-main branch and launch via local computer terminal the file out/artifacts/dots_and_boxes_jar/dots-and-boxes.jar
As( once you open the folder in the local terminal of the computer via a right click command on the file in the Project side-window)

```$java dots_and_boxes.jar cli```

!!Please, notice that the colors of the command lines have been optimized via the IntelliJ, and via a Windows terminal 
they do not work properly. Try an unix-supporting terminal. Unfortunately, this OS mismatch has been revealed too late in the development to be dealt with, if not even on the day of the deadline.
!! So to speak, this way to start the code has not been discussed enough and is a little despised because of this misbehaviour.
On Linux or Mac it should work fine and is an ok way to play the game.

Then instructions are prompted. A few references to add:
- input 1 returns what was the alpha version's behaviour. The underlying code has since been refactored and upgraded;
- input 3 retuns what could be seen as a strong proof of the fact that the code works in its most part: a demonstration CPUvsCPU is shown.
  Game rules are applied, cli is shown working, game starts and ends in one run, no input to insert (cli version might have a long time to insert the input, this is an automatized game);
- input 2 is the game versus the computer, which might run faster since it requires half of the input, and it also tests what is tested on the mode 3;
- in order to run both input 4(hosting server) and input 5(running client) you need to run the program on two different terminals.
 In one terminal you will have to choose input 4, so it sets up the server and waits for a client to connect. In the other terminal you will have to select the client and you must insert the local address 127.0.0.1 . 
  A server only accepts one client and the server-client game is exclusively pvp, no options could be choosen to change that or to modify the board.
  Extra features, like mixed-interface server-client game or a via-LAN server-client game will maybe be shown at the presentation if there will be time
  (for your information, these have been tested and work, we will be able to reproduce them for you if you are interested to see)
  !!Please, insert your input only as your turn has begun, else the input will be "buffered", and sent as a game input as soon as your turn starts: this might be a rule to follow even for the other modes, as keyboard input is open at all times but the game input is open only when the game prompts a new move.
  Also, the two players must change their turn on the keyboard as a local game is played: to do that follow the turnation given by the command line output at the start of each turn.
  
The second way to call the game is to go to the main branch and simply run the main method of the MenuCli class. What is said above, of course, still applies.

![Command Line Game Screenshot](images/dots-and-boxes-Cli-screenshot.PNG)

Remember, colors are properly displayed in a terminal that supports **ANSI escape codes**. 

Generally it works well in Unix shell prompts.

## Gui version
From this menu you can select:
1. Player-1 name
2. Player-2 name if human, or the difficulty of the computer player
3. Player colors
4. Board dimensions
5. Local: to play the game in your machine
6. Host:  to start hosting a 3x3 PvP game and wait for another player in LAN to join the game
7. Join:  to join a hosted PvP game by another user present in LAN, specifying his internal IP address.
8. "Demo" button to view a game demonstration

Then press "Start Game!". 



![Graphical User Interface Menu Screenshot](images/dots-and-boxes-GUI-Menu.PNG)


Once a game has started this window will pop up. 
Click on a line to play your turn. 
Check if it is your turn on the label under the grid.

![Graphical User Interface Board Screenshot](images/dots-and-boxes-GUI-Board.gif)

## Notes:
The gui is relatively well tested and more intuitive. A quick test could be done by the demo button. The rules button prompts the rules window.
Option errors guide the user to a correct input on the main menu. As for the CLI version, the server-client game must be prompted in two different windows.
i.e. open two windows, select option host in one and option client in the other
( insert local addres 127.0.0.1 if the two windows are open in the same desktop, on the same local machine, thought virtual machine-real machine LAN game could be done via a different address)
Run the game as regular turnation is shown on the window. Misuse of the server-client mode as been tested relatively a lot and so the unusualities of this game mode are dealt via a complicate and case-to-case exception handling.
For example the player who does not have te turn cannot insert a move. Closures of server and client are dealt with.
Options choosen from the main menu are however set by default. As we said before

In order to play this game, either go to the ```unify-main``` branch and launch via local computer terminal the file dots-and-boxes.jar
As

```$java dots_and_boxes.jar gui```

or else go to the ```main``` branch and run the main method at the last line of MenuGui.
However separate jar files could be produced in the main class as soon as one main is selected. This however has not been
done in the final version but has been tried successfully and can be reproduced during the presentation.


### Developers

- [Luca Crozzoli](https://github.com/Luca-Crozzoli)
- [Ivan Zennaro](https://github.com/Ivan-Zennaro)
- [Nicola Domenis](http://github.com/nicdom23)
- [Stefano Pasquini](https://github.com/JawaCoder)
- [Dario Crosera](https://github.com/drocro)
