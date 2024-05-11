package decryption;

import java.util.HashMap;
import java.util.Map;

import encryption.CustomCipher;

public class CustomDecipher {
	
	// Maps each letter of shuffled alphabet to normal alphabet
	public static Map<Character, Character> subTable(String shift) {
		Map<Character, Character> subTable = new HashMap<>();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ,./;'[]-=<>?:{}!@#$%^&*()|";

		for (int i = 0; i < alphabet.length(); i++) {
			subTable.put(shift.charAt(i), alphabet.charAt(i));
		}

		return subTable;
	}

	// Content of file is passed as parameter and decrypted using key
	public static StringBuilder decryptContent(String cipherText, String key) {
		StringBuilder decryptedText = new StringBuilder();

		// Uses the corresponding shuffled alphabet
		String deshift = CustomCipher.keysTable.get(key);
		Map<Character, Character> subTable = subTable(deshift);

		for (Character c : cipherText.toCharArray()) {
			if (subTable.containsValue(c)) {
				decryptedText.append(subTable.get(c));
			} else {
				decryptedText.append(c);
			}
		}

		return decryptedText;
	}

}
