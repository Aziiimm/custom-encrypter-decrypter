package encryption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class CustomCipher {

	public static Map<String, String> keysTable = new HashMap<String, String>();

	public static String randomKey() {
		int length = 5;
		Random random = new Random();
		StringBuilder generatedString = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			char randomChar = (char) ('a' + random.nextInt(26)); // Generate a random lowercase letter
			generatedString.append(randomChar);
		}
		return generatedString.toString();
	}

	public static String createRandomShift() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ,./;'[]-=<>?:{}!@#$%^&*()|";
		StringBuilder shift = new StringBuilder(alphabet.length());

		ArrayList<Character> chars = new ArrayList<>();

		for (int i = 0; i < alphabet.length(); i++) {
			chars.add(alphabet.charAt(i));
		}

		// Method to randomly permute the array of possible chars
		Collections.shuffle(chars);

		for (Character c : chars) {
			shift.append(c);
		}

		return shift.toString();
	}

	public static Map<Character, Character> subTable(String shift) {
		Map<Character, Character> subTable = new HashMap<>();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ,./;'[]-=<>?:{}!@#$%^&*()|";

		for (int i = 0; i < alphabet.length(); i++) {
			subTable.put(alphabet.charAt(i), shift.charAt(i));
		}
		return subTable;
	}

	// Encrypt text using substitution cipher
	public static String encryptContent(String fileContent, String key) {

		String shift = "";
		if (key == null || key.isEmpty()) {
			key = randomKey();
			JOptionPane.showMessageDialog(null, "Using Program Generated Key: " + key);
			shift = createRandomShift();
			keysTable.put(key, shift);
		} else if (!keysTable.containsKey(key)) {
			JOptionPane.showMessageDialog(null, "Using Key: " + key);
			shift = createRandomShift();
			keysTable.put(key, shift);
		} else if (keysTable.containsKey(key)) {
			JOptionPane.showMessageDialog(null, "Using Key: " + key);
			shift = keysTable.get(key);
		}

		Map<Character, Character> subTable = subTable(shift);

		StringBuilder cipheredText = new StringBuilder();

		for (char c : fileContent.toCharArray()) {
			if (subTable.containsKey(c)) {
				cipheredText.append(subTable.get(c));
			} else {
				cipheredText.append(c);
			}
		}

		return cipheredText.toString();
	}
}
