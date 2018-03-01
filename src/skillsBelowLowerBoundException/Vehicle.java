package skillsBelowLowerBoundException;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	private boolean isFree;
	private int x;
	private int y;
	private List<Ride> rides;
	private int freeCount;
	private String rideString = "";
	
	public Vehicle(int x, int y) {
		super();
		this.isFree = true;
		this.x = x;
		this.y = y;
		this.rides = new ArrayList<Ride>();
		this.freeCount = 0;
	}
	
	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
		if (freeCount == 0)
			isFree = true;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public boolean isFree() {
		return isFree;
	}
	public void setisFree(boolean isFree) {
		this.isFree = isFree;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public List<Ride> getRides() {
		return rides;
	}
	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}
	
	public void addRide(int ride){
		rideString = rideString + " " + ride;
	}

	public String getRideString() {
		return rideString;
	}
	
}
