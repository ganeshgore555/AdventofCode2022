package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * 
 * 
            [C]         [N] [R]    
[J] [T]     [H]         [P] [L]    
[F] [S] [T] [B]         [M] [D]    
[C] [L] [J] [Z] [S]     [L] [B]    
[N] [Q] [G] [J] [J]     [F] [F] [R]
[D] [V] [B] [L] [B] [Q] [D] [M] [T]
[B] [Z] [Z] [T] [V] [S] [V] [S] [D]
[W] [P] [P] [D] [G] [P] [B] [P] [V]
 1   2   3   4   5   6   7   8   9 

 *
 */

public class Day5Part1 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day5Input.txt")));
			String line = null;
			HashMap<Integer,Stack> map = new HashMap<Integer,Stack>();
			Stack<String> stack1 = new Stack<String>();
			stack1.push("W");
			stack1.push("B");
			stack1.push("D");
			stack1.push("N");
			stack1.push("C");
			stack1.push("F");
			stack1.push("J");
			map.put(1, stack1);
			
			Stack<String> stack2 = new Stack<String>();
			stack2.push("P");
			stack2.push("Z");
			stack2.push("V");
			stack2.push("Q");
			stack2.push("L");
			stack2.push("S");
			stack2.push("T");
			map.put(2, stack2);
			
			Stack<String> stack3 = new Stack<String>();
			stack3.push("P");
			stack3.push("Z");
			stack3.push("B");
			stack3.push("G");
			stack3.push("J");
			stack3.push("T");
			map.put(3, stack3);
			
			Stack<String> stack4 = new Stack<String>();
			stack4.push("D");
			stack4.push("T");
			stack4.push("L");
			stack4.push("J");
			stack4.push("Z");
			stack4.push("B");
			stack4.push("H");
			stack4.push("C");
			map.put(4, stack4);
			
			Stack<String> stack5 = new Stack<String>();
			stack5.push("G");
			stack5.push("V");
			stack5.push("B");
			stack5.push("J");
			stack5.push("S");
			map.put(5, stack5);
			
			Stack<String> stack6 = new Stack<String>();
			stack6.push("P");
			stack6.push("S");
			stack6.push("Q");
			map.put(6, stack6);
			
			Stack<String> stack7 = new Stack<String>();
			stack7.push("B");
			stack7.push("V");
			stack7.push("D");
			stack7.push("F");
			stack7.push("L");
			stack7.push("M");
			stack7.push("P");
			stack7.push("N");
			map.put(7, stack7);
			
			Stack<String> stack8 = new Stack<String>();
			stack8.push("P");
			stack8.push("S");
			stack8.push("M");
			stack8.push("F");
			stack8.push("B");
			stack8.push("D");
			stack8.push("L");
			stack8.push("R");
			map.put(8, stack8);
			
			Stack<String> stack9 = new Stack<String>();
			stack9.push("V");
			stack9.push("D");
			stack9.push("T");
			stack9.push("R");
			map.put(9, stack9);
			
			while((line = br.readLine()) != null){
				String[]instruction = line.split(" ");
				int num = Integer.parseInt(instruction[1]);
				int from = Integer.parseInt(instruction[3]);
				int to = Integer.parseInt(instruction[5]);
				Stack<String> stackFrom = map.get(from);
				Stack<String> stackTo = map.get(to);
				for(int i=1; i<=num; i++) {
					stackTo.push(stackFrom.pop());
				}
			}
			for(int i=1; i<=9; i++) {
				Stack<String> stack = map.get(i);
				System.out.print(stack.peek());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
