package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestCalendars {

	public final String BASE_URL="http://localhost:8080/MakeMyMeeting-Web/api";

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
	public void testCrearCalendars() {

		String url = BASE_URL + "/calendars";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Escolar");
		jsonObject.put("idUser",1);
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Municipal");
		jsonObject.put("idUser",5);
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Juegos");
		jsonObject.put("idUser",1);
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Trabajo");
		jsonObject.put("idUser",9);
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Entrevistas");
		jsonObject.put("idUser",1);
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Viajes");
		jsonObject.put("idUser",13);
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Museos");
		jsonObject.put("idUser",15);
		jsonString = jsonObject.toString();

		webResource = client.resource(url);
		response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test(dependsOnMethods= {"testCrearCalendars"})
	public void testGetCalendar() {

		String token =getToken();
		String url = BASE_URL + "/calendars/24";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearCalendars"})
	public void testGetCalendars() {

		String token =getToken();
		String url = BASE_URL + "/calendars";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);

		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test(dependsOnMethods= {"testCrearCalendars"})
	public void testUpdateCalendar() {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("name","Escolares");
		String jsonString = jsonObject.toString();

		String token =getToken();
		String url = BASE_URL + "/calendars/24";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token+"").type("application/json").put(ClientResponse.class,jsonString);

		System.out.println("\nPUT "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test(dependsOnMethods= {"testCrearCalendars"})
	public void testDeleteCalendar() {

		String token =getToken();
		String url = BASE_URL + "/calendars/28";
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
