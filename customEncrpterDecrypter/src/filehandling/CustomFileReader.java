package filehandling;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CustomFileReader {

	public static String readTextFromFile(String filePath) {

		StringBuilder content = new StringBuilder();

		try (Scanner inFS = new Scanner(new FileReader(filePath))) {
			while (inFS.hasNextLine()) {
				content.append(inFS.nextLine()).append("\n");
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error Reading File");
//			ex.printStackTrace();
		} catch (SecurityException ex) {
			JOptionPane.showMessageDialog(null, "Error Reading File");
//			ex.printStackTrace();
		}

		// Return content of the file stored as a string
		return content.toString();
	}
}
