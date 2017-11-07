package Demo;

import java.util.ArrayList;
import java.util.List;

class Trip {
	String driverName;
	String toLocation;
	double cabFare;

	
	
	public Trip(String driverName, String toLocation, double cabFare) {
		this.driverName = driverName;
		this.toLocation = toLocation;
		this.cabFare = cabFare;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public double getCabFare() {
		return cabFare;
	}

	public void setCabFare(double cabFare) {
		this.cabFare = cabFare;
	}
	
	

}
class Exercise3List{
	public static void main(String[] args){
	
		List<Trip> tripList = new ArrayList<Trip>();
		tripList.add(new Trip("Nick","Eiffel Tower",241.53));
		tripList.add(new Trip("Katelyn","Army Museum",154.31));
		tripList.add(new Trip("Mike","Cathedral",868.65));
		tripList.add(new Trip("Shane","Louvre",52.6));
		
		int index = 0;
		double highestFare = 0;
		int highestFareIndex = 0;
		
		for (Trip trip:tripList){
			System.out.println("Trip "+(index+1)+":");
			System.out.println(trip.getDriverName());
			System.out.println(trip.getToLocation());
			double fare = trip.getCabFare();
			System.out.println(fare);
			System.out.println();
			if (highestFare<fare){
				highestFare = fare;
				highestFareIndex = index;
			}
			index++;
		}
		
		System.out.println("The location with highest fare is:");
		System.out.println(tripList.get(highestFareIndex).getToLocation());
		System.out.println(tripList.get(highestFareIndex).getCabFare());

	}
}
