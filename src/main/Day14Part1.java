package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Day14Part1 {

	static final Map<String, int[]> directions = Map.of("R", new int[] {0,1},"L", new int[] {0,-1},"U", new int[] {-1,0},"D", new int[] {1,0},
			"UR", new int[] {-1,1},"UL", new int[] {-1,-1},"DR", new int[] {1,1},"DL", new int[] {1,-1});
	public static void main(String[] args) {
		try {
			String line = "";
			int m = 500;
			int n = 550;
			int maxBottom = 0;
			int[][] grid = new int[m][n];
			grid[0][500] = 9;
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day14Input.txt")));
			while ((line = br.readLine()) != null) {
				String[] paths = line.split("->");
				String[] cordFrom = null;
				String[] cordTo = null;
				for(String point : paths) {
					if(cordFrom == null)
						cordFrom = point.trim().split(",");
					else
						cordTo = point.trim().split(",");					
					if(cordFrom != null && cordTo != null) {
						markRock(grid,Integer.parseInt(cordFrom[1]), Integer.parseInt(cordFrom[0]), Integer.parseInt(cordTo[1]), Integer.parseInt(cordTo[0]));
						maxBottom = Math.max(maxBottom, Integer.parseInt(cordFrom[1]));
						maxBottom = Math.max(maxBottom, Integer.parseInt(cordTo[1]));
						cordFrom = cordTo;
					}
				}
			}
			int sand = 0;			
			while(true) {
				int x = 0;
				int y = 500;
				boolean limitReached = false;
				while(true) {
					if (isPossible(grid, x, y, x + directions.get("D")[0], y + directions.get("D")[1])) {
						x=x + directions.get("D")[0];
						y=y + directions.get("D")[1];
					}else if(isPossible(grid, x, y, x + directions.get("DL")[0], y + directions.get("DL")[1])) {
						x=x + directions.get("DL")[0];
						y=y + directions.get("DL")[1];
					}else if(isPossible(grid, x, y, x + directions.get("DR")[0], y + directions.get("DR")[1])) {
						x=x + directions.get("DR")[0];
						y=y + directions.get("DR")[1];
					}else {
						grid[x][y]=5;
						sand++;
						break;
					}
					
					if(x > maxBottom) {
						limitReached = true;
						break;
					}
				}
				if(limitReached)
					break;
			}
			System.out.println(sand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static boolean isPossible(int[][] grid, int curRow, int curCol, int row, int col) {
		int m = grid.length;
		int n = grid[0].length;
		if (row == -1 || row == m || col == -1 || col == n || grid[row][col] == 1 || grid[row][col] == 5)// 1=Rock 5=Sand
			return false;
		return true;
	}

	
	private static void markRock(int[][] grid, int rowFrom, int colFrom, int rowTo, int colTo) {
		if(colFrom == colTo) {
			int j=colFrom;
			for(int i = Math.min(rowFrom, rowTo); i <= Math.max(rowFrom, rowTo); i++)
				grid[i][j]=1;
		}else if((rowFrom == rowTo)) {
			int i=rowFrom;
			for(int j = Math.min(colFrom, colTo); j <= Math.max(colFrom, colTo); j++)
				grid[i][j]=1;
		}else {System.out.println("Not Straight Line");}
	}

	
}
