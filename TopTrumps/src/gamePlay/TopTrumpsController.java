package gamePlay;
import java.sql.SQLException;
import java.util.Scanner;

public class TopTrumpsController {	
	private TopTrumpsModel model;							// variables declared
	private TopTrumpsView view;	
	private int playerInput;
	private int noPlayers;
	private int category;

	public TopTrumpsController(TopTrumpsModel model) {		// constructor that takes a model object as parameter
		this.model = model;								
		this.view = new TopTrumpsView(model, this);
	}

	public void play() throws SQLException {				// method to play game			
		Scanner scanner = new Scanner(System.in);			// controller takes user input
		playerInput = 0;							
		view.choosePlayOrStats();							// then calls view to display options
		playerInput = scanner.nextInt();					// takes user input
		
		while (!(playerInput == 1) && !(playerInput == 2)) {	//while the user doesn't enter 1 or 2
			view.checkInput();									// method printing error message from view is displayed
			playerInput = scanner.nextInt();					// then allows user to input
			
		}
		if (playerInput == 1) {									//returns previous game statistics if 1 is chosen				
			view.returnStatistics();	
			play();												// then returns to start of play messages
		}
		if (playerInput == 2) {									//starts a new game if 2 is chosen
			noPlayers = 0;										// player numbers set to 0
			view.printSelectPlayerNo();
			noPlayers = scanner.nextInt();
			while ((noPlayers > 2) && (noPlayers > 5)) {
				view.checkNoPlayers();
				noPlayers = scanner.nextInt();
			}
			view.printGameStart();
			model.setNoOfPlayers(noPlayers);
			model.deal();
			while (!model.gameWon() && model.getPlayersArrayList().get(0).cardsArray.size() > 0) {
				view.printPlayerTopCard();
				view.printRound();
				if (model.getWinnerOfRound() == 0) {
					category = 0;
					view.printCategories();
					category = scanner.nextInt();
					while (!(category >= 1) && !(category <= 5)) {
						view.checkInput();
						category = scanner.nextInt();
					}
					model.setTrump(category);
				}

				else {
					model.aiPick(model.getPlayersArrayList().get(model.getWinnerOfRound()));
					view.printTrumps();
				}
				model.checkRound();
				if (model.getWinnerOfRound() == -1) {
					view.draw();
					model.setWinnerofRound(model.getPrevWinRound());
				} else if (model.getWinnerOfRound() == 0) {
					view.humanWin();
					model.setPrevWinRound(model.getWinnerOfRound());
				} else {
					view.AIWin();
					model.setPrevWinRound(model.getWinnerOfRound());
					
				}
			}
			view.gameWon();
			model.gameOver();
			play();
			scanner.close();
		}
	}
}

