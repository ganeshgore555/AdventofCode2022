package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2Part1 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day2Input.txt")));
			String line = null;
			int total = 0;
			while((line = br.readLine()) != null){
				char opp = line.charAt(0);
				char you = line.charAt(2);
				if(opp == 'A') {
					if(you == 'X') {
						total = total + 4;
					}else if( you == 'Y'){
						total = total + 8;
					}else {
						total = total + 3;
					}
				}else if(opp == 'B') {
					if(you == 'X') {
						total = total + 1;
					}else if( you == 'Y'){
						total = total + 5;
					}else {
						total = total + 9;
					}
				}else {
					if(you == 'X') {
						total = total + 7;
					}else if( you == 'Y'){
						total = total + 2;
					}else {
						total = total + 6;
					}
				}
			}
			System.out.println(total);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
