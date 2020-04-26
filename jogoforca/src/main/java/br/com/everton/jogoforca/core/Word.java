package br.com.everton.jogoforca.core;

import java.util.HashSet;
import java.util.Set;

public class Word {

	private static final char SECRET_CHAR = '_';
	private String originalWord;
	private Set<Character> foundChars = new HashSet<>();
	private Set<Character> wordChars = new HashSet<>();

	public Word(String originalWord) {
		this.originalWord = originalWord.toUpperCase();

		char[] chars = this.originalWord.toCharArray();
		for (char c : chars) {
			wordChars.add(c);
		}
	}

	public int size() {
		return originalWord.length();
	}

	public boolean hasChar(char character) {

		character = Character.toUpperCase(character);
		if (originalWord.indexOf(character) > -1) {
			foundChars.add(character);
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		char[] charArray = originalWord.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			char charCorrente = charArray[i];

			if (foundChars.contains(charCorrente)) {
				sb.append(charCorrente);
			} else {
				sb.append(SECRET_CHAR);
			}
			sb.append(" ");
		}

		return sb.toString();
	}

	public boolean discovered() {
		return foundChars.equals(wordChars);
	}

	public String getOriginalWord() {
		return originalWord;
	}

}
