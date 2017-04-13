package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.SqLiteConnection;
import main.UserData;

public class SignUpModel {
	Connection connection;
	public SignUpModel() {
		connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.out.println("connection not successful");
			System.exit(1);
		}
	}
	
	public boolean isDataInserted(UserData u) throws SQLException {
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO user(firstname, surname, age, username, password, gender, political_party, height, body_type, drinker) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, u.getFirstname());
			preparedStatement.setString(2, u.getSurname());
			preparedStatement.setString(3, u.getAge());
			preparedStatement.setString(4, u.getUsername());
			preparedStatement.setString(5, u.getPassword());
			preparedStatement.setString(6, u.getGender());
			preparedStatement.setString(7, u.getPoliticalParty());
			preparedStatement.setString(8, u.getHeight());
			preparedStatement.setString(9, u.getBodyType());
			preparedStatement.setString(10, u.getDrinker());
			
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} finally {
			preparedStatement.close();
		}
	}
	
	public boolean confirmData(String height, String bodyType, String drinker) throws SQLException {
		PreparedStatement preparedStatement = null;
		String query = "UPDATE user SET political_party = ? , "
                + "height = ? " + "body_type = ? " + "drinker = ? "
                + "WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, height);
			preparedStatement.setString(2, bodyType);
			preparedStatement.setString(3, drinker);
			
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
