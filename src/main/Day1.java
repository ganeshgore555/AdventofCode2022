package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day1Input.txt")));
			String line = null;
			long sum = 0;
			long max = 0;
			long max1 = 0;
			long max2 = 0;
			while((line = br.readLine()) != null){
				if(line.trim().length() == 0) {
					if(sum > max) {
						max2 = max1;
						max1 = max;
						max = sum;
					}else if(sum > max1) {
						max2 = max1;
						max1 = sum;
					}else if(sum > max2) {
						max2 = sum;
					}
					sum = 0;
				}else {
					sum = sum + Long.parseLong(line);
				}
			}
			if(sum > max) {
				max = sum;
			}
			System.out.println("1st max " + max);
			System.out.println("2nd max " + max1);
			System.out.println("3rd max " + max2);
			System.err.println(max + max1 + max2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
