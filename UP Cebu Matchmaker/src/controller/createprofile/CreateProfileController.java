package controller.createprofile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ProfilePageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateProfileController implements Initializable {

	@FXML private Pane questionArea;
	@FXML private ImageView heart;
	@FXML private ImageView ball;
	@FXML private ImageView camera;
	@FXML private ImageView backArrow;
	@FXML private ImageView nextArrow;
	private int currPageNum;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		Pane firstPage = null;
		currPageNum = PageManager.minPage();
		try {
			firstPage = FXMLLoader.load(getClass().getResource(PageManager.getPage(currPageNum)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		questionArea.getChildren().add(firstPage);
	}
	
	public void back() throws IOException {
		if(currPageNum == PageManager.minPage()) {
			// code will not reach here.
		} else {
			currPageNum--;
			questionArea.getChildren().clear();
			Pane page = FXMLLoader.load(getClass().getResource(PageManager.getPage(currPageNum)));
			questionArea.getChildren().add(page);
			nextArrow.setDisable(false);
			
			if(currPageNum == PageManager.minPage()) {
				backArrow.setDisable(true);
			}
		}

	}
	
	public void next() throws IOException {
		if(currPageNum == PageManager.maxPage()) {
			profilePageScene();
		} else {
			currPageNum++;
			questionArea.getChildren().clear();
			Pane page = FXMLLoader.load(getClass().getResource(PageManager.getPage(currPageNum)));
			questionArea.getChildren().add(page);
			backArrow.setDisable(false);
		}

	}
	
	private void profilePageScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/view/ProfilePage.fxml").openStream());
		ProfilePageController profilePageController = (ProfilePageController) loader.getController();
		//profilePageController.setUser(usernameField.getText());
		Stage stage = (Stage) questionArea.getScene().getWindow();
		stage.setTitle("Profile");
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/pastel.css");
		stage.setScene(scene);
		stage.show();
	}
}
