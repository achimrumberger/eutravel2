package de.achim.eutravelcenter2.controller;

import java.util.ArrayList;
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
import de.achim.eutravelcenter2.dao.StationDAO;
import de.achim.eutravelcenter2.dbahn.BahnRequestService;
import de.achim.eutravelcenter2.navitia.NavitiaService;
import de.achim.eutravelcenter2.repository.StationDAORepository;
import de.achim.eutravelcenter2.utils.BahnUtils;
import de.achim.eutravelcenter2.navitia.ParseNavitiaResponse;

@RestController
@RequestMapping(path="/eutravel")
public class EutravelController {

	@Autowired
	private StationDAORepository sdr;
	
	@Autowired
	private BahnRequestService brs;
	
	@Autowired
	private NavitiaService service;

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
	public List<String> requestConnection(@RequestBody ConnectionRequestDAO connections){
		List<String> connenctionLinks = new ArrayList<>();
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
			String startX =  BahnUtils.formatXYCoordinates(startStationDAO.getLongitude());
			String startY =  BahnUtils.formatXYCoordinates(startStationDAO.getLatitude());
			String startStationName = startStationDAO.getName();
			//check format: 008011160
			String startStationID = startStationDAO.getUic();
			String destinationX =  BahnUtils.formatXYCoordinates(destinationStationDAO.getLongitude());
			String destinationY =  BahnUtils.formatXYCoordinates(destinationStationDAO.getLatitude());
			String destinationStationID = destinationStationDAO.getUic();
			String destinationStationName = destinationStationDAO.getName();
			//tariffClass 2 = tariffTravellerType_1="E"??
			String tariffClass = connections.getTariffClass();		
			String numberOfTravellers = connections.getNumberOfTravellers();
			connenctionLinks = brs.getConnectionsFromDeutschBahn(startStationName, startX, startY, startStationID, 
					destinationStationName, destinationX, destinationY, destinationStationID, 
					requestTimeAsUnixTS, startTravelTime, startTravelDate, numberOfTravellers, tariffClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connenctionLinks;
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
	
	private Map<String, String> findCoordinatesForStation(String name) throws Exception, JsonProcessingException {
		JsonNode root = service.findCoordinatesForStation(name);
		Map<String, String>map = ParseNavitiaResponse.parseCoordResponse(root);
		return map;
	}
	
	private Map<String, String> findRegionForStation(String name) throws Exception {
		Map<String, String> map = findCoordinatesForStation(name);
		JsonNode regionroot = service.findRegions(map.get("latitude"), map.get("longitude"));
		Map<String, String> parseRegionsResponse = ParseNavitiaResponse.parseCoordRegionsResponse(regionroot);
		
		return parseRegionsResponse;
	}

}
