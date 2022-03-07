package de.achim.eutravelcenter2.dbahn;

public class BahnRequestDAO {
	public String targetURL;
	public String queryStringStart;
	public String identCookie;
	public String revia;
	public String existOptimizePrice_deactivated;
	public String country;
	public String dbkanal_007;
	public String start;
	public String protocol;
	public String REQ0JourneyStopsS0A;
	public String S;
	public String REQ0JourneyStopsSID;
	public String REQ0JourneyStopsZ0A;
	public String Z;
	public String REQ0JourneyStopsZID;
	public String date;
	public String time;
	public String timesel;
	public String returnDate;
	public String returnTime;
	public String returnTimesel;
	public String optimize;
	public String auskunft_travelers_number;
	public String tariffTravellerType_1;
	public String tariffTravellerReductionClass_1;
	public String tariffClass;
	public String rtMode;
	public String externRequest_1;
	public String HWAI_1;
	public String externRequest_2;
	public String HWAI_2;

	/**
	 * 
	 * @param targetURL
	 * @param queryStringStart
	 * @param identCookie
	 * @param revia
	 * @param existOptimizePrice_deactivated
	 * @param country
	 * @param dbkanal_007
	 * @param start
	 * @param protocol
	 * @param rEQ0JourneyStopsS0A
	 * @param s
	 * @param rEQ0JourneyStopsSID
	 * @param rEQ0JourneyStopsZ0A
	 * @param z
	 * @param rEQ0JourneyStopsZID
	 * @param date
	 * @param time
	 * @param timesel
	 * @param returnDate
	 * @param returnTime
	 * @param returnTimesel
	 * @param optimize
	 * @param auskunft_travelers_number
	 * @param tariffTravellerType_1
	 * @param tariffTravellerReductionClass_1
	 * @param tariffClass
	 * @param rtMode
	 * @param externRequest_1
	 * @param hWAI_1
	 * @param externRequest_2
	 * @param hWAI_2
	 */
	public BahnRequestDAO(String targetURL, String queryStringStart, String identCookie, String revia,
			String existOptimizePrice_deactivated, String country, String dbkanal_007, String start, String protocol,
			String rEQ0JourneyStopsS0A, String s, String rEQ0JourneyStopsSID, String rEQ0JourneyStopsZ0A, String z,
			String rEQ0JourneyStopsZID, String date, String time, String timesel, String returnDate, String returnTime,
			String returnTimesel, String optimize, String auskunft_travelers_number, String tariffTravellerType_1,
			String tariffTravellerReductionClass_1, String tariffClass, String rtMode, String externRequest_1,
			String hWAI_1, String externRequest_2, String hWAI_2) {
		this.targetURL = targetURL;
		this.queryStringStart = queryStringStart;
		this.identCookie = identCookie;
		this.revia = revia;
		this.existOptimizePrice_deactivated = existOptimizePrice_deactivated;
		this.country = country;
		this.dbkanal_007 = dbkanal_007;
		this.start = start;
		this.protocol = protocol;
		this.REQ0JourneyStopsS0A = rEQ0JourneyStopsS0A;
		this.S = s;
		this.REQ0JourneyStopsSID = rEQ0JourneyStopsSID;
		this.REQ0JourneyStopsZ0A = rEQ0JourneyStopsZ0A;
		this.Z = z;
		this.REQ0JourneyStopsZID = rEQ0JourneyStopsZID;
		this.date = date;
		this.time = time;
		this.timesel = timesel;
		this.returnDate = returnDate;
		this.returnTime = returnTime;
		this.returnTimesel = returnTimesel;
		this.optimize = optimize;
		this.auskunft_travelers_number = auskunft_travelers_number;
		this.tariffTravellerType_1 = tariffTravellerType_1;
		this.tariffTravellerReductionClass_1 = tariffTravellerReductionClass_1;
		this.tariffClass = tariffClass;
		this.rtMode = rtMode;
		this.externRequest_1 = externRequest_1;
		this.HWAI_1 = hWAI_1;
		this.externRequest_2 = externRequest_2;
		this.HWAI_2 = hWAI_2;
	}

	public String getTargetURL() {
		return targetURL;
	}

	public String getQueryStringStart() {
		return queryStringStart;
	}

	public String getIdentCookie() {
		return identCookie;
	}

	public String getRevia() {
		return revia;
	}

	public String getExistOptimizePrice_deactivated() {
		return existOptimizePrice_deactivated;
	}

	public String getCountry() {
		return country;
	}

	public String getDbkanal_007() {
		return dbkanal_007;
	}

	public String getStart() {
		return start;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getREQ0JourneyStopsS0A() {
		return REQ0JourneyStopsS0A;
	}

	public String getS() {
		return S;
	}

	public String getREQ0JourneyStopsSID() {
		return REQ0JourneyStopsSID;
	}

	public String getREQ0JourneyStopsZ0A() {
		return REQ0JourneyStopsZ0A;
	}

	public String getZ() {
		return Z;
	}

	public String getREQ0JourneyStopsZID() {
		return REQ0JourneyStopsZID;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getTimesel() {
		return timesel;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public String getReturnTimesel() {
		return returnTimesel;
	}

	public String getOptimize() {
		return optimize;
	}

	public String getAuskunft_travelers_number() {
		return auskunft_travelers_number;
	}

	public String getTariffTravellerType_1() {
		return tariffTravellerType_1;
	}

	public String getTariffTravellerReductionClass_1() {
		return tariffTravellerReductionClass_1;
	}

	public String getTariffClass() {
		return tariffClass;
	}

	public String getRtMode() {
		return rtMode;
	}

	public String getExternRequest_1() {
		return externRequest_1;
	}

	public String getHWAI_1() {
		return HWAI_1;
	}

	public String getExternRequest_2() {
		return externRequest_2;
	}

	public String getHWAI_2() {
		return HWAI_2;
	}
	
	
}