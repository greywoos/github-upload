# Monopoly-Final-Project-Spring-2019

This implelentation of Monopoly was the final project for the Spring 2019 Object Oriented Programming I/in Java class at UAA

## Implementation Explanation

(This is a copy of "Game Implementation Explanation.txt")

TLS\
CSCE A222

Brief explaination on why I chose to implement the game in the way that I did:

  In order to put order in the GUI I decided to have one main JPanel that holds another JPanel (named "panel1") and a JLayeredPane (named "board").
  The main JPanel is then displayed on a JFrame. In the code for the GUI the gameboard is on the JLayeredPane. 
  That way when the gameboard is set on one layer the player pieces are able to still be seen and move around on another layer above it.
  The GUI also contains a two JTextAreas which store the game log and player information. 
  These JTextAreas, as well as another smaller JPanel which holds the game buttons, are all placed on panel1.
  
The log was made to print all actions and occurances (such as dice rolls, where the player landed on the board, who gives who money, etc.) that happen throughout the game. 
  The player information area was made in order to keep players updated on each of their locations and how much money they have. 
  The player information area also functions as a turn counter to show how many turns of the game have passed/are left and also as a holder of game information.
  
Every action in the game is implemented in the Display class. 
  The roll button is the main place where everything happens (setting and changing of current player, player movement, player image movement, square functions, etc.). 
  Nearly everything happens here because with the frequency that the roll button is clicked it provides an easy way to change and update variables. 
  Whether or not a property or house can be bought is determined by the change of the "currentsquare" variable in roll. 
  Due to the multiple checks that need to be passed in order for a property to be deemed buyable, the Buy Property button calls a "buy property" function when clicked. 
  Buy Property also uses a function because multiple types of squares can be bought (Property, Utility, Railroad). 
  The code for buying a house is hard coded into the house button because there is only one case where you can buy a house (on a Property Square). 
  The winner of the game is determined by a game winner function that is called when the "turns" variable in roll reaches 40 (meaning 10 turns have passed). 
  The function disables all buttons except for the exit button (which closes the program) to ensure that the game cannot be played any further.
