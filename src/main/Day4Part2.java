package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Day4Part2 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day4Input.txt")));
			String line = null;
			int total = 0;
			while((line = br.readLine()) != null){
				int length = line.length();
				String str1 = line.substring(0, length/2);
				String str2 = line.substring(length/2, length);
				for(char c1 : str1.toCharArray()) {
					if(str2.indexOf(c1) != -1) {
						int charVal = c1;
						if(charVal >=97)
							total = total + (charVal-96);
						else
							total = total + (charVal-38);
						
						break;
					}
				}
			}
			System.out.println(total);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
