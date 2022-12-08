package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Day7 {

	public static void main(String[] args) {
		try {
			String line = null;
			int total = 0;
			TreeNode root = new TreeNode("/",null);
			TreeNode currentNode = null;
			root.parent = null;
			root.isfile = false;
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day7Input.txt")));
			while((line = br.readLine()) != null){
				String[] instruction = {};
				if(line.contains("$") && line.contains("cd")){
					instruction = line.split(" ");
					if(instruction[2].equals("/")) {
						currentNode = root;
					}else if(instruction[2].equals("..")) {
						currentNode = currentNode.parent;
					}else {
						TreeNode dirNode = new TreeNode(instruction[2], currentNode);
						for(TreeNode node : currentNode.child) {
							if(node.equals(dirNode)) {
								currentNode = node;
								break;
							}
						}
					}
				}else if(line.contains("$") && line.contains("ls")) {
					
				}else if(line.contains("dir")) {
					instruction = line.split(" ");
					String dirName = instruction[1];
					TreeNode dirNode = new TreeNode(dirName, currentNode);
					if(!currentNode.child.contains(dirNode)) {
						dirNode.isfile = false;
						dirNode.size = Integer.MIN_VALUE;
						currentNode.child.add(dirNode);
					}
				}else{
					instruction = line.split(" ");
					String fileName = instruction[1];
					int fileSize = Integer.parseInt(instruction[0]);
					TreeNode fileNode = new TreeNode(fileName, currentNode);
					if(!currentNode.child.contains(fileNode)) {
						fileNode.isfile = true;
						fileNode.size = fileSize;
						currentNode.child.add(fileNode);
					}
				}
			}
			
			updateSize(root);
			System.out.println("sumOfDirSizeAtMost100000 : " + getSumOfDirSizeAtMost100000(root));
			System.out.println("occupied : " + root.size);
			int freeSpace = 70000000 - root.size;
			System.out.println("freeSpace : " + freeSpace);
			int moreSpaceNeeded = 30000000 - freeSpace;
			System.out.println("moreSpaceNeeded : " + moreSpaceNeeded);
			System.out.println(minSizeDir(root,moreSpaceNeeded,root.size));
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static int getSumOfDirSizeAtMost100000(TreeNode parent) {
		int size = 0;
		if(parent.size <= 100000) {
			size = parent.size;
		}
		for(TreeNode node : parent.child) {
			if(!node.isfile) {
				size = size + getSumOfDirSizeAtMost100000(node);
			}
		}
		return size;
	}

	private static int updateSize(TreeNode parent) {
		int size = 0;
		for(TreeNode node : parent.child) {
			if(node.isfile) {
				size = size + node.size;
			}else {
				size = size + updateSize(node);
			}
		}
		parent.size = size;
		return size;
	}
	
	
	private static int minSizeDir(TreeNode parent, int moreSpaceNeeded, int currentMin) {
		for(TreeNode node : parent.child) {
			if(!node.isfile) {
				if(node.size >= moreSpaceNeeded && node.size < currentMin) {
					currentMin = node.size;
				}
				int minChild = minSizeDir(node,moreSpaceNeeded,currentMin);
				if(minChild < currentMin) {
					currentMin = minChild;
				}
			}
		}
		return currentMin;
	}
}
