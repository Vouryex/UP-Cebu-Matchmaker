package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LoginModel;

public class LoginController implements Initializable {
	public LoginModel loginModel = new LoginModel();
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private Label errorLbl;
	@FXML private Hyperlink signUpLink;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void login(ActionEvent event) {
		try {
			if (loginModel.isLogin(usernameField.getText(), passwordField.getText())) {
				errorLbl.setStyle("-fx-text-fill: black");
				errorLbl.setText("Match.");   // testing purposes
				ArrayList<String> info = new ArrayList<String>();
				info = loginModel.getInfo(usernameField.getText());
				FXMLLoader loader = new FXMLLoader();
				Parent root = loader.load(getClass().getResource("/view/ProfilePage.fxml").openStream());
				ProfilePageController profilePageController = (ProfilePageController) loader.getController();
				//profilePageController.setUser(info);
				int id = loginModel.getID(usernameField.getText());
				profilePageController.displayData(id);
				Stage stage = (Stage) signUpLink.getScene().getWindow();
				stage.setTitle("Profile");
				Scene scene = new Scene(root);
				scene.getStylesheets().add("/theme/pastel.css");
				stage.setScene(scene);
				stage.show();
			} else {
				errorLbl.setStyle("-fx-text-fill: #eb0404");
				errorLbl.setText("Invalid credentials.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorLbl.setText("Invalid credentials");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void signUp(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
		Stage stage = (Stage) signUpLink.getScene().getWindow();
		stage.setTitle("Sign Up");
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/pastel.css");
		stage.setScene(scene);
		stage.show();
	}
}
