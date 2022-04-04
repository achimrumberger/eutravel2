package de.achim.eutravelcenter2.dao;

public class ConnectionResponseDAO {
	
	private String startStation;
	private String stopStation;
	private String departureDate;
	private String departimeTime;
	private String arrivalTime;
	private String numberOfTravellers;
	private String tarifClass;
	private String link;
	
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
			String departimeTime, String arrivalTime,
			String numberOfTravellers, String tarifClass, String link) {
		super();
		this.startStation = startStation;
		this.stopStation = stopStation;
		this.departureDate = departureDate;
		this.departimeTime = departimeTime;
		this.arrivalTime = arrivalTime;
		this.numberOfTravellers = numberOfTravellers;
		this.tarifClass = tarifClass;
		this.link = link;
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
	
	
	

}
