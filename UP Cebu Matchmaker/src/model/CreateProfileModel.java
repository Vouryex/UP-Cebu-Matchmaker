package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import main.CreateProfileData;
import main.SqLiteConnection;

public class CreateProfileModel {
	Connection connection;
	public CreateProfileModel() {
		connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.out.println("connection not successful");
			System.exit(1);
		}
	}
	
	public boolean insertData(CreateProfileData dataRepository, int id) throws SQLException {
		PreparedStatement preparedStatement = null;

		try {
			insertGenderInterests(preparedStatement, dataRepository, id);
			insertAgeInterests(preparedStatement, dataRepository, id);
			insertHeightInterests(preparedStatement, dataRepository, id);
			insertBodyInterests(preparedStatement, dataRepository, id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} 
	}
	
	private void insertGenderInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO gender_interests(user_id, gender)" + 
				   "VALUES(?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, String.valueOf(id));
		preparedStatement.setString(2, dataRepository.getGender());
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private void insertAgeInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO age_interests(user_id, age_min, age_max)" + 
				   "VALUES(?, ?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, String.valueOf(id));
		preparedStatement.setString(2, dataRepository.getMinAge());
		preparedStatement.setString(3, dataRepository.getMaxAge());
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private void insertHeightInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO height_interests(user_id, height_min, height_max)" + 
				   "VALUES(?, ?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, String.valueOf(id));
		preparedStatement.setString(2, dataRepository.getMinHeight());
		preparedStatement.setString(3, dataRepository.getMaxHeight());
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private void insertBodyInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO body_interests(user_id, body_type)" + 
				   "VALUES(?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		insertEachBodyType(preparedStatement, dataRepository.getBodyType(), id);

		preparedStatement.close();
	}
	
	private void insertEachBodyType(PreparedStatement preparedStatement, 
			ArrayList<String> bodyTypes, int id) throws SQLException 
	{
		for(String bodyType : bodyTypes) {
			preparedStatement.setString(1, String.valueOf(id));
			preparedStatement.setString(2, bodyType);
			
			preparedStatement.executeUpdate();
		}
	}
}
