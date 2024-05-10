package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Toolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import filehandling.CustomFileReader;
import filehandling.CustomFileWriter;
import encryption.CustomCipher;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import decryption.CustomDecipher;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Launch the Application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("Button.focus", new Color(0, 0, 0, 0));
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Store integer dimensions of the window
	private int windowWidth;
	private int windowHeight;
	File selectedFile = null;
	String fileContent;
	String key = null;

	// Create the frame.
	public MainWindow() {

		contentPane = new JPanel();
		setBounds(100, 100, 884, 596);
		contentPane.setBackground(new Color(30, 53, 47));
		setTitle("Custom Encrypter/Decrypter");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/utils/favicon.png")));
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Window hero
		JLabel title = new JLabel("Custom Encrypter & Decrypter!");
		title.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		title.setBounds(228, 125, 412, 40);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Calibri", Font.BOLD, 32));
		title.setHorizontalAlignment(SwingConstants.LEFT);

		// File Status
		JLabel fileStatus = new JLabel("No File Selected");
		fileStatus.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		fileStatus.setBounds(599, 223, 104, 20);
		fileStatus.setForeground(new Color(255, 255, 255));
		fileStatus.setFont(new Font("Calibri", Font.PLAIN, 16));
		fileStatus.setHorizontalAlignment(SwingConstants.LEFT);

		// Select File button
		JButton uploadFile = new JButton("Select File");
		uploadFile.setBounds(279, 214, 310, 32);
		uploadFile.setForeground(Color.BLACK);
		uploadFile.setBackground(new Color(240, 240, 240));
		uploadFile.setBorderPainted(false);
		uploadFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uploadFile.setFont(new Font("Cambria", Font.BOLD, 12));
		// Allows the user to upload a file on click
		uploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					try {
						// Read the inputed file
						if (selectedFile.getName().endsWith(".txt")) {
							// Uses readTextFromFile function from the CustomFileReader class
							fileContent = CustomFileReader.readTextFromFile(selectedFile.getAbsolutePath());
							fileStatus.setText("File Selected: " + selectedFile.getName());
						} else {
							// Otherwise throw an exception telling user only .txt files
							selectedFile = null;
							throw new IOException("Please Select a .txt File");
						}
					} catch (IOException ex) {
						ex.printStackTrace();
						// Change file status in GUI and display an alert
						fileStatus.setText(ex.getMessage());
						JOptionPane.showMessageDialog(null, "Error reading file: " + selectedFile.getName());
					}
				}
			}
		});

		// Encrypt button
		JButton encryptButton = new JButton("Encrypt");
		encryptButton.setBounds(279, 257, 125, 32);
		encryptButton.setForeground(Color.BLACK);
		encryptButton.setBackground(new Color(240, 240, 240));
		encryptButton.setBorderPainted(false);
		encryptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		encryptButton.setFont(new Font("Cambria", Font.BOLD, 12));
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Prevents User from Encrypting without selecting a file first
				try {
					if (selectedFile == null) {
						throw new FileNotFoundException("Please Select a File First.");
					} else {
						String encryptedContent = CustomCipher.encryptContent(fileContent, key).toString();
						CustomFileWriter.writeTextToFile(selectedFile, encryptedContent);
					}
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Decrypt button
		JButton decryptButton = new JButton("Decrypt");
		decryptButton.setBounds(464, 257, 125, 32);
		decryptButton.setForeground(Color.BLACK);
		decryptButton.setBackground(new Color(240, 240, 240));
		decryptButton.setBorderPainted(false);
		decryptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		decryptButton.setFont(new Font("Cambria", Font.BOLD, 12));
		decryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Prevents User from Decrypting without selecting a file first
				try {
					if (selectedFile == null) {
						throw new FileNotFoundException("Please Select a File First.");
					} else {
						String decryptedContent = CustomDecipher.decryptContent(fileContent, key).toString();
						CustomFileWriter.overwriteFile(selectedFile, decryptedContent);
					}
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Credits
		JLabel credits = new JLabel("© Azim Rahat | Ivan Chen | Ibrahim Rahat");
		credits.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		credits.setBounds(646, 531, 212, 15);
		credits.setForeground(Color.WHITE);
		credits.setFont(new Font("Calibri", Font.BOLD, 12));
		credits.setHorizontalAlignment(SwingConstants.TRAILING);
		credits.setVerticalAlignment(SwingConstants.BOTTOM);

		// Key Field
		JTextPane keyField = new JTextPane();
		keyField.setBackground(new Color(240, 240, 240));
		keyField.setBounds(279, 300, 309, 20);

		JLabel keyLabel = new JLabel("Type Key Here");
		keyLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		keyLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		keyLabel.setForeground(new Color(255, 255, 255));
		keyLabel.setBounds(10, 300, 259, 20);

		JButton submitKey = new JButton("Submit Key");
		submitKey.setBackground(new Color(240, 240, 240));
		submitKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submitKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key = keyField.getText().strip();
				JOptionPane.showMessageDialog(null, "Your Custom Key is: " + key);
				keyLabel.setText("Key: " + key);
			}
		});
		submitKey.setBorderPainted(false);
		submitKey.setFont(new Font("Cambria", Font.BOLD, 12));
		submitKey.setBounds(599, 300, 104, 20);

		contentPane.add(keyLabel);
		contentPane.setLayout(null);
		contentPane.add(title);
		contentPane.add(uploadFile);
		contentPane.add(fileStatus);
		contentPane.add(encryptButton);
		contentPane.add(decryptButton);
		contentPane.add(credits);
		contentPane.add(keyField);
		contentPane.add(submitKey);

		// Makes window dynamic
		this.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				// Gets dimensions of window upon user resize
				windowWidth = getWidth();
				windowHeight = getHeight();

				// Methods to update window content positions based off resize
				// Positions are relative to window center and 'Select File' button
				uploadFile.setBounds(windowWidth / 2 - 134, windowHeight / 2 - 16, 269, 32);
				title.setBounds((windowWidth / 2) - 206, uploadFile.getY() - 100, 677, 40);
				fileStatus.setBounds(uploadFile.getX() + 300, uploadFile.getY(), 269, 32);
				encryptButton.setBounds(uploadFile.getX(), uploadFile.getY() + 40, 125, 32);
				decryptButton.setBounds(uploadFile.getX() + 144, uploadFile.getY() + 40, 125, 32);
				credits.setBounds(windowWidth - 250, windowHeight - 60, 212, 15);
				keyField.setBounds(encryptButton.getX(), encryptButton.getY() + 40, 270, 20);
				keyLabel.setBounds(keyField.getX() - 115, keyField.getY() + 2, 93, 20);
				submitKey.setBounds(keyField.getX() + 300, keyField.getY(), 104, 20);
			}
		});

	}
}