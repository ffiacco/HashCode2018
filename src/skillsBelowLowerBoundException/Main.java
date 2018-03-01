package skillsBelowLowerBoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	private static int R;
	private static int C;
	private static int F;
	private static int N;
	private static int B;
	private static int T;

	public static void main(String[] args) {
		
		String path = args[0];
		
		List<String> lines = FileManager.getLines(path);
		splitFirstLine(lines);
		lines.remove(0);
		
		List<Ride> rides = FileManager.splitLines(lines);
		
		for (int i = 0; i < rides.size(); i++) {
			rides.get(i).setIndex(i);
		}
		
		List<Vehicle> vehicles = new ArrayList<>();
		for (int i = 0; i < F; i++) {
			vehicles.add(new Vehicle(0,0));
		}
		
		int points = 0;
		int bCount = 0;
		
		for (int t = 0; t <= T; t++) {
			for (Vehicle v : vehicles) {
				Ride bestRide = null;
				int bestCost = Integer.MIN_VALUE;
				int bestFreeCount = 0;
				int bestD = 0;
				boolean bestWaiting = false;
				if(v.isFree()){
					for (Ride ride : rides) {
						if(ride != null){
							int sd = distanceCalculator(v.getX(), v.getY(), ride.getA(), ride.getB());
							int d = distanceCalculator(ride.getA(), ride.getB(), ride.getX(), ride.getY());
							
							boolean waiting = (ride.getS() - sd - t) > 0;
							int wait = ride.getS() - sd - t;
							
							if( (waiting && ride.getF() - d - ride.getS() > 0) || (!waiting && ride.getF() - t - sd - d > 0) ){
								// feasible
								int cost = (wait >= 0 ? B : 0) + d - ( (waiting ? wait : 0) + sd );
								
								if(bestRide == null || cost > bestCost){
									bestRide = ride;
									bestCost = cost;
									bestFreeCount = (waiting ? wait : 0) + d + sd;
									bestD = d;
									bestWaiting = waiting;
								}
							}
						}
					}
					
					if(bestRide != null){
						System.out.println("\tassigned");
						v.getRides().add(bestRide);
						v.setFreeCount(bestFreeCount-1);
						v.addRide(bestRide.getIndex());
						v.setX(bestRide.getX());
						v.setY(bestRide.getY());
						points = points + (bestWaiting ? B : 0) + bestD;
						bCount = bCount + (bestWaiting ? 1 : 0);
						rides.remove(bestRide);
					}
				}else{
					v.setFreeCount(v.getFreeCount() - 1);
				}
			}
			System.out.println(t);
		}
		
		System.out.println("\n\n" + points + "\n" + bCount + "\n" + rides.size());
		
		
		saveRides(vehicles);
		
		
	}
	
	
	private static void saveRides(List<Vehicle> vehicles) {
		List<String> outLines = new ArrayList<String>();
		for (int i = 0; i < vehicles.size(); i++) {
			outLines.add(vehicles.get(i).getRides().size() + vehicles.get(i).getRideString());
		}
		
		FileManager.printLines(outLines);
	}


	public static void splitFirstLine(List<String> lines){
		StringTokenizer st = new StringTokenizer(lines.get(0));
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
	}
	
	private static int distanceCalculator(int x1, int y1, int x2, int y2){
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}

}
