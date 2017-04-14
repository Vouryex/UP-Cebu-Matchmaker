package controller.createprofile;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import main.CreateProfileData;

public class ThirdPageController extends PageController implements Initializable {

	@FXML private CheckBox noAnswerMusic;
	@FXML private CheckBox jazz;
	@FXML private CheckBox blues;
	@FXML private CheckBox folk;
	@FXML private CheckBox rock;
	@FXML private CheckBox hiphop;
	@FXML private CheckBox rapping;
	@FXML private CheckBox edm;
	@FXML private CheckBox reggae;
	@FXML private CheckBox techno;
	@FXML private CheckBox punkRock;
	@FXML private CheckBox classical;
	@FXML private CheckBox dubstep;
	@FXML private CheckBox melody;
	@FXML private CheckBox country;
	@FXML private Hyperlink moreMusic;
	private ArrayList<CheckBox> musicList = new ArrayList<CheckBox>();
	
	@FXML private CheckBox noAnswerMovie;
	@FXML private CheckBox action;
	@FXML private CheckBox adventure;
	@FXML private CheckBox animation;
	@FXML private CheckBox biography;
	@FXML private CheckBox documentation;
	@FXML private CheckBox drama;
	@FXML private CheckBox family;
	@FXML private CheckBox fantasy;
	@FXML private CheckBox filmNoir;
	@FXML private CheckBox history;
	@FXML private CheckBox horror;
	@FXML private CheckBox music;
	@FXML private Hyperlink moreMovie;
	private ArrayList<CheckBox> movieList = new ArrayList<CheckBox>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initMusic(musicList);
		initMovie(movieList);
	}
	
	private void initMusic(ArrayList<CheckBox> musicList) {
		musicList.add(jazz);
		musicList.add(blues);
		musicList.add(folk);
		musicList.add(rock);
		musicList.add(hiphop);
		musicList.add(rapping);
		musicList.add(edm);
		musicList.add(reggae);
		musicList.add(techno);
		musicList.add(punkRock);
		musicList.add(classical);
		musicList.add(dubstep);
		musicList.add(melody);
		musicList.add(country);
	}
	
	private void initMovie(ArrayList<CheckBox> movieList) {
		movieList.add(action);
		movieList.add(adventure);
		movieList.add(animation);
		movieList.add(biography);
		movieList.add(documentation);
		movieList.add(drama);
		movieList.add(family);
		movieList.add(fantasy);
		movieList.add(filmNoir);
		movieList.add(history);
		movieList.add(horror);
		movieList.add(music);
	}
	
	@FXML
	private void noMusic() {
		if(noAnswerMusic.isSelected()) {
			for(CheckBox music : musicList) {
				music.setSelected(false);
			}
		} else {
			noAnswerMusic.setSelected(true);
		}
	}
	
	@FXML
	private void hasMusic() {
		noAnswerMusic.setSelected(false);
	}
	
	@FXML void noMovie() {
		if(noAnswerMovie.isSelected()) {
			for(CheckBox movie : movieList) {
				movie.setSelected(false);
			}
		} else {
			noAnswerMovie.setSelected(true);
		}
	}
	
	@FXML void hasMovie() {
		noAnswerMovie.setSelected(false);
	}

	@Override
	public void updateDataRepository(CreateProfileData dataRepository) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initSavedState(CreateProfileData dataRepository) {
		// TODO Auto-generated method stub
		
	}

}
