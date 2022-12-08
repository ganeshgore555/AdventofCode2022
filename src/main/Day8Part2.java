package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day8Part2 {

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
			
			int score = 0;
			int maxScore = 0;
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					if(i == 0 || i == row-1 || j == 0 || j == col-1) {
						score = 0;						
					}else {
						score = scorefromRight(matrix,i,j,row,col) * scorefromLeft(matrix,i,j,row,col) * scorefromTop(matrix,i,j,row,col) * scorefromBottom(matrix,i,j,row,col);
					}
					if(score > maxScore) {
						maxScore = score;
					}
				}
			}
			System.out.println(maxScore);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static int scorefromBottom(int[][] matrix, int i, int j, int row, int col) {
		int val = matrix[i][j];
		int score = 0;
		for(int curRow = i+1; curRow < row; curRow++) {
			if(val > matrix[curRow][j]) {
				score++;
			}else{
				score++;
				break;
			}
		}
		return score;
	}

	private static int scorefromTop(int[][] matrix, int i, int j, int row, int col) {
		int val = matrix[i][j];
		int score = 0;
		for(int curRow = i-1; curRow >= 0; curRow--) {
			if(val > matrix[curRow][j]) {
				score++;
			}else{
				score++;
				break;
			}
		}
		return score;
	}

	private static int scorefromLeft(int[][] matrix, int i, int j, int row, int col) {
		int val = matrix[i][j];
		int score = 0;
		for(int curCol = j-1; curCol >= 0; curCol--) {
			if(val > matrix[i][curCol]) {
				score++;
			}else{
				score++;
				break;
			}
		}
		return score;
	}

	private static int scorefromRight(int[][] matrix, int i, int j, int row, int col) {
		int val = matrix[i][j];
		int score = 0;
		for(int curCol = j+1; curCol < col; curCol++) {
			if(val > matrix[i][curCol]) {
				score++;
			}else{
				score++;
				break;
			}
		}
		return score;
	}
}
