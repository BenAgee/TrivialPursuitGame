<h1> Test_Gameplay </h1>
<h2>Goal(s):</h2> Test the gameplay requirements for the game

<h2>Requirement(s):

*   2.1. The game shall highlight the current active player.
*   2.2. The game shall randomly select a player to go first.
*   2.3. The active player shall be able to roll a digital dice to determine how many spaces they will
*   travel.
*   2.3.1.The active player shall be allowed to pick a direction of travel.
*   2.3.2.The active player should be moved the rolled number of spaces in the direction specified.
*   2.4. The game shall display a question from the category corresponding to the color of the user’s
*   current space.
*   2.4.1. If the player lands on a roll again space, they get to roll the dice and move again.
*   2.4.2.If the player lands on the center white trivial pursuit space, they will be allowed to pick the
*   category they wish to answer.
*   2.4.3.The Correct answer must be hidden from the player.
*   2.4.4.The correct answer will be displayed by the user when the user interacts with an on-screen
*   button.
*   2.5. The game shall accept user input on wither the question was answered correctly or not.
*   2.5.1.If the question is answered correctly
*   2.5.1.1. The Current player gets to roll the dice again and continue play.
*   2.5.1.2. If the space was an HQ space the player will be awarded a category piece for the
*   corresponding color
*   2.5.2.If the question is answered incorrectly
*   2.5.2.1. The game moves on to the next player.

================================================================================

<h3>1. Procedure: </h3> Test Current player

<h3>Location:</h3> Game board

<h4>  Instruction(s) </h4>

1.  Run the application
2.  Add 2 - 4 players into game
3.  Start the game

<h4>  Verification(s) </h4>

1.  the current player will be listed at the top of the board as well as highlighted in game
  
================================================================================

<h3>2. Procedure: </h3> Test Random starting player

<h3>Location:</h3> Game board

<h4>  Instruction(s) </h4>

1.  Run the application
2.  Add 2 - 4 players into game
3.  Start the game
4.  note starting player
5.  restart the game and repeat steps 1-4

<h4>  Verification(s) </h4>

1.  the starting player should be different every time.
 
================================================================================

<h3>3. Procedure: </h3> Test Dice rolling and movement

<h3>Location:</h3> Game board

<h4>  Instruction(s) </h4>

1.  Run the application
2.  Add 2 - 4 players into game
3.  Start the game
4.  click the roll dice button 
5.  click one of the active movement buttons

<h4>  Verification(s) </h4>


1.  The roll dice button should be active when the gameboard comes up
2.  Clicking the roll dice button should generate a random number between 1 and 6
3.  After the dice is rolled the movement buttons should become activated 
4.  only the valid movements should be active
5.  upon clicking a movement button the active player should move the number of places rolled and in the direction chosen 

 
================================================================================

<h3>3. Procedure: </h3> Test game sequence 

<h3>Location:</h3> Game board

<h4>  Instruction(s) </h4>

1.  Complete the instructions for procedure 3
2.  if the player landed on colored space the questions dialog should launch
3.  hit the display answer option 
4.  then click either the Correct or incorrect button
5.  if the player landed on a roll again space the roll button should reactivate

<h4>  Verification(s) </h4>

1.  landing on a colored space should launch a colored dialog
2.  the question should be related to the catagory of the space
2.  the display answer button should show the answer and activate the correct and incorrect buttons 
3.  answering correctly should let the play continue with the current active player
4.  answering incorrectly should move play to the next player
5.  if the question space was an HQ space and answers correctly they should be awarded the matching color in their score board 
 
================================================================================
<h4>Notes: </h4> an area for the tester to leave remarks