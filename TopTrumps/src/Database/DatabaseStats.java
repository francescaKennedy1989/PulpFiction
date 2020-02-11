package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStats {
	 private static Statement gamesTable;
	
public static void sendStatisticsDB(int GWinner, int CWins, int HWins, int NoDraws, int NoRounds) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/toptrumps",
    "postgres", "Thepeeps86");
        } catch (SQLException e) {
            DatabaseView.ConnectionFailedMessage();
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            try {
                DatabaseView.DBControlSuccess();
                gamesTable = connection.createStatement();
                gamesTable.executeUpdate("INSERT INTO games(winner,draws,no_of_rounds) VALUES(" + GWinner + "," + NoDraws + "," + NoRounds + ")");

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } // try catch exception
        } else {
            DatabaseView.DBConnectionError();
        }
    }
}