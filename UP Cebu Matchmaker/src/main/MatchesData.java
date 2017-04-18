package main;

import java.util.ArrayList;

public class MatchesData {
	private int id;
	private int age;
	private int ageMinInterests;
	private int ageMaxInterests;
	private String bodyType;
	private ArrayList<String> bodyTypeInterests;
	private String drinker;
	private int height;
	private int heightMinInterests;
	private int heightMaxInterests;
	private String politicalParty;
	private ArrayList<String> hobbies;
	private ArrayList<String> movies;
	private ArrayList<String> music;
	private ArrayList<String> pets;
	private ArrayList<String> sports;
	private int score;
	
	public MatchesData(int id, int age, String bodyType, String drinker, int height, String politicalParty) {
		this.id = id;
		this.age = age;
		this.bodyType = bodyType;
		this.drinker = drinker;
		this.height = height;
		this.politicalParty = politicalParty;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAgeMinInterests() {
		return ageMinInterests;
	}

	public void setAgeMinInterests(int ageMinInterests) {
		this.ageMinInterests = ageMinInterests;
	}

	public int getAgeMaxInterests() {
		return ageMaxInterests;
	}

	public void setAgeMaxInterests(int ageMaxInterests) {
		this.ageMaxInterests = ageMaxInterests;
	}

	public String getBodyType() {
		return bodyType;
	}
	
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	
	public ArrayList<String> getBodyTypeInterests() {
		return bodyTypeInterests;
	}

	public void setBodyTypeInterests(ArrayList<String> bodyTypeInterests) {
		this.bodyTypeInterests = bodyTypeInterests;
	}

	public String getDrinker() {
		return drinker;
	}
	
	public void setDrinker(String drinker) {
		this.drinker = drinker;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeightMinInterests() {
		return heightMinInterests;
	}

	public void setHeightMinInterests(int heightMinInterests) {
		this.heightMinInterests = heightMinInterests;
	}

	public int getHeightMaxInterests() {
		return heightMaxInterests;
	}

	public void setHeightMaxInterests(int heightMaxInterests) {
		this.heightMaxInterests = heightMaxInterests;
	}

	public String getPoliticalParty() {
		return politicalParty;
	}
	
	public void setPoliticalParty(String politicalParty) {
		this.politicalParty = politicalParty;
	}
	
	public ArrayList<String> getHobbies() {
		return hobbies;
	}
	
	public void setHobbies(ArrayList<String> hobbies) {
		this.hobbies = hobbies;
	}
	
	public ArrayList<String> getMovies() {
		return movies;
	}
	
	public void setMovies(ArrayList<String> movies) {
		this.movies = movies;
	}
	
	public ArrayList<String> getMusic() {
		return music;
	}
	
	public void setMusic(ArrayList<String> music) {
		this.music = music;
	}
	
	public ArrayList<String> getPets() {
		return pets;
	}
	
	public void setPets(ArrayList<String> pets) {
		this.pets = pets;
	}
	
	public ArrayList<String> getSports() {
		return sports;
	}
	
	public void setSports(ArrayList<String> sports) {
		this.sports = sports;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
}
