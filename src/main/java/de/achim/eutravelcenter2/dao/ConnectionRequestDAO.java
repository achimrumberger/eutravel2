package de.achim.eutravelcenter2.dao;


public class ConnectionRequestDAO {
	
	private String startStation;
	
	private String destinationStation;
	
	private String travelStartTime;
	
	private String travelStartDate;
	
	private String tariffClass;
	
	private String numberOfTravellers;
	
	public ConnectionRequestDAO(
			String startStation,
			String destinationStation,
			String travelStartTime,
			String travelStartDate,
			String tariffClass,		
			String numberOfTravellers
			) {
		this.startStation = startStation;
		this.destinationStation = destinationStation;
		this.travelStartTime = travelStartTime;
		this.travelStartDate = travelStartDate;
		this.tariffClass = tariffClass;		
		this.numberOfTravellers = numberOfTravellers;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public String getTravelStartTime() {
		return travelStartTime;
	}

	public void setTravelStartTime(String travelStartTime) {
		this.travelStartTime = travelStartTime;
	}

	public String getTravelStartDate() {
		return travelStartDate;
	}

	public void setTravelStartDate(String travelStartDate) {
		this.travelStartDate = travelStartDate;
	}

	public String getTariffClass() {
		return tariffClass;
	}

	public void setTariffClass(String tariffClass) {
		this.tariffClass = tariffClass;
	}

	public String getNumberOfTravellers() {
		return numberOfTravellers;
	}

	public void setNumberOfTravellers(String numberOfTravellers) {
		this.numberOfTravellers = numberOfTravellers;
	}

}
