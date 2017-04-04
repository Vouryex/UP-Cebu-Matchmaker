package controller;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.SignUpModel;

public class SignUpController {
	public SignUpModel signUpModel = new SignUpModel();
	@FXML private TextField firstNameField;
	@FXML private TextField surnameField;
	@FXML private DatePicker birthdatePicker;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Label errorLbl;
	
	public void signUp(ActionEvent event) {
		try {
			System.out.println(firstNameField.getText());
			System.out.println(surnameField.getText());
			System.out.println(birthdatePicker.toString());
			System.out.println(usernameField.getText());
			System.out.println(passwordField.getText());
			if (signUpModel.isDataInserted(firstNameField.getText(), surnameField.getText(), birthdatePicker.toString(), usernameField.getText(), passwordField.getText())) {
				errorLbl.setText("Inserted.");
			} else {
				errorLbl.setStyle("-fx-text-fill: #eb0404");
				errorLbl.setText("Invalid credentials.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorLbl.setText("Invalid credentials");
			e.printStackTrace();
		}
	}
}
