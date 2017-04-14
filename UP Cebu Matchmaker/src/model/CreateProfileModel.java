package model;

import java.sql.Connection;

import main.SqLiteConnection;

public class CreateProfileModel {
	Connection connection;
	public CreateProfileModel() {
		connection = SqLiteConnection.Connector();
		if (connection == null) {
			System.out.println("connection not successful");
			System.exit(1);
		}
	}
	
	
}
