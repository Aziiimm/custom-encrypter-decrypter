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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;

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
		title.setBounds(228, 165, 412, 40);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Calibri", Font.BOLD, 32));
		title.setHorizontalAlignment(SwingConstants.LEFT);

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
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected File: " + selectedFile.getAbsolutePath());
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
			}
		});
		
		// Credits
		JLabel credits = new JLabel("© Azim Rahat | Ivan Chen | Ibrahim Rahat");
		credits.setBounds(646, 531, 212, 15);
		credits.setForeground(Color.WHITE);
		credits.setFont(new Font("Calibri", Font.BOLD, 12));
		credits.setHorizontalAlignment(SwingConstants.TRAILING);
		credits.setVerticalAlignment(SwingConstants.BOTTOM);

		contentPane.setLayout(null);
		contentPane.add(title);
		contentPane.add(uploadFile);
		contentPane.add(encryptButton);
		contentPane.add(decryptButton);
		contentPane.add(credits);

		// Makes window dynamic
		this.addComponentListener(new ComponentAdapter() {
		
			public void componentResized(ComponentEvent e) {
				// Gets dimensions of window upon user resize
				windowWidth = getWidth();
				windowHeight = getHeight();
				
				// Methods to update window content positions based off resize 				 
				//Positions are relative to window center and 'Select File' button
				title.setBounds((windowWidth / 2) - 206, uploadFile.getY() - 50, 677, 40);
				uploadFile.setBounds(windowWidth / 2 - 134, windowHeight / 2 - 16, 269, 32);
				encryptButton.setBounds(uploadFile.getX(), uploadFile.getY() + 40, 125, 32);
				decryptButton.setBounds(uploadFile.getX() + 144, uploadFile.getY() + 40, 125, 32);
				credits.setBounds(windowWidth - 250, windowHeight - 60, 212, 15);
			}
		});

	}
}