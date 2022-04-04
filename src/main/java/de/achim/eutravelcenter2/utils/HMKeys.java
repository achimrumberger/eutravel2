package de.achim.eutravelcenter2.utils;

public enum HMKeys {

	REGIONID("regionid"),
	COORDINATES("coordinates"),
	LONGITUDE("longitude"),
	LATIDUDE("latitude"),
	ARRIVAL_DATE_TIME("arrival_date_time"),
	DEPARTURE_DATE_TIME("departure_date_time"),
	SECTIONS_FROM_STOP_AREA_NAME("sections-from-stop_area-name"),
	SECTIONS_TO_STOP_AREA_NAME("sections-to-stop_area-name")
	;

	private String value;

	HMKeys(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

}
