package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Day12Part1BFS {

	static int visited[][] = null;
	static int m=0;
	static int n=0;

    private static class Cell  {
        int x;
        int y;
        int dist;  	
        Cell prev;  
        Cell(int x, int y, int dist, Cell prev) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.prev = prev;
        }
        
        @Override
        public String toString(){
        	return "(" + x + "," + y + ")";
        }
    }
	
	
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
			int[] src = new int[2];
			int[] dest = new int[2];
			while ((line = br.readLine()) != null) {
				char[] rowData = line.toCharArray();
				for (char c : rowData) {
					if (c == 'S') {
						src[0] = row;
						src[1] = col;
						c = 'a';
					}
					if (c == 'z') {
						dest[0] = row;
						dest[1] = col;
						c = 'z';
					}
					grid[row][col] = c;
					col++;
				}
				col = 0;
				row++;
			}

			bestPath(grid, src, dest);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void bestPath(char[][] matrix, int[] start, int[] end) {
		int sx = start[0], sy = start[1];
		int dx = end[0], dy = end[1];	


	    for (int i = 0; i < m; i++) {
	        for (int j = 0; j < n; j++) {
	            if (matrix[i][j] != 0) {
	                cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
	            }
	        }
	    }
	    //breadth first search
	    LinkedList<Cell> queue = new LinkedList<>();       
	    Cell src = cells[sx][sy];
	    src.dist = 0;
	    queue.add(src);
	    Cell dest = null;
	    Cell p;
	    while ((p = queue.poll()) != null) {
	    	//find destination 
	        if (p.x == dx && p.y == dy) { 
	            dest = p;
	            break;
	        }      
	        // moving up
	        visit(cells, queue, p.x - 1, p.y, p);        
	        // moving down
	        visit(cells, queue, p.x + 1, p.y, p);        
	        // moving left
	        visit(cells, queue, p.x, p.y - 1, p);        
	        //moving right
	        visit(cells, queue, p.x, p.y + 1, p);
	    }
	    
	    //compose the path if path exists
	    if (dest == null) {
	    	System.out.println("there is no path.");
	        return;
	    } else {
	        LinkedList<Cell> path = new LinkedList<>();
	        p = dest;
	        do {
	            path.addFirst(p);
	        } while ((p = p.prev) != null);
	        System.out.println(path);
	  
	
	
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
