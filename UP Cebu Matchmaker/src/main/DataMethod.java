package main;

import java.util.ArrayList;

public class DataMethod {
	public static void addAges(ArrayList<String> ageList) {
		int age = 13;
		for(; age < 60; age++) {
			ageList.add(String.valueOf(age));
		}
	}
	
	public static void addHeights(ArrayList<String> heightList) {
		int height = 100;
		for(; height < 221; height++) {
			heightList.add(String.valueOf(height));
		}
	}
}
