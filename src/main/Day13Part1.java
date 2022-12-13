package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;


public class Day13Part1 {

	public static class SignalPacketComparator implements Comparator<LinkedList<Object>>{
		@SuppressWarnings("unchecked")
		@Override
		public int compare(LinkedList<Object> list1, LinkedList<Object> list2) {
			int i = 0;
			int rightOrder = 0;
			for(Object o1 : list1) {
				if(i >= list2.size()) {
					rightOrder = 1;
					break;
				}
				Object o2 = list2.get(i);	
				if(o1 instanceof LinkedList || o2 instanceof LinkedList) {
					LinkedList<Object> l1= null;
					LinkedList<Object> l2= null;				
					if(o1 instanceof LinkedList)
						l1 = (LinkedList<Object>) o1;
					else {
						l1 = new LinkedList<Object>();
						l1.add(o1);
					}
					if(o2 instanceof LinkedList)
						l2 = (LinkedList<Object>) o2;
					else {
						l2 = new LinkedList<Object>();
						l2.add(o2);
					}
					rightOrder = compare(l1,l2);
					if(rightOrder == 0) {
						if(l1.size() < l2.size()) {
							rightOrder = -1;
						}
					}
					if(rightOrder == 1 || rightOrder == -1)
						break;
				}else {
					int val1 = (int) o1;
					int val2 = (int) o2;
					if(val1 < val2) {
						rightOrder = -1;
						break;
					}
					else if(val1 > val2) {
						rightOrder = 1;
						break;
					}
					else
						rightOrder = 0;
				}
				i++;
			}
			if(rightOrder == 0 && list1.size() < list2.size()) {
				rightOrder = -1;
			}
			return rightOrder;
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			String line = "";
			int counter = 0;
			int index = 0;
			int sum = 0;
			LinkedList<Object> list1 = null;
			LinkedList<Object> list2 = null;
			ArrayList<LinkedList<Object>> list = new ArrayList<LinkedList<Object>>();
			SignalPacketComparator comparator = new SignalPacketComparator();
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day13Input.txt")));
			while((line = br.readLine()) != null){
				if(line.trim().length()!=0) {
					counter++;
					if(counter % 2 == 1) {
						list1 = parseInput(line);
					}else {
						index++;
						list2 = parseInput(line);
						int order = comparator.compare(list1, list2);
						if(order == -1) {
							sum = sum + index;
						}
						list.add(list1);
						list.add(list2);
					}
				}
			}
			System.out.println("Part1:" + sum);
			list.add(parseInput("[[2]]"));
			list.add(parseInput("[[6]]"));
			Collections.sort(list, comparator);
			int sortedListIndex = 0;
			int key = 1;
			for(LinkedList<Object> l : list) {
				sortedListIndex++;
				if(l.toString().replace(" ", "").equals("[[2]]") || l.toString().replace(" ", "").equals("[[6]]")) {
					key = key * sortedListIndex; 
				}
			}
			System.out.println("Part2:" + key);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static LinkedList<Object> parseInput(String line){
		LinkedList<Object> list = new LinkedList<Object>();
		char[] lineArr = line.toCharArray();
		String num = "";
		LinkedList<Object> stack = new LinkedList<Object>();
		stack.push(list);
		for(char ch : lineArr) {
			if(ch == '[') {
				LinkedList<Object> templist = new LinkedList<Object>();
				((LinkedList<Object>)stack.peek()).add(templist);
				stack.push(templist);
			}else if(ch == ',') {
				if(num.length() > 0) {
					((LinkedList<Object>)stack.peek()).add(Integer.parseInt(num));
					num = "";
				}
			}else if(ch == ']'){
				if(num.length() > 0) {
					((LinkedList<Object>)stack.peek()).add(Integer.parseInt(num));
					num = "";
				}
				stack.pop();
			}else {
				num = num + ch;
			}
		}
		return (LinkedList<Object>)list.get(0);
	}
	
	


}
