//import java.sql.SQLException;
import java.util.Scanner;

// controller class that calls methods from both the model and the view
public class Controller {
// attributes
	View view = new View();
// attributes for user input
	private Scanner humanSelect = new Scanner(System.in); // Create a Scanner object
	private Scanner humanSelect2 = new Scanner(System.in); // Create a Scanner object
	private Scanner humanSelect3 = new Scanner(System.in); // Create a Scanner object
	private int h;
	private int h2;
	private int h3;

	public void play() { // play method that calls everything

		do {
			view.printGameStats(); // 1 or 2 to see stats or play game
			h = humanSelect.nextInt(); // h = the user input
		} while ((h != 1 && h != 2)); // keep looping until the correct number is entered

		if (h == 1) { // if 1 call statistics ****** UPDATE WITH DATABASE STUFF
			System.out.println("Called statistics\n"); // Just for testing to be removed
//			try {
//				view.returnStatistics();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			this.play();
		} else if (h == 2) { // if 2 play game
			do {
				view.printSelectPlayerNo(); // ask how many players
				h2 = humanSelect2.nextInt(); // h2 = user input
			} while (!(h2 > 1 && h2 < 6)); // keep asking until the correct no is entered

			view.play.setPlayers(h2); // set the number of players
		}
		view.play.deal(); // deal the cards
		view.printGameStart(); // print Game started
		do { // keep doing this until the game has been won
			view.printRound(); // prints round number and players cards have been drawn
// if the human player has cards left prints out the one at position 0
			if (view.play.getPlayersArrayList().get(0).getCardsArray().isEmpty() == false) {
				view.printPlayerTopCard();
			}
			if (view.play.getWinnerOfRound() == -1) {// if it was a draw
				view.play.setWinnerofRound(view.play.getPrevWinRound()); // set winner of round to previous winner
			}
			if (view.play.getWinnerOfRound() == 0) {// if human player has won
				do {
					view.printCategories();// print categories to choose
					h3 = humanSelect3.nextInt();// h3 = user input
				} while (!(h3 > 0 && h3 < 6)); // keep asking while until the correct number is entered
				if (h3 < 1 || h3 > 5) {
					view.printCategories();// ******* TRY CATCH NEEDED
				} else if (h3 == 1) { // if 1
					view.play.setTrump(0); // set to size
				} else if (h3 == 2) { // if 2
					view.play.setTrump(1); // set to speed
				} else if (h3 == 3) { // if 3
					view.play.setTrump(2);// set to range
				} else if (h3 == 4) {// if 4
					view.play.setTrump(3);// set to firepower
				} else if (h3 == 5) {// if 5
					view.play.setTrump(4);// set to cargo
				}
			} else { // if not draw or human win pick ai card
				view.play.aiPick(view.play.getPlayersArrayList().get(view.play.getWinnerOfRound()));
				view.printTrumps();
			}
			view.play.checkRound();// check to see who has won
			view.printWinnerOfRound();
			if (view.play.getWinnerOfRound() >= 0) { // if the last round was not a draw
				view.play.setPrevWinRound(view.play.getWinnerOfRound());// set knew previous winner to winner
			}
			view.play.addNoOfRounds();
		} while (view.play.gameWon() == false); // end of do loop and game
		view.printGameOver();// print game over, winner etc
		view.play.gameOver(); // send stats to database ******* NEEDS UPDATED TO DO THIS!!*****

// humanSelect.close();// close all the scanners  THESE THREW AN ERROR SO COMMENTED OUT
// humanSelect2.close();
// humanSelect3.close();
	}
}
