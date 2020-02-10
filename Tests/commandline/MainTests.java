package commandline;
//package commandline;
//import java.util.ArrayList;

import java.util.Scanner;

public class MainTests {
//
	public static void main(String[] args) {

		// test shuffle method by printing cards, shuffle and print again
//		for (int i = 0; i < 40; i++) {
//			System.out.println(play.deck[i].toString());
//		}
//		// shuffle
//		play.shuffle(play.deck);
//		// print again
//		for (int i = 0; i < 40; i++) {
//			System.out.println(play.deck[i].toString());
//		}

		// test deal by printing out player 1 cards
//		play.deal();
//		System.out.println(play.getPlayersArrayList().get(1).cardsArray.toString());
//		play.setTrump("Firepower");
//		System.out.println(play.getTrump());

		// test the add method in ComPile class
		// Changed arrays to String to make it easier
		// changed back after test
//		ArrayList <String> names = new ArrayList<String>();
//		names.add("Steph");
//		names.add("Robyn");
//		ComPile communal = new ComPile();
//		communal.addCards(names);
//		System.out.print(names +"\n");
//		// test remove method
//		// print out what the method returns
//		System.out.print(communal.removeCards());
//		// print out the empty comPile array
//		System.out.println(communal.comPile);

//		// test maxNum and maxNumPos
//		int [] numbers = new int[] {10, 4, 8, 24, 18};
//		//print the position of first instance of max number
//		System.out.println(play.maxNum(numbers));
//		System.out.println(play.maxNumPos(numbers));

		// test checkRound method
		// play.deal(); // deals and shuffles cards
		// print top cards for all players
//		System.out.println(play.getPlayersArrayList().get(0).getCardsArray().get(0).toString());
//		System.out.println(play.getPlayersArrayList().get(1).getCardsArray().get(0).toString());
//		System.out.println(play.getPlayersArrayList().get(2).getCardsArray().get(0).toString());
//		System.out.println(play.getPlayersArrayList().get(3).getCardsArray().get(0).toString());
//		// call checkRound method and print winning player position
//		System.out.println(play.checkRound());
//		play.nextRound();
//		System.out.println(play.getTrump());
//		System.out.println(play.getPlayersArrayList().get(0).getCardsArray().get(0).toString());
//		System.out.println(play.getPlayersArrayList().get(1).getCardsArray().get(0).toString());
//		System.out.println(play.getPlayersArrayList().get(2).getCardsArray().get(0).toString());
//		System.out.println(play.getPlayersArrayList().get(3).getCardsArray().get(0).toString());
//		// call checkRound method and print winning player position
//		System.out.println(play.checkRound());
		Controller controller = new Controller();
		controller.play();
//		for(int i =0; i<40; i++) {
//		System.out.println(controller.view.play.getDeck()[i].name);
//		}
//		System.out.println("");
//		controller.view.play.setDeck(controller.view.play.shuffle(controller.view.play.getDeck()));
//		
//		for(int i =0; i<40; i++) {
//			System.out.println(controller.view.play.getDeck()[i].name);
//			}
	}
}