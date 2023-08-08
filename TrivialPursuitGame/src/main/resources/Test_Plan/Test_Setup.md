
<h1> Test_Setup </h1>
<h2>Goal(s):</h2>  Test the setup requirements for the game

<!-- Pick one either Functionality or the Requirements whichever better describes the  test  --!> 

h2>Requirement(s):</h2> 

*   1.1. The game shall support up to 4 players and a minimum of 2.
*   1.3. The game shall display the player names for all active players
*   1.4. The game shall require 4 categories to be selected before the play starts
*   1.4.1.Each category shall be given its own color corresponding to a color on the gameboard.
*   1.5 The game shall display the assigned category name on every colored space.

================================================================================

<h3>1. Procedure: </h3> Test starting with < 2 players 

<h3>Location:</h3> Trivial Compute Start Page

<h4>  Instruction(s) </h4>


1.  Run the application
2.  Add players into game

<h4>  Verification(s) </h4>

1.  the start game button should stay grayed out until there are at least two players


================================================================================

<h3>2. Procedure: </h3> Test starting with > 4 players

<h3>Location:</h3> Trivial Compute Start Page

<h4>  Instruction(s) </h4>


1.  Run the application
2.  Add 4 players
3.  try adding a 5th player
  
<h4>  Verification(s) </h4>

1.  The game should pop an error letting you know that you can't have more than 4 players

================================================================================

<h3>3. Procedure: </h3> Test displaying player names

<h3>Location:</h3> Game board

<h4>  Instruction(s) </h4>

1.  Run the application
2.  Add 2 - 4 players into game
3.  Start the game 

<h4>  Verification(s) </h4>

1.  the game board should display player names above their respective scores

================================================================================

<h3>4. Procedure: </h3> Setup categories

<h3>Location:</h3> Trivial Compute start page

<h4>  Instruction(s) </h4>

1.  select categories for each of the colors
2.  add 2 - 4 players
3.  start game

<h4>  Verification(s) </h4>

1.  game should not start until all colors have a category

================================================================================

<h3>5. Procedure: </h3> Display categories 

<h3>Location:</h3> Trivial Compute gameboard

<h4>  Instruction(s) </h4>


1.  select categories for each of the colors
2.  add 2 - 4 players
3.  start game
4.  the gameboard should display the selected categories for the specified colors

<h4>  Verification(s) </h4>

1.  the gameboard should display the selected categories for the specified colors

================================================================================