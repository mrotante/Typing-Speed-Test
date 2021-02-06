//Word lists taken from http://www.ashley-bovan.co.uk/words/syllables2.html

package wpmcalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Scanner one;
	private static Scanner two;
	private static Scanner three;
	private static ArrayList<String> one_syllable;
	private static ArrayList<String> two_syllable;
	private static ArrayList<String> three_syllable;
	private static String words;
	
	//generate random word lists from files
	public static String generate(String syllable) {
		words = "";
		switch(syllable) {
		case"one":
			for(int i = 0; i < 30; i++) {
				double next_word = Math.random() * (one_syllable.size() - 1);
				if(words.length() > 0) {
					words = words + " " + one_syllable.get((int) Math.floor(next_word));
				} else {
					words = one_syllable.get((int) Math.floor(next_word));
				}
			}
			break;
			
		case"two":
			for(int i = 0; i < 30; i++) {
				double next_word = Math.random() * (two_syllable.size() - 1);
				if(words.length() > 0) {
					words = words + " " + two_syllable.get((int) Math.floor(next_word));
				} else {
					words = two_syllable.get((int) Math.floor(next_word));
				}
			}
			break;
			
		case"three":
			for(int i = 0; i < 30; i++) {
				double next_word = Math.random() * (three_syllable.size() - 1);
				if(words.length() > 0) {
					words = words + " " + three_syllable.get((int) Math.floor(next_word));
				} else {
					words = three_syllable.get((int) Math.floor(next_word));
				}	
			}
			break;
		}
		return words;
	}

	//load word lists into arraylists
	private static void load() throws FileNotFoundException {
		one = new Scanner(new File("word lists/one syllable words.txt"));
		one_syllable = new ArrayList<String>();
		while(one.hasNextLine())
			one_syllable.add(one.nextLine());
	
		two = new Scanner(new File("word lists/two syllable words.txt"));
		two_syllable = new ArrayList<String>();
		while(two.hasNextLine())
			two_syllable.add(two.nextLine());
		
		three = new Scanner(new File("word lists/three syllable words.txt"));
		three_syllable = new ArrayList<String>();
		while(three.hasNextLine())
			three_syllable.add(three.nextLine());
	}
		
	public static void main(String[] args) throws FileNotFoundException {
		load();
		MainScreen.showGUI();
	}
}