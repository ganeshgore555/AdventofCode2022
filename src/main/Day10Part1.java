package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Day10Part1 {
	
	static Map<Integer, Integer> cycles = Map.of(20, 20, 60, 60, 100, 100, 140, 140, 180, 180, 220, 220);   
	public static void main(String[] args) {
		try {
			int X = 1; // Register
			String line = "";
			int cycle = 0;
			int totalCycle = 0;
			int total = 0;
			int[][]screen = new int[6][40];
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day10.txt")));
			int row = 0;
			int col = 0;
			while((line = br.readLine()) != null){
				String[] instruction = line.split(" ");
				if(instruction[0].equals("noop")) {
					cycle = 1;
				}else {
					cycle = 2;
				}
				while(cycle > 0) {					
					cycle--;
					totalCycle++;
					if(cycles.get(totalCycle) != null) {
						total = total + (totalCycle*X);
					}
					row = (totalCycle/40);
					col = (totalCycle%40) == 0 ? 39 : (totalCycle%40)-1;
					if(col <= X+1 && col >= X-1) {
						screen[row][col] = 1;
					}
				}
				if(instruction[0].equals("addx")) {
					X = X + Integer.parseInt(instruction[1]);
				}
			}
			System.out.println("Total Signal Strength : " + total);
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 40; j++) {
					System.out.print(screen[i][j] == 0 ? " " : "|");
				}
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
