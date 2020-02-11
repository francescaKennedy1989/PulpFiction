package gamePlay;
import java.util.ArrayList;

public class ComPile {
	ArrayList <Deck> comPile = new ArrayList<Deck>();							// create arrayList for the cards
	
	public void addCards(ArrayList<Deck> al) {										// method to take in an arrayList and add it to the comPile arrayList
			comPile.addAll(al);
	}
	
	public ArrayList<Deck> removeCards (){										// method to remove cards from the comPile and return the comPileTemp
		ArrayList <Deck> comPileTemp = new ArrayList<Deck>();					// which is a copy of the comPile
		for(int i = 0; i<comPile.size(); i++) {
			comPileTemp.addAll(comPile);
			comPile.clear();
		}
		return comPileTemp;
	}

	public int getComPileSize() {
		return comPile.size();
	}

	public void setComPile(ArrayList<Deck> comPile) {
		this.comPile = comPile;
	}
	
}
