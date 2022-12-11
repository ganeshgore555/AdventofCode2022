package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Day11Part2 {
	
	class Monkey {
		LinkedList<Long> itemList = new LinkedList<Long>();
		String worry;
		String worryOperator;
		int testDivisible;
		int testTrue;
		int testFalse;
		long inspectCount;
	}
	
	public static void main(String[] args) {
		try {
			Day11Part2 inst = new Day11Part2();
			String line = "";
			int number = 0;
			LinkedHashMap<Integer,Monkey> monkeyMap = new LinkedHashMap<Integer,Monkey>();
			Monkey currentMonkey = null;
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day11Input.txt")));
			while((line = br.readLine()) != null){
				System.out.println(line);
				String[] instruction = null;
				String[] list = null;
				if(line.contains("Monkey")) {
					instruction = line.split(" ");
					number = Integer.parseInt(instruction[1].substring(0, instruction[1].length()-1));
					System.out.println("Number:"  + number);
					currentMonkey = inst.new Monkey();
				}else if(line.contains("Starting items")) {
					instruction = line.split(":");
					list = instruction[1].split(",");
					if(list != null) {
						LinkedList<Long>itemList = currentMonkey.itemList;
						for(String s : list) {
							System.out.println("Item:" + s);
							itemList.add(Long.parseLong(s.trim()));
						}
					}
				}else if(line.contains("Operation")) {
					instruction = line.split(" ");
					currentMonkey.worry = instruction[instruction.length - 1].trim();
					currentMonkey.worryOperator = instruction[instruction.length - 2].trim();
					System.out.println("Operation:" + currentMonkey.worryOperator + " " + currentMonkey.worry );
					
				}else if(line.contains("Test")) {
					instruction = line.split(" ");
					currentMonkey.testDivisible = Integer.parseInt(instruction[instruction.length - 1]);
					System.out.println("Test:" + currentMonkey.testDivisible);
				}else if(line.contains("true")) {
					instruction = line.split(" ");
					currentMonkey.testTrue = Integer.parseInt(instruction[instruction.length - 1]);
					System.out.println("Test true:" + currentMonkey.testTrue);
				}else if(line.contains("false")) {
					instruction = line.split(" ");
					currentMonkey.testFalse = Integer.parseInt(instruction[instruction.length - 1]);
					System.out.println("Test false:" + currentMonkey.testFalse);
					monkeyMap.put(number, currentMonkey);
				}
			}
			
			int round = 1;
			while(round <= 10000) {
				for(Integer num : monkeyMap.keySet()) {
					currentMonkey = monkeyMap.get(num);
					LinkedList<Long> newItemList = new LinkedList<Long>();
					for(Long item : currentMonkey.itemList) {
						long newItem = 0;
						currentMonkey.inspectCount++;
						long worry = currentMonkey.worry.equals("old") ? item : Long.parseLong(currentMonkey.worry);
						if(currentMonkey.worryOperator.equals("+")) {
							newItem = item + worry;
						}else if(currentMonkey.worryOperator.equals("-")) {
							newItem = item - worry;
						}else if(currentMonkey.worryOperator.equals("*")) {
							newItem = item * worry;
						}else if(currentMonkey.worryOperator.equals("/")) {
							newItem = item / worry;
						}

						if((newItem % currentMonkey.testDivisible) == 0) {
							monkeyMap.get(currentMonkey.testTrue).itemList.add(newItem);
						}else {
							monkeyMap.get(currentMonkey.testFalse).itemList.add(newItem);
						}
					}
					currentMonkey.itemList = newItemList;
				}
				
				if(round == 1 || round == 20 || round == 1000) {
					for(Integer num : monkeyMap.keySet()) {
						currentMonkey = monkeyMap.get(num);
						System.out.println("Round: " + round+ " Inspection Count for "+ num + ":" + currentMonkey.inspectCount);
					}
				}
				round++;
			}
			long max1 = 0;
			long max2 = 0;
			for(Integer num : monkeyMap.keySet()) {
				currentMonkey = monkeyMap.get(num);
				if(currentMonkey.inspectCount >= max1) {
					max2 = max1;
					max1 = currentMonkey.inspectCount;
				}else if(currentMonkey.inspectCount > max2) {
					max2 = currentMonkey.inspectCount;
				}
				System.out.println("Inspection Count:" + currentMonkey.inspectCount);
			}
			System.out.println("Monkey Business:" + max1*max2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
