package de.achim.eutravelcenter2.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

public class BahnUtilsTest {
	
	@Test
	public void testFormatXYCoordinates() {
		
		String coord = BahnUtils.formatXYCoordinates("43.7764860000");
		String expectedResult = "43776486";
		assertEquals(expectedResult, coord);
	}

	@Test
	public void testMakeBahnDatum() throws ParseException {
		
		String testDate = "28/02/2022";
		String expectedResult = "Mo, 28.02.2022"	;
		String testResult = BahnUtils.makeBahnDatum(testDate);
		assertEquals(expectedResult, testResult);
	}

}
