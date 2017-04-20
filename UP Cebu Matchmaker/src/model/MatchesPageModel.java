package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.MatchesData;
import main.SqLiteConnection;

public class MatchesPageModel {
	Connection connection;
	public MatchesPageModel() {
		connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.out.println("connection not successful");
			System.exit(1);
		}
	}
	
	public ArrayList<MatchesData> getMatches(int id) throws SQLException {
		ArrayList<MatchesData> matches = new ArrayList<MatchesData>();
		String query = "SELECT user.id, user.profile_picture, matches.score " +
				   "FROM user " +
				   "JOIN ranked_matches ON user.id = ranked_matches.user_id " +
				   "JOIN matches ON user.id = matches.user1 OR user.id = matches.user2 " +
				   "WHERE user.id IN (SELECT first FROM ranked_matches WHERE user_id = ? " +
				   "UNION SELECT second FROM ranked_matches WHERE user_id = ? " + 
				   "UNION SELECT third FROM ranked_matches WHERE user_id = ? " + 
				   "UNION SELECT fourth FROM ranked_matches WHERE user_id = ?) " +
				   "AND (matches.user1 = ? OR matches.user2 = ?) " +
				   "ORDER BY score DESC";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);
		preparedStatement.setInt(3, id);
		preparedStatement.setInt(4, id);
		preparedStatement.setInt(5, id);
		preparedStatement.setInt(6, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		ArrayList<String> matchesImageOrder = new ArrayList<String>();
		matchesImageOrder.add("first match.jpg");
		matchesImageOrder.add("second match.jpg");
		matchesImageOrder.add("third match.jpg");
		matchesImageOrder.add("fourth match.jpg");
		int imageIndex = 0;
		
		while(resultSet.next()) {
			MatchesData match = new MatchesData();
			int matchId = resultSet.getInt("id");
			File matchPicture = getPicture(resultSet, matchesImageOrder, imageIndex++);
			int score = resultSet.getInt("score");
			match.setId(matchId);
			match.setProfilePicture(matchPicture);
			match.setScore(score);
			matches.add(match);
			
			// test
			System.out.println("match id: " + matchId + "score: " + score);
		}
		return matches;
	}
	
	private File getPicture(ResultSet resultSet, ArrayList<String> matchesImageOrder, int imageIndex) throws SQLException {
		File matchPicture = new File(matchesImageOrder.get(imageIndex++));
        FileOutputStream fos = null;
       
        try {
        	InputStream input = resultSet.getBinaryStream("profile_picture");
			fos = new FileOutputStream(matchPicture);
	        byte[] buffer = new byte[1024];
	        for(int readNum; (readNum = input.read(buffer)) != -1;) {
	            fos.write(buffer, 0, readNum);
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        
        return matchPicture;
	}

}
