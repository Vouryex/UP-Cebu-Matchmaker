package controller;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

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
	@FXML private PasswordField passwordField1;
	@FXML private Label errorLbl;
	@FXML private Label errorLbl1;
	
	public void signUp(ActionEvent event) {
		try {
			String date = birthdatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			System.out.println(firstNameField.getText());
			System.out.println(surnameField.getText());
			System.out.println(date);
			System.out.println(usernameField.getText());
			System.out.println(passwordField.getText());
			if (passwordField.getText().equals(passwordField1.getText())) {
				if (signUpModel.isDataInserted(firstNameField.getText(), surnameField.getText(), date, usernameField.getText(), passwordField.getText())) {
					errorLbl.setStyle("-fx-text-fill: black");
					errorLbl.setText("Inserted.");
				} else {
					errorLbl.setStyle("-fx-text-fill: #eb0404");
					errorLbl.setText("Invalid credentials.");
				}
			} else {
				errorLbl1.setStyle("-fx-text-fill: #eb0404");
				errorLbl1.setText("Passwords do not match.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorLbl.setText("Invalid credentials");
			e.printStackTrace();
		}
	}
}
