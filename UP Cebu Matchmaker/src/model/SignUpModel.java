package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.SqLiteConnection;

public class SignUpModel {
	Connection connection;
	public SignUpModel() {
		connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.out.println("connection not successful");
			System.exit(1);
		}
	}
	
	public boolean isDataInserted(String firstName, String surname, String birthdate, String user, String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO user(firstname, surname, birthdate, username, password) VALUES(?, ?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, birthdate);
			preparedStatement.setString(4, user);
			preparedStatement.setString(5, pass);
			
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} finally {
			preparedStatement.close();
		}
		
	}
}
