package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day3Part2 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day3Input.txt")));
			String line = null;
			int total = 0;
			int lineCount = 0;
			String[] ruckSacks = new String[3];
			while((line = br.readLine()) != null){
				ruckSacks[lineCount] = line;
				lineCount++;
				if(lineCount == 3) {
					lineCount = 0;
				
					for(char c1 : ruckSacks[0].toCharArray()) {
						if(ruckSacks[1].indexOf(c1) != -1 && ruckSacks[2].indexOf(c1) != -1) {
							int charVal = c1;
							if(charVal >=97)
								total = total + (charVal-96);
							else
								total = total + (charVal-38);
							
							break;
						}
					}
				}
			}
			System.out.println(total);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
