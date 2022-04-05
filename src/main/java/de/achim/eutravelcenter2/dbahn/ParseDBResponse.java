package de.achim.eutravelcenter2.dbahn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ParseDBResponse {

	public ParseDBResponse() {

	}

	public Element findresultsOverviewContainer(Document doc) {

		String searchid = "resultsOverviewContainer";
		Element results = doc.getElementById(searchid);
		return results;

	}

	public Elements findOverviewUpdate(Element doc) {

		String searchexpr = "div[id~=^overview_updateC0-[0-9]?]";
		Elements results = doc.select(searchexpr);
		return results;

	}

	//----------------------------find times ----------------------------------------------


	public List<Map<String, String>> findConnectionDataElements(Elements ovcList) {
		List<Map<String, String>> resultMapList = new ArrayList<>();
		for(Element ovc: ovcList) {
			Map<String, String> resultMap = new HashMap<>();
			Elements cdList = ovc.select("div.connectionData");
			Elements cpList = ovc.select("div.connectionPrice");
			findFarePepElements(cpList, resultMap);
			findConnectionTimeElements(cdList, resultMap);

			resultMapList.add(resultMap);
		}
		return resultMapList;

	}

	private void findConnectionTimeElements(Elements cdList, Map<String, String> map) {
		for(Element cd: cdList) {
			Elements timeList = cd.select("div.connectiontime");
			findTimeElements(timeList, map);
		}
	}

	private void findTimeElements(Elements timeList, Map<String, String> map) {

		for(Element el :timeList) {
			Elements elTimeDep = el.select("div.time.timeDep");
			Elements elTimeArr = el.select("div.time.timeArr");
			Elements elTimeDur= el.select("div.duration");
			map.put("elTimeDep", fromFirstDigit(elTimeDep.text()));
			map.put("elTimeArr", fromFirstDigit(elTimeArr.text()));
			map.put("elTimeDur", fromFirstDigit(elTimeDur.text()));

		}

	}



	//----------------------------find price and link----------------------------------------------
	private void findConnectionPriceElements(Elements ovcList, Map<String, String> map) {
		for(Element ovc: ovcList) {
			Elements cdList = ovc.select("div.connectionPrice");
			findFarePepElements(cdList, map);
		}

	}


	private void findFarePepElements(Elements cdList, Map<String, String> map) {
		for(Element cd: cdList) {

			Elements fpList = cd.select("div.farePep.lastrow.button-inside.tablebutton.borderright");
			findPriceElements(fpList, map);
		}
	}

	private void findPriceElements(Elements fpList, Map<String, String> map) {

		for(Element el : fpList) {
			Element fpOutput = el.select("span.fareOutput").first();
			Element result = el.select("a").first();
			String data = result.attr("href");		
			map.put("link", data);
			map.put("fare", fpOutput.text());
		}
	}


	//------------------helper function ----------------------------------------------

	private String fromFirstDigit(String input) {	
		int i= 0;
		int ii = input.length();
		for(i= 0; i < ii; i++) {
			char c= input.charAt(i);
			if(Character.isDigit(c)) {
				break;
			}

		}
		String result = input.substring(i, input.length()).strip(); 
		return result;
	}


}
