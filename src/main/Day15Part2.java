package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Day15Part2 {
	
	static final Map<String, int[]> directions = Map.of("R", new int[] {0,1},"L", new int[] {0,-1},"U", new int[] {-1,0},"D", new int[] {1,0},
			"UR", new int[] {-1,1},"UL", new int[] {-1,-1},"DR", new int[] {1,1},"DL", new int[] {1,-1});
	public static void main(String[] args) {
		try {
			String line = "";
			int minX = 0; 
			int minY = 0;
			int maxX = 0; 
			int maxY = 0;
			BufferedReader br = new BufferedReader(new FileReader(new File(".//resources//Day15Input.txt")));
			HashMap<Coordinate,Coordinate> map = new HashMap<Coordinate,Coordinate>();
			HashSet<Coordinate> beacons = new HashSet<Coordinate>();
			while ((line = br.readLine()) != null) {
				Coordinate sensor = new Coordinate();
				sensor.x =  Integer.parseInt(line.split(":")[0].trim().split(",")[0].trim().split("=")[1]);
				sensor.y =  Integer.parseInt(line.split(":")[0].trim().split(",")[1].trim().split("=")[1]);
				Coordinate beacon = new Coordinate();
				beacon.x =  Integer.parseInt(line.split(":")[1].trim().split(",")[0].trim().split("=")[1]);
				beacon.y =  Integer.parseInt(line.split(":")[1].trim().split(",")[1].trim().split("=")[1]);
				map.put(sensor, beacon);
				beacons.add(beacon);
				minX = Math.min(minX, sensor.x);
				minX = Math.min(minX, beacon.x);
				minY = Math.min(minY, sensor.y);
				minY = Math.min(minY, beacon.y);
				maxX = Math.max(maxX, sensor.x);
				maxX = Math.max(maxX, beacon.x);
				maxY = Math.max(maxY, sensor.y);
				maxY = Math.max(maxY, beacon.y);
			}
			
			int i = 0;
			int j = 0;
			boolean found = true;
			for(i=0; i<=4000000; i++) {
				for(j=0; j<=4000000; j++) {
					found = true;
					for(Coordinate sensor : map.keySet()) {
						Coordinate beacon = map.get(sensor);
						int distance = Math.abs(sensor.y-beacon.y) + Math.abs(sensor.x-beacon.x);
						int distanceTemp = Math.abs(sensor.y-j) + Math.abs(sensor.x-i);
						Coordinate postition = new Coordinate(i, j);
						if(distanceTemp <= distance) {
							found = false;
							break;
						}
					}
					if(found)
						break;
				}
				if(found)
					break;
			}
			System.out.println(i + " " + j);
			long signal = (i * 4000000) + j;
			System.out.println(signal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
}
