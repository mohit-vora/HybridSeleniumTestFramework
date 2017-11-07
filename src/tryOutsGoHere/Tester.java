package tryOutsGoHere;

import java.util.ArrayList;
import java.util.List;


	class Trip {
	    private String driverName;
	    private String toLocation;
	    private double cabFare;
	    public Trip (String driverName, String toLocation, double cabFare) {
	    	this.driverName = driverName;
	    	this.toLocation = toLocation;
	    	this.cabFare = cabFare;
	    }
	    // Getters and Setters
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
		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}
	    public String getDriverName(){
	    	return driverName;
	    }
	    
	}

	class Tester {
		public static void main(String[] args) {
			List<Trip> tripItems = new ArrayList<Trip>();
			tripItems.add(new Trip("Nick", "Eiffel Tower", 241.53));
			tripItems.add(new Trip("Katelyn", "Army Museum", 154.31));
			tripItems.add(new Trip("Mike", "Cathedral", 868.65));
			tripItems.add(new Trip("Shane", "Louvre", 52.60));
			
			double maxFare = tripItems.get(0).getCabFare();
			int indexOfMaxFareTrip = 0;
			
			for (int index=0;index<tripItems.size();index++){
				Trip trip = tripItems.get(index);
				int recordNo = index+1;
			    System.out.println("Trip "+recordNo+":");
			    System.out.println(trip.getDriverName());
			    System.out.println(trip.getToLocation());
			    System.out.println(trip.getCabFare());
			    System.out.println();
			    if (maxFare<trip.getCabFare()){
			    	maxFare=trip.getCabFare();
			    	indexOfMaxFareTrip=index;
			    } 
		    }
			
			System.out.println("The location with highest fare is:");
			System.out.println(tripItems.get(indexOfMaxFareTrip).getToLocation());
			System.out.println(tripItems.get(indexOfMaxFareTrip).getCabFare());
	}
}
