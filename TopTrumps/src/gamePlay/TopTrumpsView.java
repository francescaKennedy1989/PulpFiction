package gamePlay;
import java.sql.SQLException;
import Database.DatabaseConnection;

public class TopTrumpsView {
    private TopTrumpsModel model; 													//instantiation of model and controller
    private TopTrumpsController controller;

    public TopTrumpsView(TopTrumpsModel model, TopTrumpsController controller) { 	// view constructor that takes model and controller as parameters
        this.model = model;
        this.controller = controller;
    }

    public void choosePlayOrStats() { 												// method to print initial options to screen
        System.out.println("Do you want to see past results or play game?\n" +
            "\t 1:Print same statistics \n\t 2:Play game \nEnter the " +
            "number for your selection: ");
    }
    
    public void checkInput() {														 // method that prints error message if invalid input entered				
        System.out.println("\t\t**INVALID INPUT**.  " +
            "\nPlease enter a valid number after the colon. Choose 1 to view "
            + "statistics or 2 to play a game:");
    }
    public void checkNoPlayers() {														 // method that prints error message if invalid input entered				
        System.out.println("\t\t**INVALID INPUT**.  " +
            "\nPlease enter a valid number after the colon. Choose between 2"
            + "and 5 players:");
    }
    
    public void printSelectPlayerNo() {												 // Asks how many AI players to play against
        System.out.println("Please select the number of players between 2 and 5");
    }

    public void printGameStart() {													// prints Game start message
        System.out.println("Game Start");
    }

    public void printRound() { 														// prints round no and players have drawn their cards
        System.out.println("Round " + model.getNoOfRounds());
        System.out.println("Round: " + model.getNoOfRounds() + " Players have drawn their cards.");
    }

    public void printPlayerTopCard() { 												// prints humans top card
        System.out.println("\nPlayer 1 : " + "You drew" + model.getPlayersArrayList().get(0).cardsArray.get(0).toString() + "\nYou have " +
            model.getPlayersArrayList().get(0).cardsArray.size() + " cards left in your deck.");
    }

    public void printCategories() {													 // prints categories to select
        System.out.println("\nPlease select a category:\n1. Size\n2. Speed\n3. Range\n4. Firepower\n5. Cargo");
    }
    public void printWinnerOfRound() { 												// prints the winner of the round
        if (!(model.getWinnerOfRound() == -1)) {
            System.out.println("\nPlayer " + (model.getWinnerOfRound() + 1) + " has won the round with the following card:\n" +
                model.getPlayersArrayList().get(model.getWinnerOfRound()).getCardsArray().get(0).toString() + "\n");
        }
    }

    public void printTrumps() { 													// prints what category is trumps
        System.out.println("Trumps is :" + (model.getTrump() + 1));
    }

    public void printGameOver() { 													// prints game over message, winner and stats
        System.out.println("Game over! Player " + model.getGameWinner() +
        " has won the game.\nNo of draws : " + model.getNoOfDraws());
        for (int i = 0; i < model.getNoOfPlayers(); i++) {
            System.out.println("Player " + (i + 1) + " has won " 
            + model.getPlayersArrayList().get(i).getRoundsWon() + " rounds.");
        }
    }

    public void draw() {															//method to print size of comPile needed
        System.out.println("This round was a draw! \nThere are " +
            model.getComPile() + " cards in the common pile.");
        																			
    }

    public void humanWin() {														//method to print if human player has won
        System.out.println("You won this round!\n" +								// and which card they won with
            "The winning card was:\n" + model.showCard(0));							
    }
    public void AIWin() {															// method to print if AI player has won
        System.out.println("Player " + (model.getWinnerOfRound() + 1) +				// and which card they won with
            " has won this round!\n The winning card was:\n" +
        	  model.showCard(model.getWinnerOfRound()) +
            "\n The winning category was: ");
        if (model.getTrump() == 0) {
            System.out.print("Size");
        } else if (model.getTrump() == 1) {
            System.out.println("Speed");
        } else if (model.getTrump() == 2) {
            System.out.println("Range");
        } else if (model.getTrump() == 3) {
            System.out.println("Firepower");
        } else if (model.getTrump() == 4) {
            System.out.println("Cargo");
        }
    }

    public void gameWon() {																	//method to print that the game has been won and who by
        model.gameWon();
        System.out.println("\t\tGame Over!\n" + "The winner of this game is " 
        + model.getGameWinner() + "!!");
    }

    public void returnStatistics() throws SQLException {									// method that prints statistics of previous game
        DatabaseConnection DC = new DatabaseConnection();										
        System.out.println("\tNumber of Games: " + DC.getGamesPlayed() +					// prints number of games played
        				"\n\tNumber of Human Wins: " + DC.getHumanWins() 						
        				+ "\n\tNumber of AI Wins: " +  DC.getComputerWins() 
        				+ "\n\tAverage Number of Draws: " + DC.getAvgDraws() 
        				+ "\n\tLongest Game: " + DC.getLongestGame() + "\n\n");
        
    }


}