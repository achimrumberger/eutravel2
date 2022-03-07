package de.achim.eutravelcenter2.dbahn;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class BahnRequestTemplate {
	
	private BahnRequestDAO data;
	
//	private BahnRequestDAO data = new BahnRequestDAO("https://reiseauskunft.bahn.de", "/bin/query.exe/dn?", "ge.01704183", "yes", "1", "DEU", "L01_S01_D001_qf-bahn-svb-kl2_lz03", "1", "https:", "1", "Berlin Hbf", 
//			"A=1@O=Berlin Hbf@X=13369549@Y=52525589@U=80@L=008011160@B=1p=1638831949@", "1", "PARIS", "A=1@O=PARIS@X=2333681@Y=48861496@U=80@L=008796001@B=1p=1638831949@", "Fr, 18.02.2022", "13:54",
//			"depart", "", "", "depart", "0", "1", "E", "0", "2", "DB-HYBRID", "yes", "JS!js=yes!ajax=yes!", "yes", "JS!js=yes!ajax=yes!#hfsseq1|ml.0840683.1645188855");

	public BahnRequestTemplate(BahnRequestDAO data) {
		this.data = data;
	}

	private long getUnixTimeStamp() {
		Date today = Calendar.getInstance().getTime();
		return today.getTime();
	}

	private String makeSearchTermURLEncoded (String searchTerm) throws Exception {
		return URLEncoder.encode(searchTerm, StandardCharsets.UTF_8.toString());
	}
	
	
	public BahnRequestDAO getData() {
		return data;
	}

	public void setData(BahnRequestDAO data) {
		this.data = data;
	}

	public Map<String, String> makeDataMap() {
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put(data.revia, this.getData().getRevia());
		dataMap.put("existOptimizePrice-deactivated", this.getData().getExistOptimizePrice_deactivated());
		dataMap.put("country", this.getData().getCountry());
		dataMap.put("dbkanal_007", this.getData().getDbkanal_007());
		dataMap.put("start", this.getData().getStart());
		dataMap.put("protocol",this.getData().getProtocol());
		dataMap.put("REQ0JourneyStopsS0A",this.getData().getREQ0JourneyStopsS0A());
		dataMap.put("S",this.getData().getS());
		dataMap.put("REQ0JourneyStopsSID",this.getData().getREQ0JourneyStopsSID());
		dataMap.put("REQ0JourneyStopsZ0A",this.getData().getREQ0JourneyStopsZ0A());
		dataMap.put("Z",this.getData().getZ());
		dataMap.put("REQ0JourneyStopsZID",this.getData().getREQ0JourneyStopsZID());
		dataMap.put("date",this.getData().getDate());
		dataMap.put("time",this.getData().getTime());
		dataMap.put("timesel",this.getData().getTimesel());
		dataMap.put("returnDate",this.getData().getReturnDate());
		dataMap.put("returnTime",this.getData().getReturnTime());
		dataMap.put("returnTimesel",this.getData().getReturnTimesel());
		dataMap.put("optimize",this.getData().getOptimize());
		dataMap.put("auskunft_travelers_number",this.getData().getAuskunft_travelers_number());
		dataMap.put("tariffTravellerType.1",this.getData().getTariffTravellerType_1());
		dataMap.put("tariffTravellerReductionClass.1",this.getData().getTariffTravellerReductionClass_1());
		dataMap.put("tariffClass",this.getData().getTariffClass());
		dataMap.put("rtMode",this.getData().getRtMode());
		dataMap.put("externRequest",this.getData().getExternRequest_1());
		dataMap.put("HWAI",this.getData().getHWAI_1());
		dataMap.put("externRequest",this.getData().getExternRequest_2());
		dataMap.put("HWAI",this.getData().getHWAI_1()+this.getData().getHWAI_2());
		return dataMap;
	}

	public String makeRequestString() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getData().getTargetURL()).append(this.getData().getQueryStringStart());
		sb.append("revia=").append(this.makeSearchTermURLEncoded(this.getData().getRevia()));
		sb.append("&existOptimizePrice-deactivated=").append(this.makeSearchTermURLEncoded(this.getData().getExistOptimizePrice_deactivated()));
		sb.append("&country=").append(this.makeSearchTermURLEncoded(this.getData().getCountry()));
		sb.append("&dbkanal_007=").append(this.makeSearchTermURLEncoded(this.getData().getDbkanal_007()));
		sb.append("&start=").append(this.makeSearchTermURLEncoded(this.getData().getStart()));
		sb.append("&protocol=").append(this.makeSearchTermURLEncoded(this.getData().getProtocol()));
		sb.append("&REQ0JourneyStopsS0A=").append(this.makeSearchTermURLEncoded(this.getData().getREQ0JourneyStopsS0A()));
		sb.append("&S=").append(this.makeSearchTermURLEncoded(this.getData().getS()));
		sb.append("&REQ0JourneyStopsSID=").append(this.makeSearchTermURLEncoded(this.getData().getREQ0JourneyStopsSID()));
		sb.append("&REQ0JourneyStopsZ0A=").append(this.makeSearchTermURLEncoded(this.getData().getREQ0JourneyStopsZ0A()));
		sb.append("&Z=").append(this.makeSearchTermURLEncoded(this.getData().getZ()));
		sb.append("&REQ0JourneyStopsZID=").append(this.makeSearchTermURLEncoded(this.getData().getREQ0JourneyStopsZID()));
		sb.append("&date=").append(this.makeSearchTermURLEncoded(this.getData().getDate()));
		sb.append("&time=").append(this.makeSearchTermURLEncoded(this.getData().getTime()));
		sb.append("&timesel=").append(this.makeSearchTermURLEncoded(this.getData().getTimesel()));
		sb.append("&returnDate=").append(this.makeSearchTermURLEncoded(this.getData().getReturnDate()));
		sb.append("&returnTime=").append(this.makeSearchTermURLEncoded(this.getData().getReturnTime()));
		sb.append("&returnTimesel=").append(this.makeSearchTermURLEncoded(this.getData().getReturnTimesel()));
		sb.append("&optimize=").append(this.makeSearchTermURLEncoded(this.getData().getOptimize()));
		sb.append("&auskunft_travelers_number=").append(this.makeSearchTermURLEncoded(this.getData().getAuskunft_travelers_number()));
		sb.append("&tariffTravellerType.1=").append(this.makeSearchTermURLEncoded(this.getData().getTariffTravellerType_1()));
		sb.append("&tariffTravellerReductionClass.1=").append(this.makeSearchTermURLEncoded(this.getData().getTariffTravellerReductionClass_1()));
		sb.append("&tariffClass=").append(this.makeSearchTermURLEncoded(this.getData().getTariffClass()));
		sb.append("&rtMode=").append(this.makeSearchTermURLEncoded(this.getData().getRtMode()));
		sb.append("&externRequest=").append(this.makeSearchTermURLEncoded(this.getData().getExternRequest_1()));
		sb.append("&HWAI=").append(this.makeSearchTermURLEncoded(this.getData().getHWAI_1()));
		sb.append("&externRequest=").append(this.makeSearchTermURLEncoded(this.getData().getExternRequest_2()));
		sb.append("&HWAI=").append(this.makeSearchTermURLEncoded(this.getData().getHWAI_1())).append(this.getData().getHWAI_2());

		return sb.toString();
	}

}
