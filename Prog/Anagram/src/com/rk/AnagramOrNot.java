package com.rk;

import java.util.Arrays;

public class AnagramOrNot {

	public static void main(String[] args) {

		boolean isAnagram = true;

		String str1 = "Keep";
		String str2 = "peek";

		String s1 = str1.replaceAll("\\s", "").toLowerCase();
		String s2 = str2.replaceAll("\\s", "").toLowerCase();

		if (s1.length() == s2.length()) {

			char[] ch1 = s1.toCharArray();
			char[] ch2 = s2.toCharArray();

			Arrays.sort(ch1);
			Arrays.sort(ch2);

			for (int i = 0; i <= ch1.length - 1; i++) {

				for (int j = 0; j <= ch2.length - 1; j++) {
					if (ch1[i] == ch2[j]) {
						isAnagram = true;
						break;
					} else {
						isAnagram = false;

					}

				}
				if (isAnagram == false)
					break;
			}

			if (isAnagram == false) {
				System.out.println("Not an Anagram");
			} else {
				System.out.println("Anagram");
			}
		} else {
			System.out.println("Not an Angram..");
		}
	}

}
