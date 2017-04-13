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

public class FirstPageController implements Initializable {

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
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		genderCombox.getItems().addAll("Man", "Woman");
		ArrayList ageList = new ArrayList<String>();
		ArrayList heightList = new ArrayList<String>();
		addAges(ageList);
		addHeights(heightList);
		minAgeCombox.getItems().addAll(ageList);
		maxAgeCombox.getItems().addAll(ageList);
		minHeightCombox.getItems().addAll(heightList);
		maxHeightCombox.getItems().addAll(heightList);
	}
	
	private void addAges(ArrayList<String> ageList) {
		int age = 13;
		for(; age < 60; age++) {
			ageList.add(String.valueOf(age));
		}
	}
	
	public void addHeights(ArrayList<String> heightList) {
		int height = 100;
		for(; height < 221; height++) {
			heightList.add(String.valueOf(height));
		}
	}
}
