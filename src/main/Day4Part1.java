package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Day4Part1 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day4Input.txt")));
			String line = null;
			int total = 0;
			while((line = br.readLine()) != null){
				String[]pair = line.split(",");
				String [] pair1 = pair[0].split("-");
				String [] pair2 = pair[1].split("-");
				int pair1Lower = Integer.parseInt(pair1[0]);
				int pair1Upper = Integer.parseInt(pair1[1]);
				int pair2Lower = Integer.parseInt(pair2[0]);
				int pair2Upper = Integer.parseInt(pair2[1]);

				if((pair1Upper >= pair2Lower && pair1Upper <= pair2Upper) || (pair2Upper >= pair1Lower && pair2Upper <= pair1Upper)) {
					System.out.println(pair1Lower + "-" + pair1Upper + ";" + pair2Lower + "-" + pair2Upper);
					total++;
				}
			}
			System.out.println(total);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
