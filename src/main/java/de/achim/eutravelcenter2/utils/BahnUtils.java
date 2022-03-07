package de.achim.eutravelcenter2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BahnUtils {

	public static String makeBahnDatum(String dateString) throws ParseException {
		//parse the input
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.GERMAN);
		Date myDate = sdf.parse(dateString);

		//prepare to format output
		sdf.applyPattern("dd.MM.yyyy");
		String resultDate = sdf.format(myDate);

		sdf.applyPattern("EE");
		String dayOfWeek = sdf.format(myDate);

		return dayOfWeek.substring(0, dayOfWeek.length()-1) + ", " +resultDate;
	}

	public static long getUnixTimeStamp() {
		Date today = Calendar.getInstance().getTime();
		return today.getTime();
	}

	//43.7764860000	7.5043490000
	//X=13369549@Y=52525589@U=80@L=008011160
	public static String formatXYCoordinates(String coord) {
		String result = "";
		coord = coord.replace(".","");
		int coordlength = coord.length();
		if(coordlength <= 8) {
			result = coord.substring(0, coordlength);
		}
		else {
			result = coord.substring(0, 8);
		}
		return result;
	}
	
	
	public static Elements parseReiseauskuftResponse(Document doc) {
		/*
		 * tbody id="overview_upateC0-4"
		 * tbody id="overview_updateC0-5"		 
		 */

		String searchexpr = "tbody[id~=^overview_updateC0-[0-9]?]";
		Elements results = doc.select(searchexpr);
		return results;

	}

	public static List<String> findDetailsLinks(Elements els) {

		/*
		 *  <tr class="buttonLine">
	            <td data-concol="1">
	            <a class="floatLeft iconLink open" id="linkDtlC0-4" href="https://reiseauskunft.bahn.de/bin/query.exe/dn?ld=4399&amp;protocol=https:&amp;seqnr=1&amp;ident=7i.02888599.1645435080&amp;rt=1&amp;rememberSortType=minDeparture&amp;" rel="HWAI:CONNECTION{C0-4}[id=C0-4][HwaiConId=C0-4][HwaiDetailStatus=details];~CONNECTION{C0-4}[HwaiMapStatus][HwaiMapNumber][HwaiMapSlider][HwaiDetailHimMessage]" title="Details einblenden"><span><span></span></span>Details einblenden</a></td>
	            <td class="time" data-concol="3"></td>
	            <td colspan="3" class="return lastrow button
		 */
		List<String> dataList = new ArrayList<>();
		for (Element el : els) {
			Element result = el.select("a").first();
			String data = result.attr("href");
			dataList.add(data);
		}
		return dataList;

	}

	public static List<String> getDataList(Elements els) {
		List<String> dataList = new ArrayList<>();
		for (Element result : els) {
			Element sibling = getPreviousSibling(result);
			dataList.add(getDatafromSibling(sibling));
		}
		return dataList;
	}

	public static String getDatafromSibling(Element el) {
		return el.data();
	}

	public static Element getPreviousSibling(Element el) {
		return el.previousElementSibling();
	}
}
