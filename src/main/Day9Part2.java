package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day9Part2 {
	static int row = 1000;
	static int col = 1000;
	static int n = 10;
	static int[][]matrix = new int[row][col];
	static int[][]knots = new int[n][2];
	static int r = 499;
	static int c = 499;
	static int total = 0;
	public static void main(String[] args) {
		try {			
			String line = "";
			int lineCount = 0;
			matrix[r][c] = 1;
			total = total + 1;
			for(int i = 0; i < n; i++) {
				knots[i][0] = r;
				knots[i][1] = c;
			}
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day9Input.txt")));
			while((line = br.readLine()) != null){
				lineCount++;
				String[] instruction = line.split(" ");
				String direction = instruction[0]; 
				int distance = Integer.parseInt(instruction[1]);
				if(direction.equals("R")) {
					while(distance>0) {
						distance--;
						knots[0][1] = knots[0][1] + 1;  
						for(int i = 0; i < n-1; i++) {
							move(i+1, knots[i][0],knots[i][1],knots[i+1][0],knots[i+1][1]);
						}
					}
				}else if(direction.equals("L")) {
					while(distance>0) {
						distance--;
						knots[0][1] = knots[0][1] - 1;
						for(int i = 0; i < n-1; i++) {
							move(i+1, knots[i][0],knots[i][1],knots[i+1][0],knots[i+1][1]);
						}
					}
				}else if(direction.equals("U")) {
					while(distance>0) {
						distance--;
						knots[0][0] = knots[0][0] - 1;
						for(int i = 0; i < n-1; i++) {
							move(i+1, knots[i][0],knots[i][1],knots[i+1][0],knots[i+1][1]);
						}
					}
				}else if(direction.equals("D")) {
					while(distance>0) {
						distance--;
						knots[0][0] = knots[0][0] + 1;
						for(int i = 0; i < n-1; i++) {
							move(i+1, knots[i][0],knots[i][1],knots[i+1][0],knots[i+1][1]);
						}
					}
				}
				/*
				System.out.println(line);
				int[][]grid = new int[row][col];
				for(int i = 0; i < knots.length; i++) {
					grid[knots[i][0]][knots[i][1]] = i;
				}				
				for(int i = 0; i < row; i++) {
					for(int j = 0; j < col; j++) {
						System.out.print(grid[i][j]);
					}
					System.out.println("");
				}
				*/
			}
			/*
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.println("");
			}
			*/
			System.out.println(total);		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void move(int knot, int hRow, int hCol, int tRow, int tCol) {
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
		}else if(hCol == tCol-2 && hRow == tRow-2) {
			tRow = tRow - 1;
			tCol = tCol - 1;							
		}else if(hCol == tCol+2 && hRow == tRow-2) {
			tRow = tRow - 1;
			tCol = tCol + 1;							
		}else if(hCol == tCol-2 && hRow == tRow+2) {
			tRow = tRow + 1;
			tCol = tCol - 1;							
		}else if(hCol == tCol+2 && hRow == tRow+2) {
			tRow = tRow + 1;
			tCol = tCol + 1;							
		}
		
		knots[knot][0] = tRow;
		knots[knot][1] = tCol;
		
		if(knot == n-1 && matrix[tRow][tCol] == 0) {
			matrix[tRow][tCol] = 1;
			total = total + 1;
		}
	}
}
