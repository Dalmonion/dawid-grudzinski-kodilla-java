# Table of contents
* [General info](#general-info)
* [Game navigation](#game-navigation)
* [Game modes](#game-modes)
* [Game rules](#game-rules)
* [Technologies](#technologies)
* [Setup](#setup)

# General info
This is game called rock-paper-scissors with extension of 2 additional choose options. The game is meant for two players.

# Game navigation
To navigate through the game simply follow displayed options.
Most of the time it will be numeric choose between 1 and 5.
Additional there are keys responsible for starting new game ('n') or exiting current session ('x'), 
which can be confirmed with 'y' key or decline with 'n' key.

# Game modes
There are 3 options to choose:  
* human vs computer player
* computer player vs computer player
* human vs human

When picking option I or III, the game will ask for names in order to assign them for human players.


# Game rules
After start the game asks for choosing [game mode](#game-modes). By choosing specific option, the game can also ask for human 
player names. 

In next step you will ask for the number of winning rounds which will affect how long session will last. Winning rounds is the number which needs to be achieved by one player in order to win the game.

Round starts. When you choose to play with a computer you are first in round. Now you have to pick one of five available options: 1 for stone, 2 for paper, 3 for scissors, 4 for a lizard, 5 for Spock. 
Additional you can start a new game with 'n' key or end session with 'x' key. After your turn, a computer will choose its pick. It's worth to mention that 
when it comes to option 1 in game mode the computer have enhanced move which giving it 50% o winning chance, 25% of draw and only 25% of losing with you. That cheated move works only with option 1 game mode.
The round is won by the rules:
* stone destroy scissors
* scissors cuts paper
* paper covers the stone
* stone crushes lizard 
* lizard poison Spock
* Spock breaks scissors
* scissors hurt lizard
* lizard eats paper
* paper proves Spock's mistake
* Spock crushes stone

The game will display who picked specific option, who won the round and then adds 1 point to the winner winning round count. 
If both players picked up the same option game will display a draw notification and score will remain unchanged for all.
Here round ends and next one starting unless there was no draw and winning rounds value were set to 1 value.
The game will progress until one of players reach the point where winning rounds set in the beginning was equal to score of the players.
At the end the game will display winner with present score and 2 options to chose from- start a new game or exit the game.



# Technologies
Project is created with:
* IntelliJ IDEA version: 2020.2.3 x64
* JUnit Jupiter Engine version: 5.6.2
* JUnit Jupiter API version: 5.6.2
* Mockito JUnit Jupiter version: 3.3.3
* Gradle version: 6.7.1

# Setup
To run this game, simply run main method from RpsRunner class

