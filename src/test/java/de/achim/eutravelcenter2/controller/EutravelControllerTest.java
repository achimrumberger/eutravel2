package de.achim.eutravelcenter2.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.achim.eutravelcenter2.dao.ConnectionRequestDAO;
import de.achim.eutravelcenter2.dao.StationDAO;
import de.achim.eutravelcenter2.dbahn.BahnRequestService;
import de.achim.eutravelcenter2.repository.StationDAORepository;

@WebMvcTest
public class EutravelControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BahnRequestService brs;
	
	@MockBean
	private StationDAORepository sdr;

	@Test
	public void testGetStationList() throws Exception {
		StationDAO sd = new StationDAO();
		sd.setId(6778);
		sd.setName("Achim");
		sd.setSlug("achim");
		sd.setUic("8013746");
		sd.setLatitude("53.015986");
		sd.setLongitude("9.030446");
		sd.setCountry("DE");
		sd.setCountry_time_zone("Europe/Berlin");
		
		List<StationDAO> list = new ArrayList<>();
		list.add(sd);
		when(sdr.findByNameQuery(anyString())).thenReturn(list);
		
		this.mockMvc.perform(get("/eutravel/stationbyname?name=Achim")) 
		.andDo(print()) 
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Achim")));
	}

	@Test
	public void testRequestConnection() throws Exception {
		ConnectionRequestDAO connectionDAO = new ConnectionRequestDAO("Kirchheim (Teck)", "Stuttgart Hbf", "12:00", "03/06/2022", "2", "1");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(connectionDAO );	
		
		
		StationDAO sd = new StationDAO();
		sd.setId(6778);
		sd.setName("Achim");
		sd.setSlug("achim");
		sd.setUic("8013746");
		sd.setLatitude("53.015986");
		sd.setLongitude("9.030446");
		sd.setCountry("DE");
		sd.setCountry_time_zone("Europe/Berlin");
		
		List<StationDAO> list = new ArrayList<>();
		list.add(sd);
		when(sdr.findByNameQuery(anyString())).thenReturn(list);

		List<String> result = new ArrayList<>();
		result.add("https://reiseauskunft.bahn.de/bin/query.exe/dn?ld=4395&protocol=https:&seqnr=1&ident=jt.02265895.1646239655&rt=1&rememberSortType=minDeparture&");
		when(brs.getConnectionsFromDeutschBahn(anyString(), anyString(), anyString(), 
				anyString(), anyString(), anyString(), anyString(), anyString(), anyLong(), 
				anyString(), anyString(), anyString(), anyString()))
		.thenReturn(result);
		
		this.mockMvc.perform(post("/eutravel/connections")
				.contentType(APPLICATION_JSON_UTF8)
				.content(requestJson))
		.andDo(print()) 
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("reiseauskunft")));

	}

}
