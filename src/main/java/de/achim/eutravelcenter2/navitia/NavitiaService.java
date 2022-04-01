package de.achim.eutravelcenter2.navitia;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NavitiaService {

	@Value( "${nativia.authtoken}" )
	private String authToken;

	private final RestTemplate restTemplate;

	public NavitiaService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public JsonNode findCoordinatesForStation(String stationName) throws Exception, JsonProcessingException {
		//https://api.navitia.io/v1/places?q=Gare de Lyon&
		//https://api.navitia.io/v1/coverage/fr-idf/places?q=Gare de Lyon&
		String nativaURL = "https://api.navitia.io/v1/places?q="+ stationName;
		JsonNode root = getResponseFromNativia(nativaURL);
		return root;
	}



	public JsonNode findConnections(Map fromCoordinates, Map toCoordinates, String dateTime,String regionid) throws Exception, JsonProcessingException {
		//https://api.navitia.io/v1/coverage/fr-se/journeys?from=2.37083185;48.840163306&to=2.879779;42.696292&datetime=20220331T150000&count=4&
		String nativaURL = "/https://api.navitia.io/v1/coverage/" 
				+ regionid 
				+ "/journeys?from=" + fromCoordinates.get("longitude") + ";" + fromCoordinates.get("latitude") 
				+ "&to="+ toCoordinates.get("longitude") + ";" + toCoordinates.get("latitude")
				+ "&datetime="+dateTime
				+ "&count=4&";

		JsonNode root = getResponseFromNativia(nativaURL);
		return root;

	}

	public JsonNode findRegions(String latitute, String longitude) throws Exception, JsonProcessingException {
		//https://api.navitia.io/v1/coord/2.879779;42.696292
		String nativaURL = "https://api.navitia.io/v1/coord/"+ longitude + ";" + latitute;
		JsonNode root = getResponseFromNativia(nativaURL);
		return root;

	}


	private JsonNode getResponseFromNativia(String nativaURL) throws JsonProcessingException, JsonMappingException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		//		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response
		= restTemplate.exchange(nativaURL,HttpMethod.GET, entity ,String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		return root;
	}

}
