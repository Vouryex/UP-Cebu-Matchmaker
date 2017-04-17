package model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			insertSportsInterests(preparedStatement, dataRepository, id);
			insertHobbiesInterests(preparedStatement, dataRepository, id);
			insertPetsInterests(preparedStatement, dataRepository, id);
			insertMusicInterests(preparedStatement, dataRepository, id);
			insertMovieInterests(preparedStatement, dataRepository, id);
			insertProfilePicture(preparedStatement, dataRepository, id);
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
		insertEachData(preparedStatement, dataRepository.getBodyType(), id);

		preparedStatement.close();
	}
	
	private void insertEachData(PreparedStatement preparedStatement, 
			ArrayList<String> dataList, int id) throws SQLException 
	{
		for(String data : dataList) {
			preparedStatement.setString(1, String.valueOf(id));
			preparedStatement.setString(2, data);
			
			preparedStatement.executeUpdate();
		}
	}
	
	private void insertSportsInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO sports_interests(user_id, sport)" + 
				   "VALUES(?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		insertEachData(preparedStatement, dataRepository.getSports(), id);

		preparedStatement.close();
	}

	
	private void insertHobbiesInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO hobbies_interests(user_id, hobby)" + 
				   "VALUES(?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		insertEachData(preparedStatement, dataRepository.getHobbies(), id);

		preparedStatement.close();
	}
	

	
	private void insertPetsInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO pets_interests(user_id, pet)" + 
				   "VALUES(?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		insertEachData(preparedStatement, dataRepository.getPets(), id);

		preparedStatement.close();
	}
	
	private void insertMusicInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO music_interests(user_id, genre)" + 
				   "VALUES(?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		insertEachData(preparedStatement, dataRepository.getMusicGenres(), id);

		preparedStatement.close();
	}
	
	private void insertMovieInterests(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		String query = "INSERT INTO movie_interests(user_id, genre)" + 
				   "VALUES(?, ?)";
		
		preparedStatement = connection.prepareStatement(query);
		insertEachData(preparedStatement, dataRepository.getMovieGenres(), id);

		preparedStatement.close();
	}
	
	private void insertProfilePicture(PreparedStatement preparedStatement, 
			CreateProfileData dataRepository, int id) throws SQLException 
	{
		try {
			String query = "UPDATE user " + 
					   	   "SET profile_picture = ? " + 
					   	   "WHERE id = ?";
			
			File profilePicture = dataRepository.getProfilePicture();
			FileInputStream fis = new FileInputStream(profilePicture);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for(int readNum; (readNum = fis.read(buf)) != -1;) {
				baos.write(buf, 0, readNum);
			}
			fis.close();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBytes(1, baos.toByteArray());
			preparedStatement.setString(2, String.valueOf(id));
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		preparedStatement.close();
	}
	
	public void deleteMovies(int id) throws SQLException {
		String sql = "DELETE FROM movie_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deleteMusic(int id) throws SQLException {
		String sql = "DELETE FROM music_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deletePets(int id) throws SQLException {
		String sql = "DELETE FROM pets_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deleteHobbies(int id) throws SQLException {
		String sql = "DELETE FROM hobbies_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deleteSports(int id) throws SQLException {
		String sql = "DELETE FROM sports_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deleteBody(int id) throws SQLException {
		String sql = "DELETE FROM body_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deleteHeight(int id) throws SQLException {
		String sql = "DELETE FROM height_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deleteAge(int id) throws SQLException {
		String sql = "DELETE FROM age_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deleteGender(int id) throws SQLException {
		String sql = "DELETE FROM gender_interests WHERE user_id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
	
	public void deleteUser(int id) throws SQLException {
		String sql = "DELETE FROM user WHERE id = ?";
		PreparedStatement pstmt = null;
        try {
        	pstmt = connection.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        	pstmt.close();
        }
	}
}
