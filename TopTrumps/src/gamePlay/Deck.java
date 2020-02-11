package gamePlay;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Deck {
    private String name; 		// Global variables
    private int size;
    private int speed;
    private int range;
    private int firepower;
    private int cargo;

    public Deck(String name, int size, int speed, int range, int firepower, int cargo) { // Constructor including all the variables of the card
        this.name = name;
        this.size = size;
        this.speed = speed;
        this.range = range;
        this.firepower = firepower;
        this.cargo = cargo;
    }

    public static Deck[] shipArrayFill() { 						// Method to fill the ship Array of all 40 slots via fileIO upload
        Deck spaceshipArray[] = new Deck[40]; 					// Array of number of cards within the deck
        int pos = 0; 											// Integer for number of ships, used to cycle array later
        FileReader fr = null; 									// Opening filereader
        try {
            fr = new FileReader("TopTrumpsCsv.csv"); 			// Reading the path to the file in question
            Scanner s = new Scanner(fr); 						// Scanner to read through the file
            while (s.hasNextLine()) { 							// Loops until there are no more lines in the file
                String line = s.nextLine(); 					// Cycles through each line
                String[] shipData = line.split(",");			// Splits via comma (file is csv, each field deliminated by comma)
                String shipName = shipData[0]; 					// Putting the ship details into the newly created array
                int shipSize = Integer.parseInt(shipData[1]);
                int shipSpeed = Integer.parseInt(shipData[2]);
                int shipRange = Integer.parseInt(shipData[3]);
                int shipFirepower = Integer.parseInt(shipData[4]);
                int shipCargo = Integer.parseInt(shipData[5]);
                spaceshipArray[pos++] = new Deck(shipName, shipSize, shipSpeed, // Cycles array, puts the variables of the ship into the actual array
                    shipRange, shipFirepower, shipCargo);
                
            }
        } catch (FileNotFoundException e) { 					// Catches exception if file isn't found
            e.printStackTrace();
        } finally {
            try {
                fr.close();										 // Close filereader
            } catch (IOException e) {							 // Displays error message/details if ioexception
                e.printStackTrace();
            }
        }
        if (pos > 0) { 											 // If statement to print each ship in the array
            for (int i = 0; i < pos; i++) {}
        }
        return spaceshipArray;
    }
    public String toString() { 									// ToString to format and print the array
        return " " + "'" + name + "'" + "\n\t\tSize: " + size + 
        		"\n\t\tSpeed: " + speed + "\n\t\tRange: " + range +
        		"\n\t\tFirepower: " + firepower + "\n\t\tCargo: " + cargo;

    }
    public String getName() { 									// Getters to return private attributes
        return name;
    }
    public int getSize() {
        return size;
    }
    public int getSpeed() {
        return speed;
    }
    public int getRange() {
        return range;
    }
    public int getFirepower() {
        return firepower;
    }
    public int getCargo() {
        return cargo;
    }
   
}