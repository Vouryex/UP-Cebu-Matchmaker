package application;

import java.sql.*;

public class LoginModel {
	Connection connection;
	public LoginModel() {
		connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.exit(1);
		}
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
