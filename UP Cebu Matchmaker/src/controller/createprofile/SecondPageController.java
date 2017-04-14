package controller.createprofile;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import main.CreateProfileData;

public class SecondPageController extends PageController implements Initializable {

	@FXML private CheckBox noAnswerSports;
	@FXML private CheckBox aerobics;
	@FXML private CheckBox basketball;
	@FXML private CheckBox autoracing;
	@FXML private CheckBox billiards;
	@FXML private CheckBox baseball;
	@FXML private CheckBox bowling;
	@FXML private CheckBox cycling;
	@FXML private Hyperlink moreSports;
	private ArrayList<CheckBox> sports = new ArrayList<CheckBox>();
	
	@FXML private CheckBox noAnswerHobbies;
	@FXML private CheckBox camping;
	@FXML private CheckBox diningOut;
	@FXML private CheckBox coffee;
	@FXML private CheckBox gardening;
	@FXML private CheckBox cooking;
	@FXML private CheckBox movies;
	@FXML private Hyperlink moreHobbies;
	private ArrayList<CheckBox> hobbies = new ArrayList<CheckBox>();
	
	@FXML private CheckBox noPreference;
	@FXML private CheckBox cats;
	@FXML private CheckBox dogs;
	@FXML private CheckBox exotic;
	@FXML private CheckBox fish;
	@FXML private CheckBox horses;
	@FXML private CheckBox other;
	private ArrayList<CheckBox> pets = new ArrayList<CheckBox>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initSports(sports);
		initHobbies(hobbies);
		initPets(pets);
	}
	
	private void initSports(ArrayList<CheckBox> sports) {
		sports.add(aerobics);
		sports.add(basketball);
		sports.add(autoracing);
		sports.add(billiards);
		sports.add(baseball);
		sports.add(bowling);
		sports.add(cycling);
	}
	
	private void initHobbies(ArrayList<CheckBox> hobbies) {
		hobbies.add(camping);
		hobbies.add(diningOut);
		hobbies.add(coffee);
		hobbies.add(gardening);
		hobbies.add(cooking);
		hobbies.add(movies);
	}
	
	private void initPets(ArrayList<CheckBox> pets) {
		pets.add(cats);
		pets.add(dogs);
		pets.add(exotic);
		pets.add(fish);
		pets.add(horses);
		pets.add(other);
	}
	
	@FXML
	private void noSports() {
		if(noAnswerSports.isSelected()) {
			for(CheckBox sport : sports) {
				sport.setSelected(false);
			}
		} else {
			noAnswerSports.setSelected(true);
		}
	}
	
	@FXML
	private void hasSports() {
		noAnswerSports.setSelected(false);
	}
	
	@FXML
	private void noHobbies() {
		if(noAnswerHobbies.isSelected()) {
			for(CheckBox hobby : hobbies) {
				hobby.setSelected(false);
			}
		} else {
			noAnswerHobbies.setSelected(true);
		}
	}
	
	@FXML
	private void hasHobbies() {
		noAnswerHobbies.setSelected(false);
	}
	
	@FXML
	private void noPets() {
		if(noPreference.isSelected()) {
			for(CheckBox pet : pets) {
				pet.setSelected(false);
			}
		} else {
			noPreference.setSelected(true);
		}
	}
	
	@FXML
	private void hasPets() {
		noPreference.setSelected(false);
	}

	@Override
	public void updateDataRepository(CreateProfileData dataRepository) {
		ArrayList<String> sportsString = new ArrayList<String>();
		ArrayList<String> hobbiesString = new ArrayList<String>();
		ArrayList<String> petsString = new ArrayList<String>();
		convertString(sportsString, sports);
		convertString(hobbiesString, hobbies);
		convertString(petsString, pets);
		dataRepository.setSports(sportsString);
		dataRepository.setHobbies(hobbiesString);
		dataRepository.setPets(petsString);
	}
	
	private void convertString(ArrayList<String> destination, ArrayList<CheckBox> source) {
		for(CheckBox sourceValue : source) {
			if(sourceValue.isSelected()) {
				destination.add(sourceValue.getText());
			}
		}
	}

	@Override
	public void initSavedState(CreateProfileData dataRepository) {
		setValueCheckBox(dataRepository.getSports(), sports, noAnswerSports);
		setValueCheckBox(dataRepository.getHobbies(), hobbies, noAnswerHobbies);
		setValueCheckBox(dataRepository.getPets(), pets, noPreference);
	}
	
	private void setValueCheckBox(ArrayList<String> stringList, 
			ArrayList<CheckBox> checkBoxList, CheckBox defaultCheckBox) 
	{
		for(CheckBox checkBox : checkBoxList) {
			for(String string : stringList) {
				if(checkBox.getText().equals(string)) {
					checkBox.setSelected(true);
					defaultCheckBox.setSelected(false);
					stringList.remove(string);
					break;
				}
			}
		}
	}
}
