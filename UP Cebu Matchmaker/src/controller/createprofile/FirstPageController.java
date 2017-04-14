package controller.createprofile;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import main.CreateProfileData;
import main.DataMethod;

public class FirstPageController extends PageController implements Initializable {

	@FXML private ComboBox<String> genderCombox;
	@FXML private ComboBox<String> minAgeCombox;
	@FXML private ComboBox<String> maxAgeCombox;
	@FXML private Label heightQuestion;
	@FXML private ComboBox<String> minHeightCombox;
	@FXML private ComboBox<String> maxHeightCombox;
	@FXML private CheckBox noPreference;
	@FXML private CheckBox average;
	@FXML private CheckBox skinny;
	@FXML private CheckBox stocky;
	@FXML private CheckBox heavyset;
	@FXML private CheckBox fullFigured;
	@FXML private CheckBox athletic;
	@FXML private Hyperlink moreBodyType;
	private ArrayList<CheckBox> bodyTypes = new ArrayList<CheckBox>();
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		genderCombox.getItems().addAll("Man", "Woman");
		ArrayList ageList = new ArrayList<String>();
		ArrayList heightList = new ArrayList<String>();
		DataMethod.addAges(ageList);
		DataMethod.addHeights(heightList);
		minAgeCombox.getItems().addAll(ageList);
		maxAgeCombox.getItems().addAll(ageList);
		minHeightCombox.getItems().addAll(heightList);
		maxHeightCombox.getItems().addAll(heightList);
		initBodyTypes(bodyTypes);
	}
	
	private void initBodyTypes(ArrayList<CheckBox> bodyTypes) {
		bodyTypes.add(average);
		bodyTypes.add(skinny);
		bodyTypes.add(stocky);
		bodyTypes.add(heavyset);
		bodyTypes.add(fullFigured);
		bodyTypes.add(athletic);
	}
	
	@FXML
	private void noPreferenceClicked() {
		if(noPreference.isSelected()) {
			for(CheckBox bodyType : bodyTypes) {
				bodyType.setSelected(false);
			}
		} else {
			noPreference.setSelected(true);
		}
	}
	
	@FXML
	private void hasPreference() {
		noPreference.setSelected(false);
	}

	@Override
	public void updateDataRepository(CreateProfileData dataRepository) {
		dataRepository.setGender(genderCombox.getValue());
		dataRepository.setMinAge(minAgeCombox.getValue());
		dataRepository.setMaxAge(maxAgeCombox.getValue());
		dataRepository.setMinHeight(minHeightCombox.getValue());
		dataRepository.setMaxHeight(maxHeightCombox.getValue());
		ArrayList<String> bodyTypesString = new ArrayList<String>();
		convertBodyTypes(bodyTypesString, bodyTypes);
		dataRepository.setBodyType(bodyTypesString);
	}
	
	private void convertBodyTypes(ArrayList<String> destination, ArrayList<CheckBox> source) {
		for(CheckBox bodyType : source) {
			if(bodyType.isSelected()) {
				destination.add(bodyType.getText());
			}
		}
	}

	@Override
	public void initSavedState(CreateProfileData dataRepository) {
		genderCombox.setValue(dataRepository.getGender());
		minAgeCombox.setValue(dataRepository.getMinAge());
		maxAgeCombox.setValue(dataRepository.getMaxAge());
		minHeightCombox.setValue(dataRepository.getMinHeight());
		maxHeightCombox.setValue(dataRepository.getMaxHeight());
		setValueBodyTypes(dataRepository.getBodyType());
	}
	
	private void setValueBodyTypes(ArrayList<String> bodyTypesString) {
		for(CheckBox bodyType : bodyTypes) {
			for(String bodyTypeString : bodyTypesString) {
				if(bodyType.getText().equals(bodyTypeString)) {
					bodyType.setSelected(true);
					noPreference.setSelected(false);
					bodyTypesString.remove(bodyTypeString);
					break;
				}
			}
		}
	}
}
