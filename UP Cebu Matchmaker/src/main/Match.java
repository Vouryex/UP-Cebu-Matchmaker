package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class Match {
	
	public static void update(int id) throws SQLException {
		Connection connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.out.println("connection not successful");
			System.exit(1);
		}
		
		// delete previous records in matches. two columns
		deletePreviousMatches(connection, id);
		
		// get gender
		String genderFiltered = getGender(connection, id);

		// get gender interest
		String genderInterestFiltered = getGenderInterest(connection, id);

		// get probable matches
		ArrayList<MatchesData> probableMatches = getProbableMatches(connection, genderInterestFiltered, genderFiltered, id);
		
		// get data of user
		MatchesData userData = getUserData(connection, id);
		
		// compare factors, assign scores.
		compare(userData, probableMatches);
		
		// insert top matches. insert if id is not present in ranked_matches, otherwise update
		insertTopMatches(connection, id, probableMatches);
		
		// insert matches
		insertMatches(connection, id, probableMatches);
	}
	
	private static void deletePreviousMatches(Connection connection, int id) throws SQLException {
		String query = "DELETE FROM matches WHERE user1 = ? OR user2 = ?";	
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private static String getGender(Connection connection, int id) throws SQLException {
		String gender = "";
		
		String query = "SELECT gender FROM user WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			gender = resultSet.getString("gender");
		}
		preparedStatement.close();
		return filterGender(gender);
	}
	
	private static String filterGender(String gender) {
		if(gender.equals("Male")) {
			return "Man";
		} else {
			return "Woman";
		}
	}
	
	private static String getGenderInterest(Connection connection, int id) throws SQLException {
		String genderInterest = "";
		
		String query = "SELECT gender FROM gender_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			genderInterest = resultSet.getString("gender");
		}
		preparedStatement.close();
		return filterGenderInterest(genderInterest);
	}
	
	private static String filterGenderInterest(String genderInterest) {
		if(genderInterest.equals("Man")) {
			return "Male";
		} else {
			return "Female";
		}
	}
	
	private static ArrayList<MatchesData> getProbableMatches(Connection connection, 
			String genderInterest, String gender, int id) throws SQLException 
	{
		ArrayList<MatchesData> probableMatches = new ArrayList<MatchesData>();
		
		String query = "SELECT id, age, body_type, drinker, height, political_party " +
					   "FROM user " +
					   "JOIN gender_interests " +
					   "ON user.id = gender_interests.user_id " +
					   "WHERE user.gender = ? AND gender_interests.gender = ? AND user.id != ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, genderInterest);
		preparedStatement.setString(2, gender);
		preparedStatement.setInt(3, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			int matchId = resultSet.getInt("id");
			MatchesData data = new MatchesData(matchId, resultSet.getInt("age"), 
					resultSet.getString("body_type"), resultSet.getString("drinker"), 
					resultSet.getInt("height"), resultSet.getString("political_party"));
			
			setAgeInterest(matchId, data, connection);
			setBodyTypeInterests(matchId, data, connection);
			setHeightInterests(matchId, data, connection);
			setHobbies(matchId, data, connection);
			setMovies(matchId, data, connection);
			setMusic(matchId, data, connection);
			setPets(matchId, data, connection);
			setSports(matchId, data, connection);

			probableMatches.add(data);
		}
		preparedStatement.close();
		return probableMatches;
	}
	
	private static MatchesData getUserData(Connection connection, int id) throws SQLException {
		String query = "SELECT age, body_type, drinker, height, political_party " +
					   "FROM user " +
					   "WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		MatchesData userData = null;
		if(resultSet.next()) {
			userData = new MatchesData(id, resultSet.getInt("age"), 
					resultSet.getString("body_type"), resultSet.getString("drinker"), 
					resultSet.getInt("height"), resultSet.getString("political_party"));
			
			setAgeInterest(id, userData, connection);
			setBodyTypeInterests(id, userData, connection);
			setHeightInterests(id, userData, connection);
			setHobbies(id, userData, connection);
			setMovies(id, userData, connection);
			setMusic(id, userData, connection);
			setPets(id, userData, connection);
			setSports(id, userData, connection);
		}
		preparedStatement.close();
		return userData;
	}
	
	private static void setAgeInterest(int matchId, MatchesData data, Connection connection) throws SQLException {
		String query = "SELECT age_min, age_max FROM age_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, matchId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			int ageMinInterests = resultSet.getInt("age_min");
			int ageMaxInterests = resultSet.getInt("age_max");
			data.setAgeMinInterests(ageMinInterests);
			data.setAgeMaxInterests(ageMaxInterests);
		}
		preparedStatement.close();
	}
	
	private static void setBodyTypeInterests(int matchId, MatchesData data, Connection connection) throws SQLException {
		String query = "SELECT body_type FROM body_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, matchId);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<String> bodyTypeInterests = new ArrayList<String>();
		while(resultSet.next()) {
			String bodyType = resultSet.getString("body_type");
			bodyTypeInterests.add(bodyType);
		}
		data.setBodyTypeInterests(bodyTypeInterests);
		preparedStatement.close();
	}
	
	private static void setHeightInterests(int matchId, MatchesData data, Connection connection) throws SQLException {
		String query = "SELECT height_min, height_max FROM height_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, matchId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			int heightMinInterests = resultSet.getInt("height_min");
			int heightMaxInterests = resultSet.getInt("height_max");
			data.setHeightMinInterests(heightMinInterests);
			data.setHeightMaxInterests(heightMaxInterests);
		}
		preparedStatement.close();
	}
	
	private static void setHobbies(int matchId, MatchesData data, Connection connection) throws SQLException {
		String query = "SELECT hobby FROM hobbies_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, matchId);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<String> hobbies = new ArrayList<String>();
		while(resultSet.next()) {
			String hobby = resultSet.getString("hobby");
			hobbies.add(hobby);
		}
		data.setHobbies(hobbies);
		preparedStatement.close();
	}
	
	private static void setMovies(int matchId, MatchesData data, Connection connection) throws SQLException {
		String query = "SELECT genre FROM movie_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, matchId);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<String> movies = new ArrayList<String>();
		while(resultSet.next()) {
			String genre = resultSet.getString("genre");
			movies.add(genre);
		}
		data.setMovies(movies);
		preparedStatement.close();
	}
	
	private static void setMusic(int matchId, MatchesData data, Connection connection) throws SQLException {
		String query = "SELECT genre FROM music_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, matchId);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<String> music = new ArrayList<String>();
		while(resultSet.next()) {
			String genre = resultSet.getString("genre");
			music.add(genre);
		}
		data.setMusic(music);
		preparedStatement.close();
	}
	
	private static void setPets(int matchId, MatchesData data, Connection connection) throws SQLException {
		String query = "SELECT pet FROM pets_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, matchId);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<String> pets = new ArrayList<String>();
		while(resultSet.next()) {
			String pet = resultSet.getString("pet");
			pets.add(pet);
		}
		data.setPets(pets);;
		preparedStatement.close();
	}
	
	private static void setSports(int matchId, MatchesData data, Connection connection) throws SQLException {
		String query = "SELECT sport FROM sports_interests WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, matchId);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<String> sports = new ArrayList<String>();
		while(resultSet.next()) {
			String sport = resultSet.getString("sport");
			sports.add(sport);
		}
		data.setSports(sports);
		preparedStatement.close();
	}
	
	private static void compare(MatchesData userData, ArrayList<MatchesData> probableMatches) {
		for(MatchesData probableMatch : probableMatches) {
			float score = 0;
			score += compareAge(userData.getAgeMinInterests(), 
								userData.getAgeMaxInterests(), probableMatch.getAge(),
								probableMatch.getAgeMinInterests(),
								probableMatch.getAgeMaxInterests(), userData.getAge());
			
			score += compareBodyType(userData.getBodyTypeInterests(), probableMatch.getBodyType(),
									 probableMatch.getBodyTypeInterests(), userData.getBodyType());

			score += compareDrinker(userData.getDrinker(), probableMatch.getDrinker());

			score += compareHeight(userData.getHeightMinInterests(),
								   userData.getHeightMaxInterests(), probableMatch.getHeight(),
								   probableMatch.getHeightMinInterests(),
								   probableMatch.getHeightMaxInterests(), userData.getHeight());

			score += comparePoliticalParty(userData.getPoliticalParty(), probableMatch.getPoliticalParty());

			score += compareHobbies(userData.getHobbies(), probableMatch.getHobbies());

			score += compareMovies(userData.getMovies(), probableMatch.getMovies());

			score += compareMusic(userData.getMusic(), probableMatch.getMusic());

			score += comparePets(userData.getPets(), probableMatch.getPets());

			score += compareSports(userData.getSports(), probableMatch.getSports());

			probableMatch.setScore((int)score);
		}
	}
	
	private static int compareAge(int userAgeMinInterest, int userAgeMaxInterest, int matchAge, 
								  int matchAgeMinInterest, int matchAgeMaxInterest, int userAge)
	{
		int score = 0;
		if(matchAge >= userAgeMinInterest && matchAge <= userAgeMaxInterest &&
		   userAge >= matchAgeMinInterest && userAge <= matchAgeMaxInterest) 
		{
			score = 16;
		} else if((matchAge >= userAgeMinInterest && matchAge <= userAgeMaxInterest) ||
				  (userAge >= matchAgeMinInterest && userAge <= matchAgeMaxInterest)) 
		{
			score = 10;
		}
		
		return score;
	}
	
	private static int compareBodyType(ArrayList<String> userBodyTypeInterests, String matchBodyType,
									   ArrayList<String> matchBodyTypeInterests, String userBodyType) 
	{
		int score = 0;
		
		if(dataFound(userBodyTypeInterests, matchBodyType) && dataFound(matchBodyTypeInterests, userBodyType)) {
			score = 14;
		} else if(dataFound(userBodyTypeInterests, matchBodyType) || dataFound(matchBodyTypeInterests, userBodyType)) {
			score = 9;
		}
		
		return score;
	}
	
	private static boolean dataFound(ArrayList<String> dataList, String data) {
		for(String dataInList : dataList) {
			if(dataInList.equals(data)) {
				return true;
			}
		}
		return false;
	}
	
	private static int compareDrinker(String userDrinker, String matchDrinker) {
		if(userDrinker.equals(matchDrinker)) {
			return 5;
		} else {
			return 0;
		}
	}
	
	private static int compareHeight(int userHeightMinInterest, int userHeightMaxInterest, 
									 int matchHeight, int matchHeightMinInterest,
									 int matchHeightMaxInterest, int userHeight) 
	{
		int score = 0;
		if(matchHeight >= userHeightMinInterest && matchHeight <= userHeightMaxInterest &&
		   userHeight >= matchHeightMinInterest && userHeight <= matchHeightMaxInterest) 
		{
			score = 12;
		} else if((matchHeight >= userHeightMinInterest && matchHeight <= userHeightMaxInterest) ||
				  (userHeight >= matchHeightMinInterest && userHeight <= matchHeightMaxInterest)) 
		{
			score = 7;
		}
		
		return score;
	}
	
	private static int comparePoliticalParty(String userParty, String matchParty) {
		if(userParty.equals(matchParty)) {
			return 12;
		} else {
			return 0;
		}
	}
	
	private static float compareHobbies(ArrayList<String> userHobbies, ArrayList<String> matchHobbies) {
		float score = 9;
		
		int selected = selected(userHobbies, matchHobbies);
		int matches = matches(userHobbies, matchHobbies);
		score =  score * ((float)matches / selected);
		
		return score;
	}
	
	private static float compareMovies(ArrayList<String> userMovies, ArrayList<String> matchMovies) {
		float score = 9;
		
		int selected = selected(userMovies, matchMovies);
		int matches = matches(userMovies, matchMovies);
		score = score * ((float)matches / selected);
		
		return score;
	}
	
	private static float compareMusic(ArrayList<String> userMusic, ArrayList<String> matchMusic) {
		float score = 9;
		
		int selected = selected(userMusic, matchMusic);
		int matches = matches(userMusic, matchMusic);
		score = score * ((float)matches / selected);
		
		return score;
	}
	
	private static float comparePets(ArrayList<String> userPets, ArrayList<String> matchPets) {
		float score = 6;
		
		int selected = selected(userPets, matchPets);
		int matches = matches(userPets, matchPets);
		score = score * ((float)matches / selected);
		
		return score;
	}
	
	private static float compareSports(ArrayList<String> userSports, ArrayList<String> matchSports) {
		float score = 8;
		
		int selected = selected(userSports, matchSports);
		int matches = matches(userSports, matchSports);
		score = score * ((float)matches / selected);
		
		return score;
	}
	
	private static int selected(ArrayList<String> userData, ArrayList<String> matchData) {
		ArrayList<String> selectedData = new ArrayList<String>();
		
		for(String data : userData) {
			if(!containsData(selectedData, data)) {
				selectedData.add(data);
			}
		}
		
		for(String data : matchData) {
			if(!containsData(selectedData, data)) {
				selectedData.add(data);
			}
		}
		
		return selectedData.size();
	}
	
	private static boolean containsData(ArrayList<String> dataList, String data) {
		for(String dataInList : dataList) {
			if(dataInList.equals(data)) {
				return true;
			}
		}
		return false;
	}
	
	private static int matches(ArrayList<String> userData, ArrayList<String> matchData) {
		int match = 0;
		
		for(String dataInUser : userData) {
			if(containsData(matchData, dataInUser)) {
				match++;
			}
		}
		
		return match;
	}
	
	private static void insertTopMatches(Connection connection, int id, ArrayList<MatchesData> matches) throws SQLException {
		ArrayList<MatchesData> ignore = new ArrayList<MatchesData>();
		
		MatchesData first = null;
		MatchesData second = null;
		MatchesData third = null;
		MatchesData fourth = null;
		
		if(matches.size() >= 1) {
			first = getTopMatch(matches, ignore);
			ignore.add(first);
		}

		if(matches.size() >= 2) {
			second = getTopMatch(matches, ignore);
			ignore.add(second);
		}

		if(matches.size() >= 3) {
			third = getTopMatch(matches, ignore);
			ignore.add(third);
		}

		if(matches.size() >= 4) {
			fourth = getTopMatch(matches, ignore);
			ignore.add(fourth);
		}

		
		if(idExistsRankedMatch(connection, id)) {
			updateRankedMatches(connection, id, first, second, third, fourth);
		} else {
			insertRankedMatches(connection, id, first, second, third, fourth);
		}
	}
	
	private static MatchesData getTopMatch(ArrayList<MatchesData> matches, ArrayList<MatchesData> ignore) {
		int startIndex = getStartIndexMatches(matches, ignore);
		MatchesData top = matches.get(startIndex);
		
		for(int i = startIndex + 1; i < matches.size(); i++) {
			if(matches.get(i).getScore() > top.getScore() && !containsMatchData(ignore, matches.get(i))) {
				top = matches.get(i);
			}
		}
		
		return top;
	}
	
	private static int getStartIndexMatches(ArrayList<MatchesData> matches, ArrayList<MatchesData> ignore) {
		for(int i = 0 ; i < matches.size(); i++) {
			if(!containsMatchData(ignore, matches.get(i))) {
				return i;
			}
		}
		return 0;
	}
	
	private static boolean containsMatchData(ArrayList<MatchesData> dataList, MatchesData data) {
		for(MatchesData dataInList : dataList) {
			if(data.equals(dataInList)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean idExistsRankedMatch(Connection connection, int id) throws SQLException {
		String query = "SELECT * FROM ranked_matches WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		preparedStatement.close();
		return resultSet.next();
	}
	
	private static void updateRankedMatches(Connection connection, int id, MatchesData first, 
			MatchesData second, MatchesData third, MatchesData fourth) throws SQLException 
	{
		String query = "UPDATE ranked_matches " +
				   "SET first = ?, second = ?, third = ?, fourth = ? " +
				   "WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		setIntOrNullMatches(preparedStatement, 1, first);
		setIntOrNullMatches(preparedStatement, 2, second);
		setIntOrNullMatches(preparedStatement, 3, third);
		setIntOrNullMatches(preparedStatement, 4, fourth);
		preparedStatement.setInt(5, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private static void setIntOrNullMatches(PreparedStatement preparedStatement,
			int paramIndex, MatchesData data) throws SQLException 
	{
		if(data != null) {
			preparedStatement.setInt(paramIndex, data.getId());
		} else {
			preparedStatement.setNull(paramIndex, Types.INTEGER);
		}
	}
	
	private static void insertRankedMatches(Connection connection, int id, MatchesData first, 
			MatchesData second, MatchesData third, MatchesData fourth) throws SQLException 
	{
		String query = "INSERT INTO ranked_matches(user_id, first, second, third, fourth) " +
				   "VALUES(?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		setIntOrNullMatches(preparedStatement, 2, first);
		setIntOrNullMatches(preparedStatement, 3, second);
		setIntOrNullMatches(preparedStatement, 4, third);
		setIntOrNullMatches(preparedStatement, 5, fourth);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private static void insertMatches(Connection connection, int id, ArrayList<MatchesData> probableMatches) throws SQLException {
		String query = "INSERT INTO matches(user1, user2, score) VALUES(?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		for(MatchesData match : probableMatches) {
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, match.getId());
			preparedStatement.setInt(3, match.getScore());
			preparedStatement.executeUpdate();
		}
		preparedStatement.close();
	}
}
