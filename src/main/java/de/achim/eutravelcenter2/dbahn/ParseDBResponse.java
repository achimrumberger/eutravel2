package de.achim.eutravelcenter2.dbahn;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import de.achim.eutravelcenter2.dao.ConnectionResponseDAO;

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


	public List<ConnectionResponseDAO> findConnectionDataElements(Elements ovcList) {
		List<ConnectionResponseDAO> resultList = new ArrayList<>();
		for(Element ovc: ovcList) {
			ConnectionResponseDAO crDAO = new ConnectionResponseDAO();
			Elements cdList = ovc.select("div.connectionData");
			Elements cpList = ovc.select("div.connectionPrice");
			findFarePepElements(cpList, crDAO);
			findConnectionTimeElements(cdList, crDAO);

			resultList.add(crDAO);
		}
		return resultList;

	}

	private void findConnectionTimeElements(Elements cdList, ConnectionResponseDAO crDAO) {
		for(Element cd: cdList) {
			Elements timeList = cd.select("div.connectiontime");
			findTimeElements(timeList, crDAO);
		}
	}

	private void findTimeElements(Elements timeList, ConnectionResponseDAO crDAO) {

		for(Element el :timeList) {
			Elements elTimeDep = el.select("div.time.timeDep");
			Elements elTimeArr = el.select("div.time.timeArr");
			Elements elTimeDur= el.select("div.duration");
			crDAO.setArrivalTime(fromFirstDigit(elTimeArr.text()));
			crDAO.setDepartimeTime(fromFirstDigit(elTimeDep.text()));
			crDAO.setTravelDuration(fromFirstDigit(elTimeDur.text()));
		}

	}



	//----------------------------find price and link----------------------------------------------
	private void findConnectionPriceElements(Elements ovcList, ConnectionResponseDAO crDAO) {
		for(Element ovc: ovcList) {
			Elements cdList = ovc.select("div.connectionPrice");
			findFarePepElements(cdList, crDAO);
		}

	}


	private void findFarePepElements(Elements cdList, ConnectionResponseDAO crDAO) {
		for(Element cd: cdList) {

			Elements fpList = cd.select("div.farePep.lastrow.button-inside.tablebutton.borderright");
			findPriceElements(fpList, crDAO);
		}
	}

	private void findPriceElements(Elements fpList,ConnectionResponseDAO crDAO) {

		for(Element el : fpList) {
			Element fpOutput = el.select("span.fareOutput").first();
			Element result = el.select("a").first();
//			String data = result.attr("href");		
			crDAO.setFare(fpOutput.text());
			crDAO.setLink("");
			
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
