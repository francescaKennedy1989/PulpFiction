package Database;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//connection instructions used from DTA Lab session week 8

public class DatabaseConnection {								  //variables declared for data retrieved from database
    private int gamesPlayed = 0;
    private int computerWins = 0;
    private int humanWins = 0;
    private double avgDraws = 0;
    private int longestGame = 0;
   
    public DatabaseConnection() throws SQLException {
        try {														 //load the JDBC driver
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {						// try catch exception
            DatabaseView.DriverError();								// prints error message from Database View class
            e.printStackTrace();
            return;
        }
        
        DatabaseView.DriverFound();											// prints from Database View that the driver is loaded...
        Connection connection = null;										//proceed with a database connection
        
        String sql1 = "SELECT COUNT(GameID) AS Total_No_Games FROM Games;";						// SQL queries for previous games
        String sql2 = "SELECT COUNT (Winner) AS Computer_Wins FROM Games WHERE Winner > 1;";
        String sql3 = "SELECT COUNT (Winner) AS Human_Wins FROM Games WHERE Winner = 1;";
        String sql4 = "SELECT AVG(Draws) AS Avg_No_Draws FROM Games;";
        String sql5 = "SELECT MAX(no_of_rounds) AS Top_No_Rounds FROM Games;";


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/toptrumps",
    "postgres", "Thepeeps86");
        } catch (SQLException e) {
           DatabaseView.ConnectionFailedMessage();
            e.printStackTrace();
            return;
        }
        																								//try catch exception
        																								//connection to the database is done!
        if (connection != null) {
            try {
                DatabaseView.DBControlSuccess();
                Statement statement1 = connection.createStatement();									//sql statements created and executed, results returned and variables set -
                ResultSet resultGamesPlayed = statement1.executeQuery(sql1);							  //one for each sql statement
                while (resultGamesPlayed.next()) {														// while there is data in the set
                    gamesPlayed = resultGamesPlayed.getInt(1);											// return the type
                }
                Statement statement2 = connection.createStatement();
                ResultSet resultComputerWins = statement2.executeQuery(sql2);
                while (resultComputerWins.next()) {
                    computerWins = resultComputerWins.getInt(1);
                }
                Statement statement3 = connection.createStatement();
                ResultSet resultHumanWins = statement3.executeQuery(sql3);
                while (resultHumanWins.next()) {
                    humanWins = resultHumanWins.getInt(1);
                }
                Statement statement4 = connection.createStatement();
                ResultSet resultAvgDraws = statement4.executeQuery(sql4);
                while (resultAvgDraws.next()) {
                    avgDraws = resultAvgDraws.getInt(1);
                }
                Statement statement5 = connection.createStatement();
                ResultSet resultLongestGame = statement5.executeQuery(sql5);
                while (resultLongestGame.next()) {
                    longestGame = resultLongestGame.getInt(1);
                }

                 connection.close();																	//do not forget to close the connection to your database!


            } catch (SQLException e) {																	// try catch exception
                e.printStackTrace();
            } 
        } else {
        	DatabaseView.DBConnectionError();
        }
 
    }
    
    public int getGamesPlayed() {																		//getters so that other parts of the system can retrieve the data
        return gamesPlayed;

    }

    public int getComputerWins() {
        return computerWins;
    }
    public int getHumanWins() {
        return humanWins;
    }
    public double getAvgDraws() {
        return avgDraws;
    }
    public int getLongestGame() {
        return longestGame;
    }

    
}
