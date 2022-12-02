package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day2Input.txt")));
			String line = null;
			while((line = br.readLine()) != null){
				if(line.trim().length() == 0) {
				}else {
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
