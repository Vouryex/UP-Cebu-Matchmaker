package controller.createprofile;

import main.CreateProfileData;

public abstract class PageController {

	public abstract void updateDataRepository(CreateProfileData dataRepository);
	public abstract void initSavedState(CreateProfileData dataRepository);
}
