package de.achim.eutravelcenter2.dbahn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import de.achim.eutravelcenter2.dao.ConnectionResponseDAO;
import de.achim.eutravelcenter2.utils.BahnUtils;
import de.achim.eutravelcenter2.utils.DiagnosticConnection;

@Component
public class BahnRequestService {

	private static final String BIN_QUERY_EXE_DN = "bin/query.exe/dn?";
	private static final String MOZILLA_5_0 = "Mozilla/5.0";
	private static final String HTTPS_REISEAUSKUNFT_BAHN_DE = "https://reiseauskunft.bahn.de/";
	private static final String IDENT = "ident";
	
	@Autowired
	ParseDBResponse pdbr;

	public List<ConnectionResponseDAO> getConnectionsFromDeutschBahn(
			String startStation, String startX, String startY, String startStationID, 
			String destinationStation, String destinationX, String destinationY, String destinationStationID,
			long requestTimeAsUnixTS,
			String startTime,
			String startDate,
			String numberOfTravellers,
			String tariffClassOfTraveller) throws Exception {

		/**
		 *  new BahnRequestDAO("https://reiseauskunft.bahn.de", "/bin/query.exe/dn?", "ge.01704183", "yes", "1", "DEU", "L01_S01_D001_qf-bahn-svb-kl2_lz03", "1", "https:", "1", "Berlin Hbf", 
			"A=1@O=Berlin Hbf@X=13369549@Y=52525589@U=80@L=008011160@B=1p=1638831949@", "1", "PARIS", "A=1@O=PARIS@X=2333681@Y=48861496@U=80@L=008796001@B=1p=1638831949@", "Fr, 18.02.2022", "13:54",
			"depart", "", "", "depart", "0", "1", "E", "0", "2", "DB-HYBRID", "yes", "JS!js=yes!ajax=yes!", "yes", "JS!js=yes!ajax=yes!#hfsseq1|ml.0840683.1645188855");

		 */

		List<String> resultList = new ArrayList<>();
		
		String targetURL = HTTPS_REISEAUSKUNFT_BAHN_DE;
		String queryStringStart = BIN_QUERY_EXE_DN;
		String identCookie = getIdentCookie();
		String revia = "yes";
		String existOptimizePrice_deactivated = "1";
		String country = "DEU";
		String dbkanal_007 = "L01_S01_D001_qf-bahn-svb-kl2_lz03";
		String start = "1";
		String protocol = "https:";
		String rEQ0JourneyStopsS0A = "1";
		String s = startStation;
		//A=1@O=Berlin Hbf@X=13369549@Y=52525589@U=80@L=008011160@B=1p=1638831949@"
		String rEQ0JourneyStopsSID = "A=1@O="+ startStation + "@X=" + startX + "@Y=" + startY +"@U=80@L=" + startStationID + "@B=1p=" + requestTimeAsUnixTS + "@"; 
		String rEQ0JourneyStopsZ0A = "1";
		String z = destinationStation;
		//A=1@O=PARIS@X=2333681@Y=48861496@U=80@L=008796001@B=1p=1638831949@"
		String rEQ0JourneyStopsZID = "A=1@O="+ destinationStation + "@X=" + destinationX + "@Y=" + destinationY +"@U=80@L=" + destinationStationID + "@B=1p=" + requestTimeAsUnixTS + "@";
		String date = startDate; 
		String time = startTime;
		String timesel = "depart";
		String returnDate= "";
		String returnTime= "";
		String returnTimesel="depart";
		String optimize = "0";
		String auskunft_travelers_number = numberOfTravellers;
		String tariffTravellerType_1 = "E";
		String tariffClass = tariffClassOfTraveller;
		String tariffTravellerReductionClass_1 = "0";
		String rtMode = "DB-HYBRID";
		String externRequest_1 = "yes";
		String hWAI_1 = "JS!js=yes!ajax=yes!";
		String externRequest_2 = "yes";
		//maybe replace | with .
		String hWAI_2 = "#hfsseq1." + identCookie;

		/*
		 * String targetURL, String queryStringStart, String identCookie, String revia,
			String existOptimizePrice_deactivated, String country, String dbkanal_007, String start, String protocol,
			String rEQ0JourneyStopsS0A, String s, String rEQ0JourneyStopsSID, String rEQ0JourneyStopsZ0A, String z,
			String rEQ0JourneyStopsZID, String date, String time, String timesel, String returnDate, String returnTime,
			String returnTimesel, String optimize, String auskunft_travelers_number, String tariffTravellerType_1,
			String tariffTravellerReductionClass_1, String tariffClass, String rtMode, String externRequest_1,
			String hWAI_1, String externRequest_2, String hWAI_2)
		 */

		BahnRequestDAO brDAO = new BahnRequestDAO(
				targetURL,queryStringStart, identCookie, revia, existOptimizePrice_deactivated, country, dbkanal_007,start, protocol, 
				rEQ0JourneyStopsS0A, s, rEQ0JourneyStopsSID,	rEQ0JourneyStopsZ0A, z, rEQ0JourneyStopsZID, 
				date, time, timesel, returnDate, returnTime, returnTimesel, 
				optimize, auskunft_travelers_number, tariffTravellerType_1, tariffTravellerReductionClass_1, tariffClass,
				rtMode, externRequest_1, hWAI_1, externRequest_2, hWAI_2
				);

		BahnRequestTemplate brt = new BahnRequestTemplate(brDAO);
		//brt.setData(brDAO);
		String requestString = brt.makeRequestString();
		System.out.println("**********************************");

		System.out.println(requestString);
		System.out.println("**********************************");

		Response response = 
				DiagnosticConnection.connect(requestString)
				.userAgent(MOZILLA_5_0)
				.timeout(10 * 1000)
				.method(Method.GET)
				.followRedirects(true)
				.execute();

		//parse response

//		Elements tbodies = BahnUtils.parseReiseauskuftResponse(response.parse());
//		//List<String> dataList = this.getDataList(tbodies);
//		resultList = BahnUtils.findDetailsLinks(tbodies);
//		
		Element overviewContainer = pdbr.findresultsOverviewContainer(response.parse());
		Elements overview_updateC0 = pdbr.findOverviewUpdate(overviewContainer);

		List<ConnectionResponseDAO> result = pdbr.findConnectionDataElements(overview_updateC0);

		return result;
	}


	private String getIdentCookie() throws Exception {

		Response resReiseauskunt =  DiagnosticConnection
				.connect(HTTPS_REISEAUSKUNFT_BAHN_DE)
				.userAgent(MOZILLA_5_0)
				.method(Method.POST)
				.execute();

		Map<String, String> resReiseauskuntCookies = resReiseauskunt.cookies();
		String identCookie = resReiseauskuntCookies.get(IDENT);
		return identCookie;
	}

	

}
