package model;

import java.sql.*;
import java.util.ArrayList;

import main.SqLiteConnection;
import main.UserData;

public class LoginModel {
	Connection connection;
	public LoginModel() {
		connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.out.println("connection not successful");
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
	
	public boolean isLogin(String user, String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM user WHERE lower(username) = lower(?) AND password = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public ArrayList<String> getInfo(String user) throws SQLException {
		ArrayList<String> info = new ArrayList<String>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM user WHERE username = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			resultSet = preparedStatement.executeQuery();
			info.add(resultSet.getString("firstname"));
			info.add(resultSet.getString("surname"));
			info.add(((Integer) resultSet.getInt("age")).toString());
			info.add(resultSet.getString("username"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return info;
	}
	
	public int getID(String user) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM user WHERE username = ?";
		int id = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			resultSet = preparedStatement.executeQuery();
			id = resultSet.getInt("id");
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return id;
	}
}
