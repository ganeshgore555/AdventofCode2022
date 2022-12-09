package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day9Part1 {
	static int row = 1000;
	static int col = 1000;
	static int[][]matrix = new int[row][col];
	static int hRow = 499;
	static int tRow = 499;
	static int hCol = 499;
	static int tCol = 499;
	static int total = 0;
	public static void main(String[] args) {
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day9Input.txt")));
			matrix[tRow][tCol] = 1;
			total = total + 1;
			while((line = br.readLine()) != null){
				String[] instruction = line.split(" ");
				String direction = instruction[0]; 
				int distance = Integer.parseInt(instruction[1]);
				if(direction.equals("R")) {
					while(distance>0) {
						distance--;
						hCol++;
						move();
					}
				}else if(direction.equals("L")) {
					while(distance>0) {
						distance--;
						hCol--;
						move();
					}
				}else if(direction.equals("U")) {
					while(distance>0) {
						distance--;
						hRow--;
						move();
					}
				}else if(direction.equals("D")) {
					while(distance>0) {
						distance--;
						hRow++;
						move();
					}
				}
			}
			System.out.println(total);		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void move() {
		if(hRow == tRow && hCol > tCol+1) {
			tCol = tCol + 1;
		}else if(hRow == tRow && hCol < tCol-1){
			tCol = tCol - 1;
		}else if(hCol == tCol && hRow > tRow+1) {
			tRow = tRow + 1;
		}else if(hCol == tCol && hRow < tRow-1){
			tRow = tRow - 1;
		}else if(hCol == tCol+1 && hRow > tRow+1) {
			tRow = tRow + 1;
			tCol = tCol + 1;							
		}else if(hCol == tCol+1 && hRow < tRow-1) {
			tRow = tRow - 1;
			tCol = tCol + 1;							
		}else if(hCol == tCol-1 && hRow > tRow+1) {
			tRow = tRow + 1;
			tCol = tCol - 1;							
		}else if(hCol == tCol-1 && hRow < tRow-1) {
			tRow = tRow - 1;
			tCol = tCol - 1;							
		}else if(hRow == tRow+1 && hCol > tCol+1) {
			tCol = tCol + 1;
			tRow = tRow + 1;							
		}else if(hRow == tRow+1 && hCol < tCol-1) {
			tCol = tCol - 1;
			tRow = tRow + 1;							
		}else if(hRow == tRow-1 && hCol > tCol+1) {
			tCol = tCol + 1;
			tRow = tRow - 1;							
		}else if(hRow == tRow-1 && hCol < tCol-1) {
			tCol = tCol - 1;
			tRow = tRow - 1;							
		}
		if(matrix[tRow][tCol] == 0) {
			matrix[tRow][tCol] = 1;
			total++;
		}
	}


}
