package controller.createprofile;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import main.CreateProfileData;
import main.Match;
import model.CreateProfileModel;

public class CreateProfileController implements Initializable {

	public CreateProfileModel createProfileModel = new CreateProfileModel(); 
	@FXML private Pane questionArea;
	@FXML private ImageView heart;
	@FXML private ImageView ball;
	@FXML private ImageView camera;
	@FXML private ImageView backArrow;
	@FXML private ImageView nextArrow;
	private int currPageNum;
	private PageController pageController;
	private CreateProfileData dataRepository = new CreateProfileData();
	private int id;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		Parent firstPage = null;
		FXMLLoader loader = new FXMLLoader();
		currPageNum = PageManager.minPage();
		try {
			firstPage = loader.load(getClass().getResource(PageManager.getPage(currPageNum)).openStream());
			pageController = (PageController) loader.getController();
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
			pageController.updateDataRepository(dataRepository);
			
			currPageNum--;
			questionArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			Parent page = loader.load(getClass().getResource(PageManager.getPage(currPageNum)).openStream());
			pageController = (PageController) loader.getController();
			pageController.initSavedState(dataRepository);
			questionArea.getChildren().add(page);
			nextArrow.setDisable(false);
			
			if(currPageNum == PageManager.minPage()) {
				backArrow.setDisable(true);
			}
		}

	}
	
	public void next() throws IOException, SQLException {
		pageController.updateDataRepository(dataRepository);
		
		if(currPageNum == PageManager.maxPage()) {
			try {
				createProfileModel.insertData(dataRepository, id);
				Match.update(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			profilePageScene();
		} else {			
			currPageNum++;
			questionArea.getChildren().clear();
			FXMLLoader loader = new FXMLLoader();
			Parent page = loader.load(getClass().getResource(PageManager.getPage(currPageNum)).openStream());
			pageController = (PageController) loader.getController();
			pageController.initSavedState(dataRepository);
			questionArea.getChildren().add(page);
			backArrow.setDisable(false);
		}

	}
	
	private void profilePageScene() throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/view/ProfilePage.fxml").openStream());
		ProfilePageController profilePageController = (ProfilePageController) loader.getController();
		profilePageController.setId(id);
		profilePageController.displayData(id);
		Stage stage = (Stage) questionArea.getScene().getWindow();
		stage.setTitle("Profile");
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/theme/pastel.css");
		stage.setScene(scene);
		stage.show();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void deleteUser(int id) throws SQLException {
		createProfileModel.deleteMovies(id);
		createProfileModel.deleteMusic(id);
		createProfileModel.deletePets(id);
		createProfileModel.deleteHobbies(id);
		createProfileModel.deleteSports(id);
		createProfileModel.deleteBody(id);
		createProfileModel.deleteHeight(id);
		createProfileModel.deleteAge(id);
		createProfileModel.deleteGender(id);
	}
}
