package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Day12Part1 {

	static int visited[][] = null;
	static int m=0;
	static int n=0;

	public static void main(String[] args) {
		try {
			String line = "";
			BufferedReader tempReader = new BufferedReader(new FileReader(new File(".//resources//Day12Input.txt")));
			while ((line = tempReader.readLine()) != null) { //Get Grid Dimensions
				if(n==0)
					n=line.length();
				m++;
			}
			char[][] grid = new char[m][n];
			visited = new int[m][n];
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day12Input.txt")));
			int row = 0;
			int col = 0;
			int[][] src = new int[1][2];
			int[][] dest = new int[1][2];
			while ((line = br.readLine()) != null) {
				char[] rowData = line.toCharArray();
				for (char c : rowData) {
					if (c == 'S') {
						src[0][0] = row;
						src[0][1] = col;
						c = 'a';
					}
					if (c == 'z') {
						dest[0][0] = row;
						dest[0][1] = col;
						c = 'z';
					}
					grid[row][col] = c;
					col++;
				}
				col = 0;
				row++;
			}

			visited[src[0][0]][src[0][1]] = 1;
			int step = bestPath(grid, src[0][0], src[0][1], dest, Integer.MAX_VALUE, 1);
			System.out.println(step);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static int bestPath(char[][] grid, int curRow, int curCol, int[][] dest, int minStep, int step) {

		if (curRow == dest[0][0] && curCol == dest[0][1]) {
			minStep = Math.min(step, minStep);			
			return minStep;
		}
		
		visited[curRow][curCol] = step;
		// go to the bottom cell
		if (isPossible(grid, curRow, curCol, curRow + 1, curCol)) {
			minStep = bestPath(grid, curRow + 1, curCol, dest, minStep, step + 1);
		}
		// go to the right cell
		if (isPossible(grid, curRow, curCol, curRow, curCol + 1)) {
			minStep = bestPath(grid, curRow, curCol + 1, dest, minStep, step + 1);
		}
		// go to the top cell
		if (isPossible(grid, curRow, curCol, curRow - 1, curCol)) {
			minStep = bestPath(grid, curRow - 1, curCol, dest, minStep, step + 1);
		}
		// go to the left cell
		if (isPossible(grid, curRow, curCol, curRow, curCol - 1)) {
			minStep = bestPath(grid, curRow, curCol - 1, dest, minStep, step + 1);
		}
		
		visited[curRow][curCol] = 0;
		
		return minStep;
	}

	static boolean isPossible(char[][] grid, int curRow, int curCol, int row, int col) {
		if (row == -1 || row == m || col == -1 || col == n || visited[row][col] != 0 || ((int) grid[row][col] > ((int) grid[curRow][curCol] + 1)))
			return false;

		return true;
	}

}
