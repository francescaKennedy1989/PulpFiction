

//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayGame {
// attributes
	private int noOfPlayers;
	private int noOfRounds = 1;
	private int noOfDraws = 0;
	private int prevWinRound = 0;
	private int winnerOfRound = 0;
	private int gameWinner;
	private int trump;
	private ArrayList<Player> playersArrayList = new ArrayList<Player>();// array of players
	private static Card[] deck = Card.shipArrayFill(); // deck of cards
	private ComPile comPile = new ComPile(); // create a class object this will hold communal pile
// array to hold categories
	ArrayList<Integer> statsArray = new ArrayList<Integer>();

// method that takes in the number of players
// and creates a player object for each one
	public void setPlayers(int noOfPlayers) {
		setNoOfPlayers(noOfPlayers); // set global var to local
		for (int i = noOfPlayers; i > 0; i--) { // loop the player
			Player p = new Player(); // create object
			playersArrayList.add(p); // add them to the plyersArrayList
		}
	}

// method to shuffle the deck of cards
// which takes in the current deck and returns it reordered
	public Card[] shuffle(Card[] array) {
		Random rgen = new Random(); // Random number generator
// loop the existing deck
		for (int i = 0; i < array.length; i++) {
// generate a random number from the length of the array
			int randomPosition = rgen.nextInt(array.length);
			Card temp = array[i]; // put the current pos i into a temp card
			array[i] = array[randomPosition]; // add a random card from the array to pos i
			array[randomPosition] = temp;// add temp value to the ranPos
// set deck to the new reordered array
			deck = array;
		}
		return deck;
	}

// method to deal the cards
// calls the shuffle method within it
	public void deal() {
// calls the shuffle method
		shuffle(deck);
// set int for card count
		int cardCount = 0;
// deal
// cards divided by noOfPlayers times
		for (int i = 0; i < (40 / getNoOfPlayers()); i++) {
// loop through the players in the ArrayList
// add a card to each persons cardArrayList
			for (int p = 0; p < getNoOfPlayers(); p++) {
				playersArrayList.get(p).cardsArray.add(deck[cardCount]);
				cardCount++; // count how many cards are dealt
			}
// if 3 players the cards don't divide equally
// player 1 needs to get an extra card
			if (cardCount == 39) { // if only 39 cards have been dealt
				playersArrayList.get(0).cardsArray.add(deck[39]); // give the 40th card to player 1
				cardCount++;
			}
		}
	}

// method that returns the number of times a number appears in an array
	public int countValue(int[] values, int num) {
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == num) {
				count++;
			}
		}
		return count;
	}

// method that returns the position of the largest
// value in an int Array
	public int maxNumPos(int[] array) {
		int largest = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[largest])
				largest = i;
		}
		return largest; // position of the first largest found
	}

// method that returns the highest
// value in an array of ints
	public int maxNum(int[] array) {
		int largest = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[largest])
				largest = i;
		}
		return array[largest]; // highest value
	}

// method to add all the players top card to an ArrayList
// and then delete them from the players array
// calls the shuffle method and then
// returns the new array of cards
	public ArrayList<Card> cardsWon() {
// for testing System.out.println("calling cardsWon");
		ArrayList<Card> cardsPlayed = new ArrayList<Card>();
		for (int i = 0; i < noOfPlayers; i++) {
// if the player has no cards skip them
			if (playersArrayList.get(i).cardsArray.size() == 0)
				continue;
// else add there card to the cardsPlayed arrayList
			cardsPlayed.add(playersArrayList.get(i).cardsArray.get(0));
// then remove it from there hand
			playersArrayList.get(i).cardsArray.remove(0);
// call shuffle methd to reorder the cards
		}
		return cardsPlayed;
	}

// method that when called removes all players top card
// and adds them to the communal pile
	public void isDraw() {
// for testing System.out.println("calling isDraw");
		System.out.println("The round was a draw.");
// call the cardsWon method adding them to the comPile
		comPile.addCards(cardsWon()); // removes players cards and returns them
		noOfDraws++;
// nextRound();
	}

// method to check and return who has won the round
// calls on various other methods to achieve this
	public int checkRound() {
// for testing System.out.println("calling checkRound");
		int[] trumpsArray = new int[noOfPlayers];
// loop the number of players
		for (int i = 0; i < getNoOfPlayers(); i++) {
// skip player if they have no cards left
			if (playersArrayList.get(i).cardsArray.size() == 0)
				continue;
// add the players trump value to trumpsArray
// by calling the getters on the card class
			if (getTrump() == 0) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getSize();
			} else if (getTrump() == 1) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getSpeed();
			} else if (getTrump() == 2) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getRange();
			} else if (getTrump() == 3) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getFirepower();
			} else if (getTrump() == 4) {
				trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getCargo();
			}
		}
// find the first highest number position in the array
// by calling the maxNumPos method
		int highPos = maxNumPos(trumpsArray);
// find the highest value in the array
// by calling the maxNum method
		int highVal = maxNum(trumpsArray);
// call the countValue method to see if it's a draw
		if (countValue(trumpsArray, highVal) >= 2) {
// if it is set winner to position -1
			winnerOfRound = -1;
// and call the isDraw method
			isDraw();
		} else {
// add all cards in the round to the winners pile
// by calling the cardsWon method
			playersArrayList.get(highPos).cardsArray.addAll(cardsWon());
// give winner any cards in the comPile by calling the removeCards method
			playersArrayList.get(highPos).cardsArray.addAll(comPile.removeCards());
			winnerOfRound = highPos; // update the winnerOfRound var
			playersArrayList.get(winnerOfRound).addRound();// call the addRound method to increments the players
// winRound attribute
		}
		return winnerOfRound;
	}

// method for an ai player to select their highest value
// category and set it as trump via calling setTrump method
	public void aiPick(Player p) {
// for testing System.out.println("calling aiPick");
// add all their caregories values to an array
		int[] topCat = new int[] { p.cardsArray.get(0).getSize(), p.cardsArray.get(0).getSpeed(),
				p.cardsArray.get(0).getRange(), p.cardsArray.get(0).getFirepower(), p.cardsArray.get(0).getCargo(), };
// set trump to the highest value position in the array
		if (maxNumPos(topCat) == 0) {
			setTrump(0);
		} else if (maxNumPos(topCat) == 1) {
			setTrump(1);
		} else if (maxNumPos(topCat) == 2) {
			setTrump(2);
		} else if (maxNumPos(topCat) == 3) {
			setTrump(3);
		} else if (maxNumPos(topCat) == 4) {
			setTrump(4);
		}
	}

// method to check if any players have won the game
	public boolean gameWon() {
// for testing System.out.println("calling gameWon");
// set win to false initially
		boolean win = false;
// loop all players
		for (int i = 0; i < getNoOfPlayers(); i++) {
// if any player has all 40 cards
			if (playersArrayList.get(i).cardsArray.size() == 40) {
// they have won the game
				setGameWinner(i + 1);
				win = true;
				break;
			}
		}
		return win;
	}

// gameOver method which will send all the stats to the database
	public void gameOver() {
// for testing System.out.println("calling gameOver");
//ArrayList <Integer> statsArray = new ArrayList<Integer>();

		int humanwins = playersArrayList.get(0).getRoundsWons();
		int computerwins = 0;
		for (int i = 1; i < noOfPlayers; i++) {
			computerwins = computerwins + playersArrayList.get(i).getRoundsWons();
		}

		if (gameWon() == true) {

//			try {
//				DatabaseConnection DC = new DatabaseConnection();
//				DC.sendStatisticsDB(gameWinner, humanwins, computerwins, noOfDraws, noOfRounds - 1);
//			} catch (SQLException e) {
//// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			/*
			 * statsArray.add(noOfDraws); statsArray.add(gameWinner);
			 * statsArray.add(noOfRounds - 1); for (int i = 0; i < noOfPlayers; i++) {
			 * statsArray.add(playersArrayList.get(i).getRoundsWons());
			 */
		}
	}
//return statsArray;

// getters and setters for attributes
	public ArrayList<Player> getPlayersArrayList() {
		return playersArrayList;
	}

	public void setPlayersArrayList(ArrayList<Player> playersArrayList) {
		this.playersArrayList = playersArrayList;
	}

	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	public int getNoOfRounds() {
		return noOfRounds;
	}

	public void addNoOfRounds() {
		noOfRounds++;
	}

	public int getNoOfDraws() {
		return noOfDraws;
	}

	public void setNoOfDraws(int noOfDraws) {
		this.noOfDraws = noOfDraws;
	}

	public int getWinnerOfRound() {
		return winnerOfRound;
	}

	public void setWinnerofRound(int winnerOfRound) {
		this.winnerOfRound = winnerOfRound;
	}

	public int getGameWinner() {
		return gameWinner;
	}

	public void setGameWinner(int gameWinner) {
		this.gameWinner = gameWinner;
	}

	public int getTrump() {
		return trump;
	}

	public void setTrump(int trump) {
		this.trump = trump;
	}

	public int getPrevWinRound() {
		return prevWinRound;
	}

	public void setPrevWinRound(int prevWinRound) {
		this.prevWinRound = prevWinRound;
	}

	public int getComPile() {
		return comPile.getComPileSize();
	}

	public static Card[] getDeck() {
		return deck;
	}

	public static void setDeck(Card[] deck) {
		PlayGame.deck = deck;
	}
}
