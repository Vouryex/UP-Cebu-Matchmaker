package controller.createprofile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import main.CreateProfileData;

public class FourthPageController extends PageController implements Initializable {

	@FXML private ImageView profilePicture;
	@FXML private Button upload;
	private File selectedFile;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void uploadPic() throws IOException {
		FileChooser fileChooser = new FileChooser();
		selectedFile = fileChooser.showOpenDialog(null);
		BufferedImage bufferedProfile = ImageIO.read(selectedFile);
		Image profile = SwingFXUtils.toFXImage(bufferedProfile, null);
		profilePicture.setImage(profile);
	}

	@Override
	public void updateDataRepository(CreateProfileData dataRepository) {
		dataRepository.setProfilePicture(selectedFile);
	}

	@Override
	public void initSavedState(CreateProfileData dataRepository) {
		try {
			selectedFile = dataRepository.getProfilePicture();
			BufferedImage bufferedProfile = ImageIO.read(selectedFile);
			Image profile = SwingFXUtils.toFXImage(bufferedProfile, null);
			profilePicture.setImage(profile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
