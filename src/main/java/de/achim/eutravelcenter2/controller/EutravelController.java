package de.achim.eutravelcenter2.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import de.achim.eutravelcenter2.dao.ConnectionRequestDAO;
import de.achim.eutravelcenter2.dao.ConnectionResponseDAO;
import de.achim.eutravelcenter2.dao.StationDAO;
import de.achim.eutravelcenter2.dbahn.BahnRequestService;
import de.achim.eutravelcenter2.navitia.NavitiaService;
import de.achim.eutravelcenter2.repository.StationDAORepository;
import de.achim.eutravelcenter2.utils.BahnUtils;
import de.achim.eutravelcenter2.utils.HMKeys;
import de.achim.eutravelcenter2.navitia.ParseNavitiaResponse;

@RestController
@RequestMapping(path="/eutravel")
public class EutravelController {

	@Autowired
	private StationDAORepository sdr;

	@Autowired
	private BahnRequestService brs;

	@Autowired
	private NavitiaService navitiaService;

	@GetMapping(path="/all")
	public @ResponseBody Iterable<StationDAO> getAllStations(){
		return sdr.findAll();
	}

	@RequestMapping(value ="/stationbyname", produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody Iterable<StationDAO> getStationList(
			@RequestParam String name) {
		System.out.println(name);
		return sdr.findByNameQuery(name);

	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value ="/stationsearch", produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody Iterable<String> getStationListSearch(
			@RequestParam String name) {
		System.out.println(name);
		Iterable<StationDAO> stationDaoList = sdr.findByNameQuery(name);

		List<String> stationNameList = new ArrayList<>();
		stationDaoList.forEach(sdl -> stationNameList.add(sdl.getName()));

		return stationNameList;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/connections")
	public @ResponseBody List<ConnectionResponseDAO> requestConnection(@RequestBody ConnectionRequestDAO connections){
		List<Map<String, String>> resultMap = new ArrayList<>();
		List<ConnectionResponseDAO> resultList = new ArrayList<>();
		System.out.println(connections.getStartStation());
		System.out.println(connections.getDestinationStation());
		System.out.println(connections.getTravelStartDate());
		System.out.println(connections.getTravelStartTime());
		try {
			StationDAO startStationDAO = sdr.findByNameQuery(connections.getStartStation()).iterator().next();
			StationDAO destinationStationDAO = sdr.findByNameQuery(connections.getDestinationStation()).iterator().next();

			String startTravelDate = BahnUtils.makeBahnDatum(connections.getTravelStartDate());
			String startTravelTime = connections.getTravelStartTime();
			long requestTimeAsUnixTS = BahnUtils.getUnixTimeStamp();
			//TODO: are coordinates present? if not call findCoordinatesForStation()
			String startX =  BahnUtils.formatXYCoordinates(startStationDAO.getLongitude());
			String startY =  BahnUtils.formatXYCoordinates(startStationDAO.getLatitude());
			String startStationName = startStationDAO.getName();
			//TODO: start station located in France or Germany
			// if not germany us navitia 
			//remember for navitia service use region of destination for coverage

			//check format: 008011160
			String startStationID = startStationDAO.getUic();
			String destinationX =  BahnUtils.formatXYCoordinates(destinationStationDAO.getLongitude());
			String destinationY =  BahnUtils.formatXYCoordinates(destinationStationDAO.getLatitude());
			String destinationStationID = destinationStationDAO.getUic();
			String destinationStationName = destinationStationDAO.getName();
			//tarifclass and number of travellers not supportet by navitia
			//tariffClass 2 = tariffTravellerType_1="E"??
			String tariffClass = connections.getTariffClass();		
			String numberOfTravellers = connections.getNumberOfTravellers();
			//connect to db service4
			if(startStationDAO.getCountry().equalsIgnoreCase("de")) {
				resultList = brs.getConnectionsFromDeutschBahn(startStationName, startX, startY, startStationID, 
						destinationStationName, destinationX, destinationY, destinationStationID, 
						requestTimeAsUnixTS, startTravelTime, startTravelDate, numberOfTravellers, tariffClass);

//				for(String link: connenctionLinks) {
//					ConnectionResponseDAO responseDAO = new ConnectionResponseDAO(startStationName, destinationStationName, 
//							startTravelDate, startTravelTime, "00:00",  numberOfTravellers, tariffClass, link);
//					resultList.add(responseDAO);
//				}
				return resultList;
			}	

			//navitia calling
			String fromCoordinates = "";
			String toCoordinates = "";
			String regionid = "";
			if(startX != null && destinationX != null ) {
				fromCoordinates = startStationDAO.getLongitude()+ ";" + startStationDAO.getLatitude();
				toCoordinates = destinationStationDAO.getLongitude()+ ";" + destinationStationDAO.getLatitude();
				Map<String, String> destData = findRegionForStationFromCoordinates(destinationStationDAO.getLongitude(), destinationStationDAO.getLatitude());
				regionid = destData.get(HMKeys.REGIONID.getValue());
			}
			else {
				Map<String, String> fromStationData = findRegionForStation(connections.getStartStation());
				fromCoordinates = fromStationData.get(HMKeys.COORDINATES.getValue());
				Map<String, String> toStationData =  findRegionForStation(connections.getDestinationStation());
				toCoordinates = toStationData.get(HMKeys.COORDINATES.getValue());
				regionid = toStationData.get(HMKeys.REGIONID.getValue());
			}
			String navitiaTravelDate =makeNavitiaDateTimeString(connections.getTravelStartDate(), connections.getTravelStartTime());

			List<Map<String, String>> navitiaConnections = getNavitiaConnections(fromCoordinates, toCoordinates, navitiaTravelDate, regionid);
			for(Map<String, String> navitiaMap : navitiaConnections) {
				
				ConnectionResponseDAO responseDAO = new ConnectionResponseDAO(startStationName, destinationStationName, 
						startTravelDate,startTravelTime, navitiaMap.get(HMKeys.ARRIVAL_DATE_TIME.getValue()),"n.a",
						numberOfTravellers, tariffClass, "n.a",  "n.a");
				resultList.add(responseDAO);
			}
			return resultList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}



	@RequestMapping(path="/coordinates", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> getStationCooridnates(@RequestParam String name) throws Exception{
		System.out.println("search name" + name);
		Map<String, String> map = findCoordinatesForStation(name);
		return map;
	}


	@RequestMapping(path="/region", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> getRegionForStation(@RequestParam String name) throws Exception {		
		Map<String, String> parseRegionsResponse = findRegionForStation(name);
		return parseRegionsResponse;
	}

	public List<Map<String, String>> getNavitiaConnections(String fromCoordinates, String toCoordinates, String dateTime,String regionid) throws Exception {	
		JsonNode journeysroot = navitiaService.findConnections(fromCoordinates, toCoordinates, dateTime, regionid);
		List<Map<String, String>> parseConnectionsResponse = ParseNavitiaResponse.parseConnectionsResponse(journeysroot);
		return parseConnectionsResponse;
	}

	private Map<String, String> findCoordinatesForStation(String name) throws Exception, JsonProcessingException {
		JsonNode root = navitiaService.findCoordinatesForStation(name);
		Map<String, String>map = ParseNavitiaResponse.parseCoordResponse(root);
		return map;
	}

	private Map<String, String> findRegionForStation(String name) throws Exception {
		Map<String, String> map = findCoordinatesForStation(name);
		JsonNode regionroot = navitiaService.findRegions(map.get("latitude"), map.get("longitude"));
		Map<String, String> parseRegionsResponse = ParseNavitiaResponse.parseCoordRegionsResponse(regionroot);

		return parseRegionsResponse;
	}
	
	private Map<String, String> findRegionForStationFromCoordinates(String longitude, String latidude) throws Exception {
		
		JsonNode regionroot = navitiaService.findRegions(latidude, longitude);
		Map<String, String> parseRegionsResponse = ParseNavitiaResponse.parseCoordRegionsResponse(regionroot);

		return parseRegionsResponse;
	}

	private String makeNavitiaDateTimeString(String travelStartDate, String travelStartTime) throws Exception {
		String input = travelStartDate + " " + travelStartTime;
		SimpleDateFormat sourceSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = sourceSdf.parse(input);
		SimpleDateFormat targetSdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String intermedateDateString = targetSdf.format(date);
		String s1 = intermedateDateString.substring(0,8);
		String s2 = intermedateDateString.substring(8,intermedateDateString.length());
		return s1 + "T" +s2;
	}

}
