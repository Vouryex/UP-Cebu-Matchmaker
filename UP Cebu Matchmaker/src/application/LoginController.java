package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	public LoginModel loginModel = new LoginModel();
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private Label errorLbl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void Login(ActionEvent event) {
		try {
			if (loginModel.isLogin(usernameField.getText(), passwordField.getText())) {
				
			} else {
				errorLbl.setStyle("-fx-text-fill: #eb0404");
				errorLbl.setText("Invalid credentials");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorLbl.setText("Invalid credentials");
			e.printStackTrace();
		}
	}
}
