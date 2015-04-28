package edu.neu.vcare.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.neu.vcare.entity.WebInitiative;

public class Parser {

	
	public WebInitiative[] fetchWebInitiative(){
		String allStr = "https://api.globalgiving.org/api/public/projectservice/featured/projects?api_key=546e592a-b794-4257-acdb-9a33ec99e0ea";
		try
		{
			URL url = new URL(allStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			InputStream in = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(isr);
			String out;
			StringBuffer jasonp = new StringBuffer();
			while((out = reader.readLine()) != null){
				jasonp.append(out);
			}
			//WebInitiative blog = new WebInitiative();
			Parser p = new Parser();
			
			int index= (jasonp.toString()).indexOf("[");
			int indexEnd= (jasonp.toString()).lastIndexOf("]");
			//String proj = (jasonp.toString()).substring(index + 1, indexEnd);

			String projectsJson = (jasonp.toString()).substring(index, indexEnd + 1);
			
			WebInitiative[] blogs = p.createBlogs(projectsJson);
			return blogs;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	/*private WebInitiative createBlogObject(String jason){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jason, WebInitiative.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	
	private WebInitiative[] createBlogs(String jason){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jason, WebInitiative[].class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}