package de.achim.eutravelcenter2.dao;

public class ConnectionResponseDAO {
	
	private String startStation;
	private String stopStation;
	private String departureDate;
	private String departimeTime;
	private String arrivalTime;
	private String travelDuration;
	private String numberOfTravellers;
	private String tarifClass;
	private String link;
	private String fare;
	
	public ConnectionResponseDAO() {
	}

	/**
	 * 
	 * @param startStation
	 * @param stopStation
	 * @param departureDate
	 * @param departumeTime
	 * @param numberOfTravellers
	 * @param tarifClass
	 * @param link
	 */
	public ConnectionResponseDAO(String startStation, String stopStation, String departureDate, 
			String departimeTime, String arrivalTime, String travelDuration,
			String numberOfTravellers, String tarifClass, String link, String fare) {
		super();
		this.startStation = startStation;
		this.stopStation = stopStation;
		this.departureDate = departureDate;
		this.departimeTime = departimeTime;
		this.travelDuration = travelDuration;
		this.arrivalTime = arrivalTime;
		this.numberOfTravellers = numberOfTravellers;
		this.tarifClass = tarifClass;
		this.link = link;
		this.fare = fare;
	}
	
	

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public void setStopStation(String stopStation) {
		this.stopStation = stopStation;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public void setDepartimeTime(String departimeTime) {
		this.departimeTime = departimeTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setNumberOfTravellers(String numberOfTravellers) {
		this.numberOfTravellers = numberOfTravellers;
	}

	public void setTarifClass(String tarifClass) {
		this.tarifClass = tarifClass;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getStartStation() {
		return startStation;
	}

	public String getStopStation() {
		return stopStation;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public String getDepartumeTime() {
		return departimeTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getNumberOfTravellers() {
		return numberOfTravellers;
	}

	public String getTarifClass() {
		return tarifClass;
	}

	public String getLink() {
		return link;
	}

	public String getFare() {
		return fare;
	}

	public String getTravelDuration() {
		return travelDuration;
	}

	public void setTravelDuration(String travelDuration) {
		this.travelDuration = travelDuration;
	}
	

}
