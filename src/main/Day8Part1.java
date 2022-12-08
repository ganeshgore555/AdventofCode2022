package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day8Part1 {

	public static void main(String[] args) {
		try {
			String line = null;
			int total = 0;
			int row = 0;
			int col = 0;
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day8Input.txt")));
			while((line = br.readLine()) != null){
				col = line.length();
				row = ++total;
			}
			int[][]matrix = new int[row][col];
			System.out.println(row + " " + col );
			br = new BufferedReader(new FileReader(new File(".//resources//Day8Input.txt")));
			int lineNum = 0;
			while((line = br.readLine()) != null){
				for(int i = 0 ; i < line.length(); i++) {
					matrix[lineNum][i] = Integer.parseInt(line.charAt(i)+"");
					System.out.print(line.charAt(i));
				}
				System.out.println("");
				lineNum++;
			}
			
			int visibleCount = 0;
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					if(i == 0 || i == row-1 || j == 0 || j == col-1) {
						visibleCount++;
					}else if(visiblefromRight(matrix,i,j,row,col)) {
						visibleCount++;
					}else if(visiblefromLeft(matrix,i,j,row,col)) {
						visibleCount++;
					}else if(visiblefromTop(matrix,i,j,row,col)) {
						visibleCount++;
					}else if(visiblefromBottom(matrix,i,j,row,col)) {
						visibleCount++;
					}
					
				}
			}
			System.out.println(visibleCount);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static boolean visiblefromBottom(int[][] matrix, int i, int j, int row, int col) {
		int val = matrix[i][j];
		boolean visible = true;
		for(int curRow = i+1; curRow < row; curRow++) {
			if(val <= matrix[curRow][j]) {
				visible = false;
				break;
			}
		}
		return visible;
	}

	private static boolean visiblefromTop(int[][] matrix, int i, int j, int row, int col) {
		int val = matrix[i][j];
		boolean visible = true;
		for(int curRow = i-1; curRow >= 0; curRow--) {
			if(val <= matrix[curRow][j]) {
				visible = false;
				break;
			}
		}
		return visible;
	}

	private static boolean visiblefromLeft(int[][] matrix, int i, int j, int row, int col) {
		int val = matrix[i][j];
		boolean visible = true;
		for(int curCol = j-1; curCol >= 0; curCol--) {
			if(val <= matrix[i][curCol]) {
				visible = false;
				break;
			}
		}
		return visible;
	}

	private static boolean visiblefromRight(int[][] matrix, int i, int j, int row, int col) {
		int val = matrix[i][j];
		boolean visible = true;
		for(int curCol = j+1; curCol < col; curCol++) {
			if(val <= matrix[i][curCol]) {
				visible = false;
				break;
			}
		}
		return visible;
	}
}
