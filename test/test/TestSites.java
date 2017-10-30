package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestSites {

	public final String BASE_URL="http://localhost:8081/MakeMyMeeting-Web/api";

	public Client client = Client.create();

	public String getToken(){

		String url = BASE_URL + "/autentication/";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("username","pepito");
		jsonObject.put("password","pepito123");
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		String responseContent= response.getEntity(String.class);
		System.out.println("Response Content : " + responseContent);	
		return responseContent;

	}

	@Test
	public void testCrearSites() {

		String url = BASE_URL + "/sites";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Sala nro 1");
		jsonObject.put("address","san martin 154");
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Sala nro 2");
		jsonObject.put("address","pinto 959");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Sala nro 4");
		jsonObject.put("address","arenales 612");
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test(dependsOnMethods= {"testCrearSites"})
	public void testGetSite() {

		String token =getToken();
		String url = BASE_URL + "/sites/21";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearSites"})
	public void testGetSites() {

		String token =getToken();
		String url = BASE_URL + "/sites";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearSites"})
	public void testUpdateSite() {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Sala nro 3");
		jsonObject.put("address","san martin 154");
		String jsonString = jsonObject.toString();

		String token =getToken();
		String url = BASE_URL + "/sites/21";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token+"").type("application/json").put(ClientResponse.class,jsonString);

		System.out.println("\nPUT "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test(dependsOnMethods= {"testCrearSites"})
	public void testDeleteSite() {

		String token =getToken();
		String url = BASE_URL + "/sites/23";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).delete(ClientResponse.class);

		System.out.println("\nDELETE "+url);
		System.out.println("Response Code : " + response.getStatus());
		if(response.hasEntity())
			System.out.println("Response Content : " + response.getEntity(String.class));
		else
			System.out.println("Response Content : NO CONTENT");
		Assert.assertTrue(response.getStatus() == 200);

	}
}
