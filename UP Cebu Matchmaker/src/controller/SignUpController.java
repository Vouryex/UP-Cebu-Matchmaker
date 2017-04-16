package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.createprofile.CreateProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.DataMethod;
import main.UserData;
import model.SignUpModel;

public class SignUpController implements Initializable {
	public SignUpModel signUpModel = new SignUpModel(this);
	@FXML private TextField firstNameField;
	@FXML private TextField surnameField;
	@FXML private RadioButton maleRBtn;
	@FXML private RadioButton femaleRBtn;
	@FXML private ToggleGroup gender;
	@FXML private ComboBox<String> ageCombox;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private PasswordField passwordField1;
	@FXML private Label errorLbl;
	@FXML private Label errorLbl1;
	@FXML private Label lastErrorLbl;
	@FXML private Button signUpBtn;
	
	@FXML private ToggleGroup drinker;
	@FXML private ComboBox<String> ppCombox;
	@FXML private ComboBox<String> heightCombox;
	@FXML private ComboBox<String> btCombox;
	@FXML private RadioButton yesRBtn;
	@FXML private RadioButton noRBtn;
	
	private int id;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList heightList = new ArrayList<String>();
		DataMethod.addHeights(heightList);
		ArrayList ageList = new ArrayList<String>();
		DataMethod.addAges(ageList);
		ageCombox.getItems().addAll(ageList);
		ppCombox.getItems().addAll("UPS", "NKE", "None");
		heightCombox.getItems().addAll(heightList);  // not final
		btCombox.getItems().addAll("Skinny", "Average", "Stocky", "Heavyset", "Full-figured", "Athletic");
		maleRBtn.setToggleGroup(gender);
		femaleRBtn.setToggleGroup(gender);
		yesRBtn.setToggleGroup(drinker);
		noRBtn.setToggleGroup(drinker);
	}
	public void signUp(ActionEvent event) throws IOException {
		String pp = "";
		String height = "";
		String bodyType = "";
		try {
			pp = ppCombox.getValue();
			height = heightCombox.getValue();
			bodyType = btCombox.getValue();
			UserData userData = new UserData();
			System.out.println(firstNameField.getText());
			System.out.println(surnameField.getText());
			System.out.println(ageCombox.getValue());
			System.out.println(usernameField.getText());
			System.out.println(passwordField.getText());
			
			userData.setFirstname(firstNameField.getText());
			userData.setSurname(surnameField.getText());
			if (maleRBtn.isSelected()) {
				userData.setGender(maleRBtn.getText());
			} else {
				userData.setGender(femaleRBtn.getText());
			}
			userData.setAge(ageCombox.getValue());
			userData.setUsername(usernameField.getText().toLowerCase());
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
					errorLbl.setText("Invalid Credentials.");
				}
			} else {
				errorLbl1.setStyle("-fx-text-fill: #eb0404");
				errorLbl1.setText("Passwords do not match.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lastErrorLbl.setStyle("-fx-text-fill: #eb0404");
			lastErrorLbl.setText("Please fill up all the fields.");
			e.printStackTrace();
		}
		if (!(firstNameField.getText().equals("")) && !(surnameField.getText().equals("")) && !(pp.equals(null)) && !(bodyType.equals(null)) && !(height.equals(null)) && !(ageCombox.getValue().equals(null))
				&& !(usernameField.getText().equals("")) && !(passwordField.getText().equals(""))) {
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(getClass().getResource("/view/profile/CreateProfile.fxml").openStream());
			CreateProfileController createProfileController = (CreateProfileController) loader.getController();
			createProfileController.setId(id);
			Stage stage = (Stage) signUpBtn.getScene().getWindow();
			stage.setTitle("Create Your Profile");
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/theme/pastel.css");
			stage.setScene(scene);
			stage.show();
		} else {
			lastErrorLbl.setStyle("-fx-text-fill: #eb0404");
			lastErrorLbl.setText("Please fill up all the fields.");
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
