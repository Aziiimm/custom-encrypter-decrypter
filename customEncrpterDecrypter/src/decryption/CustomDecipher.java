package decryption;

import java.util.HashMap;
import java.util.Map;

import encryption.CustomCipher;

public class CustomDecipher {

	public static Map<Character, Character> subTable(String shift) {
		Map<Character, Character> subTable = new HashMap<>();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ,./;'[]-=<>?:{}!@#$%^&*()|";

		for (int i = 0; i < alphabet.length(); i++) {
			subTable.put(shift.charAt(i), alphabet.charAt(i));
		}

		return subTable;
	}

	public static StringBuilder decryptContent(String cipherText, String key) {
		StringBuilder decryptedText = new StringBuilder();

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
