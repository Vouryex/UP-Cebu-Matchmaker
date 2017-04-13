package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.UserData;
import model.SignUpModel;

public class SignUpController implements Initializable {
	public SignUpModel signUpModel = new SignUpModel();
	@FXML private TextField firstNameField;
	@FXML private TextField surnameField;
	@FXML private RadioButton maleRBtn;
	@FXML private RadioButton femaleRBtn;
	@FXML private ToggleGroup gender;
	@FXML private DatePicker birthdatePicker;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private PasswordField passwordField1;
	@FXML private Label errorLbl;
	@FXML private Label errorLbl1;
	@FXML private Button signUpBtn;
	
	@FXML private ToggleGroup drinker;
	@FXML private ComboBox<String> ppCombox;
	@FXML private ComboBox<String> heightCombox;
	@FXML private ComboBox<String> btCombox;
	@FXML private RadioButton yesRBtn;
	@FXML private RadioButton noRBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList heightList = new ArrayList<String>();
		addHeights(heightList);
		ppCombox.getItems().addAll("UPS", "NKE", "None");
		heightCombox.getItems().addAll(heightList);  // not final
		btCombox.getItems().addAll("Skinny", "Average", "Stocky");
		maleRBtn.setToggleGroup(gender);
		femaleRBtn.setToggleGroup(gender);
		yesRBtn.setToggleGroup(drinker);
		noRBtn.setToggleGroup(drinker);
	}
	public void signUp(ActionEvent event) throws IOException {
		try {
			String pp = ppCombox.getValue();
			String height = heightCombox.getValue();
			String bodyType = btCombox.getValue();
			UserData userData = new UserData();
			String date = birthdatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			System.out.println(firstNameField.getText());
			System.out.println(surnameField.getText());
			System.out.println(date);
			System.out.println(usernameField.getText());
			System.out.println(passwordField.getText());
			
			userData.setFirstname(firstNameField.getText());
			userData.setSurname(surnameField.getText());
			if (maleRBtn.isSelected()) {
				userData.setGender(maleRBtn.getText());
			} else {
				userData.setGender(femaleRBtn.getText());
			}
			userData.setBirthdate(date);
			userData.setUsername(usernameField.getText());
			userData.setPassword(passwordField.getText());
			userData.setPoliticalParty(pp);
			userData.setHeight(height);
			userData.setBodyType(bodyType);
			if (yesRBtn.isSelected()) {
				userData.setDrinker(yesRBtn.getText());
			} else {
				userData.setDrinker(noRBtn.getText());
			}
			
			if (passwordField.getText().equals(passwordField1.getText())) {
				errorLbl1.setText("");
				if (signUpModel.isDataInserted(userData)) {
					errorLbl.setStyle("-fx-text-fill: black");
					errorLbl.setText("Inserted.");
				} else {
					errorLbl.setStyle("-fx-text-fill: #eb0404");
					errorLbl.setText("Username already exists.");
				}
			} else {
				errorLbl1.setStyle("-fx-text-fill: #eb0404");
				errorLbl1.setText("Passwords do not match.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorLbl.setText("Username already exists.");
			e.printStackTrace();
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/profile/CreateProfile.fxml"));
		Stage stage = (Stage) signUpBtn.getScene().getWindow();
		stage.setTitle("Create Your Profile");
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/pastel.css");
		stage.setScene(scene);
		stage.show();
	}
	
	public void addHeights(ArrayList<String> heightList) {
		int height = 100;
		for(; height < 221; height++) {
			heightList.add(String.valueOf(height));
		}
	}
}
