package com.serviddio.gis.rest;

import org.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {
	private String uri = "http://localhost:9925/Wgis/servRest/resource";

	public void testGet() {

		try {

			Client client = Client.create();

			WebResource webResource = client.resource(uri);

			ClientResponse response = webResource.accept("text/html").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void testPost() {

		try {
			Client client = Client.create();

			WebResource webResource = client.resource(uri);
			
			JSONObject input = new JSONObject().put("user_email","stefano@example.com").put("user_passw","password");

			
			
			System.out.println("invio: "+input.toString());
			

			// POST method
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.post(ClientResponse.class, input.toString());

			// check response status code
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			// display response
			String output = response.getEntity(String.class);
			System.out.println("Output from Server .... ");
			System.out.println(output + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new RestClient().testGet();
		new RestClient().testPost();

	}
}
