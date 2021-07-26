package com.exl.technical.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleMapsHelpers {
	public void addResultListToFile(List<String> results) throws IOException {
		List<List<String>> splitResults = new ArrayList<List<String>>();
		for (String result : results) {
			splitResults.add(Arrays.asList(result.split("\n")));
		}
		File fileToBeWritten = new File("Details.txt");
		if (fileToBeWritten.createNewFile()) {
			System.out.println(
					"File created: " + fileToBeWritten.getName() + " at: " + fileToBeWritten.getAbsolutePath());
		}

		FileWriter fileWriter = new FileWriter(fileToBeWritten);
		for (List<String> splitResult : splitResults) {
			fileWriter.write("Route Title: " + splitResult.get(2));
			fileWriter.write("\nDistance in Miles: " + splitResult.get(1));
			fileWriter.write("\nTravel Time: " + splitResult.get(0));
			fileWriter.write("\n============================================\n");
		}
		fileWriter.close();
	}
}
