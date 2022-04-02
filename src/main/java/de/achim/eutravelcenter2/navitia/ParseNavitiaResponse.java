package de.achim.eutravelcenter2.navitia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public class ParseNavitiaResponse {
	
	
	public static Map<String, String> parseCoordRegionsResponse(JsonNode root) {
		Map<String, String> resultMap = new HashMap<>();
		String regionid = "";
		JsonNode regions =root.findPath("regions");
		if(regions instanceof ArrayNode) {
			ArrayNode arrNode = (ArrayNode) regions;
			JsonNode jn = arrNode.get(0);
			regionid = jn.textValue();
			
		}
		String coord =root.at("/address/id").textValue();
		resultMap.put("regionid", regionid);
		resultMap.put("coordinates", coord);
		
		return resultMap;
	
	}
	
	public static List<Map> parseConnectionsResponse(JsonNode root) {
		JsonNode journeyNode = root.findPath("journeys");
		List<Map> resultList = findArrivalDateTime(journeyNode);
		return resultList;
	}
	
	public static Map<String, String> parseCoordResponse(JsonNode root) {
		Map<String, String> resultMap = new HashMap<>();
		String lat =root.findPath("coord").findPath("lat").textValue();
		String lon =root.findPath("coord").findPath("lon").textValue();
		resultMap.put("longitude", lon);
		resultMap.put("latitude", lat);
		return resultMap;
	}
	
	private static List<Map> findArrivalDateTime(JsonNode node) {

		List<Map> resultList = new ArrayList<>();
		if (node instanceof ArrayNode) {		
			ArrayNode arrayNode = (ArrayNode) node;
			Iterator<JsonNode> nodeIterator = arrayNode.iterator();	
			while(nodeIterator.hasNext()) {
				JsonNode nextNode = nodeIterator.next();
				Map<String, String> map = new HashMap<>();
				processNode("", nextNode, map);
				resultList.add(map);
//				System.out.println("--" +map.get("arrival_date_time"));
//				System.out.println("--" +map.get("departure_date_time"));
//				System.out.println("--" +map.get("sections-from-stop_area-name"));
//				System.out.println("--" +map.get("sections-to-stop_area-name"));

			}
		}
		return resultList;
	}
	
	
	private static void processNode(String currentPath, JsonNode jsonNode, Map<String, String> map) {
		if (jsonNode.isObject()) {
			ObjectNode objectNode = (ObjectNode) jsonNode;
			Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
			String pathPrefix = currentPath.isEmpty() ? "" : currentPath + "-";

			while (iter.hasNext()) {
				Map.Entry<String, JsonNode> entry = iter.next();
				processNode(pathPrefix + entry.getKey(), entry.getValue(), map);
			}
		} else if (jsonNode.isArray()) {
			ArrayNode arrayNode = (ArrayNode) jsonNode;
			Iterator<JsonNode> nodeIterator = arrayNode.iterator();	
			while(nodeIterator.hasNext()) {
				JsonNode node = nodeIterator.next();
				processNode(currentPath, node, map);
			}

		} else if (jsonNode.isValueNode()) {
			ValueNode valueNode = (ValueNode) jsonNode;
			map.put(currentPath, valueNode.asText());
		}
	}

}
