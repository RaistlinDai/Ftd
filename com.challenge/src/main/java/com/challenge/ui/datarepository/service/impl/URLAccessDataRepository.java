package com.challenge.ui.datarepository.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.ui.datarepository.api.IDataAccessable;
import com.challenge.ui.datarepository.dao.MobileFoodFacilityDataObject;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Forrest Dai
 */
public class URLAccessDataRepository implements IDataAccessable {

	private static final Logger logger = LoggerFactory.getLogger(URLAccessDataRepository.class);
	
	private static final String HTTP_URL = "https://data.sfgov.org/api/id/rqzj-sfat.json?$query=select*%2C%20%3Aid%20limit%20100";
	
	@Override
	public List<MobileFoodFacilityDataObject> getData() {
			
		logger.info("getData invoke");
		List<MobileFoodFacilityDataObject> listFacility = new ArrayList<MobileFoodFacilityDataObject> ();
		HttpURLConnection con = null;
		
		try {
			URL url = new URL(HTTP_URL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
				
			// Build parameters
//			StringBuilder param = new StringBuilder();
//			if (!StringUtils.isEmpty(queryParam)) {
//				param.append(URLEncoder.encode("query", "UTF-8"));
//				param.append("=");
//				param.append(URLEncoder.encode(queryParam, "UTF-8"));
//			}
//			String sst = param.toString().replace("+", "");
//			logger.info("Parameters is: {}", sst);
//			con.setDoOutput(true);
//			DataOutputStream out = new DataOutputStream(con.getOutputStream());
//			out.writeBytes(queryParam);
//			out.flush();
//			out.close();
//			
			// Get the response code
			logger.info("Connection {} returns back {}", HTTP_URL, con.getResponseCode());
			
			// Read the response body
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
			  sb.append(output);
			}
			br.close();

			logger.info("Connection response");

			// Parse json to list
			ObjectMapper mapper = new ObjectMapper();
			MobileFoodFacilityDataObject[] objs = mapper.readValue(sb.toString(), MobileFoodFacilityDataObject[].class);
			listFacility = Arrays.asList(objs);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			// Close the connection
			if (con != null)
				con.disconnect();
			logger.info("getData exit");
		}
			
		return listFacility;
	}

}
