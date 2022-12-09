package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Day9Part2Optimised {
	
	static final Map<String, int[]> directions = Map.of("R", new int[] {0,1},"L", new int[] {0,-1},"U", new int[] {-1,0},"D", new int[] {1,0});
	public static void main(String[] args) {
		try {
			int n = 10; // 10 knots including head and tail, 1(H).2.3.4.5.6.7.8.9(T) 
			int[][]matrix = new int[1000][1000]; // grid to hold visited position 
			int[][]knots = new int[n][2]; // List to hold (x,y) position of n knots
			int r = 499,c = 499; // Random start position in middle to huge grid based on size of grid	
			int total = 0; // Total position visited
			String line = "";
			matrix[r][c] = 1; // mark start position as visited by default
			total = total + 1;
			for(int i = 0; i < n; i++) { // initialize all knot position to start position
				knots[i][0] = r;
				knots[i][1] = c;
			}
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day9Input.txt")));
			while((line = br.readLine()) != null){
				String[] instruction = line.split(" ");
				int[] direction = directions.get(instruction[0]); 
				int distance = Integer.parseInt(instruction[1]);
				total = total + move(direction,distance,n,matrix,knots);								
			}
			System.out.println(total);		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static int move(int[] direction, int distance, int n, int[][] matrix, int[][] knots) {
		int totalMove = 0;
		while(distance > 0) {
			knots[0][0] = knots[0][0] + direction[0];//Move head as per directions
			knots[0][1]	= knots[0][1] + direction[1];
			for(int i = 1; i < n; i++) {//Move each knot in direction of the knot before it. Move current knot only if previous knot is not adjacent or diagonally around current knot.
				if((knots[i-1][0] == knots[i][0] || knots[i-1][1] == knots[i][1]) && (Math.abs(knots[i-1][0]-knots[i][0]) > 1 || Math.abs(knots[i-1][1]-knots[i][1]) > 1)  ) { //If the previous knot is two steps directly up, down, left, or right from the current knot, the current knot must move one step in that direction   
					knots[i][0] = knots[i][0] + ((knots[i-1][0] == knots[i][0]) ? 0 : (knots[i-1][0] < knots[i][0]) ? -1 : 1);
					knots[i][1] = knots[i][1] + ((knots[i-1][1] == knots[i][1]) ? 0 : (knots[i-1][1] < knots[i][1]) ? -1 : 1);
				}else if((knots[i-1][0] != knots[i][0] && knots[i-1][1] != knots[i][1]) && (Math.abs(knots[i-1][0]-knots[i][0]) > 1 || Math.abs(knots[i-1][1]-knots[i][1]) > 1)) { //If the previous knot and current knot aren't in same row or column, the current knot always moves one step diagonally in the direction of the knot ahead it
					knots[i][0] = knots[i][0] + ((knots[i-1][0] < knots[i][0]) ? -1 : 1);
					knots[i][1] = knots[i][1] + ((knots[i-1][1] < knots[i][1]) ? -1 : 1);
				}
			}
			if(matrix[knots[n-1][0]][knots[n-1][1]] == 0) {//If last knot or tail has visited it's current position for first time then mark the current position as visited
				matrix[knots[n-1][0]][knots[n-1][1]] = 1;
				totalMove = totalMove + 1;
			}
			distance--;
		}
		return totalMove;
	}
}
