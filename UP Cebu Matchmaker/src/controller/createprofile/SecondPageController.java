package controller.createprofile;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;

public class SecondPageController implements Initializable {

	@FXML private CheckBox noAnswerSports;
	@FXML private CheckBox aerobics;
	@FXML private CheckBox basketball;
	@FXML private CheckBox autoracing;
	@FXML private CheckBox billiards;
	@FXML private CheckBox baseball;
	@FXML private CheckBox bowling;
	@FXML private CheckBox cycling;
	@FXML private Hyperlink moreSports;
	
	@FXML private CheckBox noAnswerHobbies;
	@FXML private CheckBox camping;
	@FXML private CheckBox diningOut;
	@FXML private CheckBox coffee;
	@FXML private CheckBox gardening;
	@FXML private CheckBox cooking;
	@FXML private CheckBox movies;
	@FXML private Hyperlink moreHobbies;
	
	@FXML private CheckBox noPreference;
	@FXML private CheckBox cats;
	@FXML private CheckBox dogs;
	@FXML private CheckBox exotic;
	@FXML private CheckBox fish;
	@FXML private CheckBox horses;
	@FXML private CheckBox other;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
