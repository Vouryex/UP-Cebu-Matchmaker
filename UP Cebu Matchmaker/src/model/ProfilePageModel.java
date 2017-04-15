package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.SqLiteConnection;

public class ProfilePageModel {
	Connection connection;
	public ProfilePageModel() {
		connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.out.println("connection not successful");
			System.exit(1);
		}
	}
	
	public ArrayList<String> getInfo(int id) {
		ArrayList<String> info = new ArrayList<String>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM user WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			info.add(resultSet.getString("firstname"));
			info.add(resultSet.getString("surname"));
			info.add(((Integer) resultSet.getInt("age")).toString());
			info.add(resultSet.getString("username"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
		
	}
	
	public String retrieveGender(int id) throws SQLException {
		System.out.println(id);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String gender = "";
		String query = "SELECT * FROM gender_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			gender = resultSet.getString("gender");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return gender;
	}
	
	public ArrayList<Integer> retrieveAges(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> ageList = new ArrayList<Integer>();
		String query = "SELECT * FROM age_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			ageList.add(resultSet.getInt("age_min"));
			ageList.add(resultSet.getInt("age_max"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return ageList;
	}
	
	public ArrayList<Integer> retrieveHeights(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Integer> heightList = new ArrayList<Integer>();
		String query = "SELECT * FROM height_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			heightList.add(resultSet.getInt("height_min"));
			heightList.add(resultSet.getInt("height_max"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return heightList;
	}
	
	public ArrayList<String> retrieveBodyTypes(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> bodyList = new ArrayList<String>();
		String query = "SELECT * FROM body_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
            	bodyList.add(resultSet.getString("body_type"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return bodyList;
	}
	
	public ArrayList<String> retrieveSports(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> sportList = new ArrayList<String>();
		String query = "SELECT * FROM sports_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				sportList.add(resultSet.getString("sport"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return sportList;
	}
	
	public ArrayList<String> retrieveHobbies(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> hobbyList = new ArrayList<String>();
		String query = "SELECT * FROM hobbies_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				hobbyList.add(resultSet.getString("hobby"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return hobbyList;
	}
	
	public ArrayList<String> retrievePets(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> petList = new ArrayList<String>();
		String query = "SELECT * FROM pets_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				petList.add(resultSet.getString("pet"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return petList;
	}
	
	public ArrayList<String> retrieveMusic(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> musicList = new ArrayList<String>();
		String query = "SELECT * FROM music_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				musicList.add(resultSet.getString("genre"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return musicList;
	}
	
	public ArrayList<String> retrieveMovies(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> movieList = new ArrayList<String>();
		String query = "SELECT * FROM movie_interests WHERE user_id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				movieList.add(resultSet.getString("genre"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return movieList;
	}
}