package gamePlay;
import java.util.ArrayList;

public class Player {
	ArrayList<Deck> cardsArray;						//variables declared
	private int roundsWon;	
	
	public Player() {								// constructor that creates a new deck
		cardsArray = new ArrayList<Deck>();
	}
	public ArrayList<Deck> getCardsArray() {		// returns the players cards
		return cardsArray;
	}
	public int getRoundsWon() {						//returns the number of rounds won
		return roundsWon;
	}	
	public void addRound() {						// increases the number of rounds won
		roundsWon++;
	}
	public void setCardsArray(ArrayList<Deck> cardsArray) {
		this.cardsArray = cardsArray;
	}

	public int getRoundsWons() {
		return roundsWon;
	}
}