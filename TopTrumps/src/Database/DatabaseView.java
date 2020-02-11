package Database;

public class DatabaseView {
	public static void ConnectionFailedMessage() {
		System.out.println("Connection failed!");
	
	}
		
	public static void DBConnectionError() {
		System.out.println("Failed to establish connection.");
		
	}

	public static void DBControlSuccess() {
		System.out.println("Controlling your database!");
		
	}

	public static void DriverError() {
		System.out.println(" Could not find JBDC driver.");
		
	}
		public static void DriverFound() {
			System.out.println("Postgresql Driver found.");
		
	}

}
