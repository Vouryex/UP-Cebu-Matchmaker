package main;

import java.io.File;
import java.util.ArrayList;

public class CreateProfileData {
	private String gender;
	private String minAge;
	private String maxAge;
	private String minHeight;
	private String maxHeight;
	private ArrayList<String> bodyType = new ArrayList<String>();
	
	private ArrayList<String> sports = new ArrayList<String>();
	private ArrayList<String> hobbies = new ArrayList<String>();
	private ArrayList<String> pets = new ArrayList<String>();
	
	private ArrayList<String> musicGenres = new ArrayList<String>();
	private ArrayList<String> movieGenres = new ArrayList<String>();
	
	private File profilePicture;
	
	public CreateProfileData() {
		profilePicture = new File("/resources/default pic.jpg");
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getMinAge() {
		return minAge;
	}
	
	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}
	
	public String getMaxAge() {
		return maxAge;
	}
	
	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}
	
	public String getMinHeight() {
		return minHeight;
	}
	
	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}
	
	public String getMaxHeight() {
		return maxHeight;
	}
	
	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public ArrayList<String> getBodyType() {
		return bodyType;
	}

	public void setBodyType(ArrayList<String> bodyType) {
		this.bodyType = bodyType;
	}

	public ArrayList<String> getSports() {
		return sports;
	}

	public void setSports(ArrayList<String> sports) {
		this.sports = sports;
	}

	public ArrayList<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(ArrayList<String> hobbies) {
		this.hobbies = hobbies;
	}

	public ArrayList<String> getPets() {
		return pets;
	}

	public void setPets(ArrayList<String> pets) {
		this.pets = pets;
	}

	public ArrayList<String> getMusicGenres() {
		return musicGenres;
	}

	public void setMusicGenres(ArrayList<String> musicGenres) {
		this.musicGenres = musicGenres;
	}

	public ArrayList<String> getMovieGenres() {
		return movieGenres;
	}

	public void setMovieGenres(ArrayList<String> movieGenres) {
		this.movieGenres = movieGenres;
	}

	public File getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(File profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
}
