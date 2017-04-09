package com.practice.trie;

class Word {
	Word references[];
	int length;

	public Word() {
		length = 0;
		references = new Word[26];
	}
}

public class WordFind {

	public void findWords() {

	}

	public static void main(String[] args) {
		Word w = new Word();
		String inputArray[] = { "EASYTOFINDEAGSRVHOTCJYG",
				"FLVENKDHCESOXXXXFAGJKEO", "YHEDYNAIRQGIZECGXQLKDBI",
				"DEIJFKABAQSIHSNDLOMYJIN", "CKXINIMMNGRNSNRGIWQLWOG",
				"VOFQDROQGCWDKOUYRAFUCDO", "PFLXWTYKOITSURQJGEGSPGG" };
		addToTrie(w, inputArray);
		printWords("", w);
	}

	private static void printWords(String word, Word w) {
		if (w == null) {
			return;
		}

		for (int i = 0; i < 26; i++) {
			word = word + (char) ('A' + i);
			printWords(word, w.references[i]);
			word = word.substring(0, word.length() - 1);
		}

		if (w.length == 1) {
			System.out.println(word);
		}
	}

	private static void addToTrie(Word w, String[] inputArray) {
		addLtoR(w, inputArray);
		addTtoD(w, inputArray);
		addDiagonal(w, inputArray);
	}

	private static void addDiagonal(Word w, String[] inputArray) {
		for (int i = 0; i < inputArray.length; i++)
			for (int j = 0; j < inputArray[i].length(); j++) {
				addCharDiag(w, inputArray, i, j);
			}
		for (int j = 0; j < inputArray[0].length(); j++)
			for (int i = 1; i < inputArray.length; i++) {
				addCharDiag(w, inputArray, i, 0);
			}
	}

	private static void addCharDiag(Word w, String[] inputArray, int i, int j) {
		if (i == inputArray.length || j == inputArray[0].length()) {
			w.length = 1;
			return;
		}
		int index = inputArray[i].charAt(j) - 65;
		if (w.references[index] == null)
			w.references[index] = new Word();

		addCharDiag(w.references[index], inputArray, i + 1, j + 1);
	}

	private static void addTtoD(Word w, String[] inputArray) {
		for (int j = 0; j < inputArray[0].length(); j++) {
			addChar(w, inputArray, 0, j);
		}
	}

	private static void addChar(Word w, String[] inputArray, int i, int j) {
		if (i == inputArray.length) {
			w.length = 1;
			return;
		}
		int index = inputArray[i].charAt(j) - 65;
		if (w.references[index] == null)
			w.references[index] = new Word();

		addChar(w.references[index], inputArray, i + 1, j);
	}

	private static void addLtoR(Word w, String[] inputArray) {
		for (int i = 0; i < inputArray.length; i++) {
			addWord(w, inputArray[i]);
		}
	}

	private static void addWord(Word w, String word) {
		if (word.isEmpty()) {
			w.length = 1;
			return;
		}
		char k = word.charAt(0);
		word = word.substring(1, word.length());
		int index = k - 65;
		if (w.references[index] == null)
			w.references[index] = new Word();

		addWord(w.references[index], word);
	}
}
