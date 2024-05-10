package filehandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class CustomFileWriter {

	public static void overwriteFile(File selectedFile, String decryptedContent) {
		try {
			File decryptedFile = new File(selectedFile.getAbsolutePath());
			FileWriter outFS = new FileWriter(decryptedFile);
			outFS.write(decryptedContent);
			outFS.close();
			JOptionPane.showMessageDialog(null, "Successfully Decrypted File: " + selectedFile.getName());
		} catch (IOException ex) {
//			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error Writing to File");
		} catch (SecurityException ex) {
//			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error Writing to File");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
//			ex.printStackTrace();
		}
	}

	public static void writeTextToFile(File selectedFile, String encryptedContent) {

		try {
			File newFile = new File(
					selectedFile.getAbsolutePath().substring(0, selectedFile.getAbsolutePath().length() - 4)
							+ "_encrypted.txt");
			if (newFile.createNewFile()) {
				FileWriter outFS = new FileWriter(newFile);
				outFS.write(encryptedContent);
				outFS.close();
				JOptionPane.showMessageDialog(null, "Created New File: " + newFile.getName());
			} else {
				JOptionPane.showMessageDialog(null,
						"Cannot Encrypt. Please Delete Conflicting File\n" + newFile.getName());
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error Writing to File");
		} catch (SecurityException ex) {
			JOptionPane.showMessageDialog(null, "Error Writing to File");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
}
