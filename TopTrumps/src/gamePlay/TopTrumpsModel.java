package gamePlay;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import Database.DatabaseStats;

public class TopTrumpsModel {
    private int noOfPlayers; 															// create attributes
    private int noOfRounds = 1;
    private int noOfDraws = 0;
    private int prevWinRound = 0;
    private int winnerOfRound = 0;
    private int gameWinner;
    private int trump;
    private int highPos;
    private ArrayList < Player > playersArrayList = new ArrayList < Player > (); 		// array of players
    private static Deck[] deck = Deck.shipArrayFill(); 									// deck of cards
    private ComPile comPile = new ComPile(); 											// create a class object this will hold communal pile

    
    public TopTrumpsModel() {													// Constructor that passes in the number of players												
        for (int i = noOfPlayers; i > 0; i--) {									// set global variable to local				
            Player p = new Player();											// this.noOfPlayers = noOfPlayers; create the same amount of Player objects as noOfPlayer
            playersArrayList.add(p);											// add them to the palyersArrayList
        }
    }
    
    public Deck[] shuffle(Deck[] array) {										// shuffle method that takes in current deck and returns it reordered
        Random rgen = new Random();											    // Random number generator
        for (int i = 0; i < array.length; i++) {								// loop the existing deck
            int randomPosition = rgen.nextInt(array.length);			  	    // generate a random number from the length of the array
            Deck temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
            deck = array;														// set deck to the new reordered array
        }
        return deck;
    }

    public void deal() {														 // method to deal the cards // calls the shuffle method within it
        shuffle(deck);															 // calls the shuffle method within it
        int cardCount = 0;					        							 // set int for card count
        for (int i = 0; i < (40 / getNoOfPlayers()); i++) {						 // cards divided by noOfPlayers times
 
            for (int p = 0; p < getNoOfPlayers(); p++) {                         // loop through the players in the ArrayList
                playersArrayList.get(p).cardsArray.add(deck[cardCount]); 		 // add a card to each persons cardArrayList
                cardCount++; 													 // count how many cards are dealt
            }
            																	 // if 3 players the cards don't divide equally
          
            if (cardCount == 39) { // if only 39 cards have been dealt			 // player 1 needs to get an extra card
                playersArrayList.get(0).cardsArray.add(deck[39]); 				 // give the 40th card to player 1
                cardCount++;
            }
        }
    }
    public int countValue(int[] values, int num) {								// method that returns the number of times a number appears in an array
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == num) {
                count++;
            }
        }
        return count;
    }
    
    public int maxNumPos(int[] array) {											 // method that returns the position of the largest
        int largest = 0;														 // value in an int Array
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[largest])
                largest = i;
        }
        return largest; 														// position of the first largest found
    }

    public int maxNum(int[] array) {										    // method that returns the highest
        int largest = 0;														// value in an array of ints
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[largest])
                largest = i;
        }
        return array[largest]; 													// highest value
    }
														
    public void cardsRound() {													//method to add cards to the winners pile
        playersArrayList.get(highPos).cardsArray.addAll(cardsWon());			//from other players and the comPile
        playersArrayList.get(highPos).cardsArray.addAll(comPile.removeCards());	// give winner any cards in the comPile by calling the removeCards method
        winnerOfRound = highPos; 												// update the winnerOfRound var
        playersArrayList.get(winnerOfRound).addRound(); 						// call the addRound method to increments the players winRound attribute
    }
 																					
    public ArrayList < Deck > cardsWon() {										// method to add all players' top card to an ArrayList & delete them from players array					
        ArrayList < Deck > cardsPlayed = new ArrayList < Deck > ();				// calls the shuffle method and then
        for (int i = 0; i < noOfPlayers; i++) {									// returns the new array of cards
            if (playersArrayList.get(i).cardsArray.size() == 0)					 // if the player has no cards skip them
                continue;
            cardsPlayed.add(playersArrayList.get(i).cardsArray.get(0));			 // else add there card to the cardsPlayed arrayList
            playersArrayList.get(i).cardsArray.remove(0);						 // then remove it from there hand
        }
        return cardsPlayed;														 // call shuffle method to reorder the cards
    }

    public void isDraw() {																   // method to remove all players top cards and adds them to communal pile
        comPile.addCards(cardsWon());													    // removes players cards and returns them
        noOfDraws++;																		// call the cardsWon method adding them to the comPile
    }

    public int checkRound() {																// method to check and return who has won the round through various methods
             int[] trumpsArray = new int[noOfPlayers];									    // for testing System.out.println("calling checkRound");
             for (int i = 0; i < getNoOfPlayers(); i++) {									// loop the number of players
                      if (playersArrayList.get(i).cardsArray.size() == 0)				   	// skip player if they have no cards left
                continue;
       
            if (getTrump() == 0) {															 // add the players trump value to trumpsArray
                trumpsArray[i] = playersArrayList.get(i).cardsArray.get(0).getSize();		 // by calling the getters on the card class
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
        
        int highPos = maxNumPos(trumpsArray);										// find the first highest number position in the array by calling the maxNumPos method
        int highVal = maxNum(trumpsArray);											// find the highest value in the array by calling the maxNum method
        if (countValue(trumpsArray, highVal) >= 2) {								// call the countValue method to see if it's a draw
            winnerOfRound = -1;										   				// if it is set winner to position -1
            isDraw();																// and call the isDraw method
        } else {																	// add all cards in the round to the winners pile
            playersArrayList.get(highPos).cardsArray.addAll(cardsWon());			// by calling the cardsWon method
            playersArrayList.get(highPos).cardsArray.addAll(comPile.removeCards()); // give winner any cards in the comPile by calling the removeCards method
            winnerOfRound = highPos; 												// update the winnerOfRound var
            playersArrayList.get(winnerOfRound).addRound(); 						// call the addRound method to increments the players
        }
        noOfRounds++;													
        return winnerOfRound;														// winRound attribute
    }

    public void aiPick(Player p) {													// method for an ai player to choose highest category and set as trump via setTrump method	
        int[] topCat = new int[] {													// add all their categories values to an array
            p.cardsArray.get(0).getSize(), 
            p.cardsArray.get(0).getSpeed(),
            p.cardsArray.get(0).getRange(), 
            p.cardsArray.get(0).getFirepower(),
            p.cardsArray.get(0).getCargo(),
        };
        if (maxNumPos(topCat) == 0) {								 				// set trump to the highest value position in the array
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

    public boolean gameWon() {												// method to see if any players have won the game
      																		
        boolean win = false;												// set win to false initially
        for (int i = 0; i < getNoOfPlayers(); i++) {						// loop all players
            if (playersArrayList.get(i).cardsArray.size() == 40) {			// if any player has all 40 cards
                setGameWinner(i + 1);										// they have won the game
                win = true;
                break;
            }
        }
        return win;
    }

    public void gameOver() throws SQLException {
        int humanwins = playersArrayList.get(0).getRoundsWons();
        int computerwins = 0;
        for (int i = 1; i < noOfPlayers; i++) {
            computerwins = computerwins + playersArrayList.get(i).getRoundsWons();
        }
        if (gameWon() == true) {
        	DatabaseStats.sendStatisticsDB(gameWinner, humanwins, computerwins, noOfDraws, noOfRounds - 1);
        }
    }

    public ArrayList < Player > getPlayersArrayList() {									// getters and setters for attributes
        return playersArrayList;
    }

    public void setPlayersArrayList(ArrayList < Player > playersArrayList) {
        this.playersArrayList = playersArrayList;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
        for (int i = noOfPlayers; i > 0; i--) {
            Player p = new Player();
            playersArrayList.add(p);												// add them to the plyersArrayList
        }
    }

    public int getNoOfRounds() {													// getters and setters for attributes
        return noOfRounds;
    }

    public void addNoOfRounds() {
        noOfRounds++;
    }

    public void setNoOfRounds(int noOfRounds) {
        this.noOfRounds = noOfRounds;
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

    public static Deck[] getDeck() {
        return deck;
    }

    public static void setDeck(Deck[] deck) {
        TopTrumpsModel.deck = deck;
    }

    public String showCard(int player) {
        return playersArrayList.get(player).cardsArray.get(0).toString();
    }

}