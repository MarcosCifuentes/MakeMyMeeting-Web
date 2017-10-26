package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestUsers {

	public final String BASE_URL="http://localhost:8081/MakeMyMeeting-Web/api";

	public Client client = Client.create();

	private String authorization (String username, String password) {

		String url = BASE_URL + "/autentication/";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("userName",username);
		jsonObject.put("password",password);
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		String token = response.getEntity(String.class);
		System.out.println("Response Content : " + token);	
		return token;
	}

	@Test
	public void testCrearUsers() {

		String url = BASE_URL + "/users";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("userName","pepito");
		jsonObject.put("name","pablo");
		jsonObject.put("lastname","perez");
		jsonObject.put("email","pablo2017@gmail.com");
		jsonObject.put("password","pepito123");
		String jsonString = jsonObject.toString();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonString);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 201);

	}

	//	@Test(dependsOnMethods= {"testCrearUsers"})
	//	public void testGetUser() {
	//		
	//		String token = authorization("pepito","pepito123");
	//
	//		String url = BASE_URL + "/users/1";
	//		WebResource webResource = client.resource(url);
	//		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).accept("application/json").get(ClientResponse.class);
	//
	//		System.out.println("\nGET "+url);
	//		System.out.println("Response Code : " + response.getStatus());
	//		System.out.println("Response Content : " + response.getEntity(String.class));
	//		Assert.assertEquals(response.getStatus(), 200);
	//
	//	}
	//
	//	@Test(dependsOnMethods= {"testCrearUsers"})
	//	public void testGetUsers() {
	//
	//		String url = BASE_URL + "/users";
	//		WebResource webResource = client.resource(url);
	//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	//
	//		System.out.println("\nGET "+url);
	//		System.out.println("Response Code : " + response.getStatus());
	//		System.out.println("Response Content : " + response.getEntity(String.class));
	//		Assert.assertEquals(response.getStatus(), 200);
	//
	//	}

	@Test(dependsOnMethods= {"testCrearUsers"})
	public void testUpdateUser() {

		String token = authorization("pepito","pepito123");

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("userName","pepito");
		jsonObject.put("name","peter");
		jsonObject.put("lastname","perez");
		jsonObject.put("email","pablo2017@gmail.com");
		jsonObject.put("password","pepito123");
		String jsonString = jsonObject.toString();

		String url = BASE_URL + "/users/1";
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Bearer-"+token).type("application/json").put(ClientResponse.class,jsonString);

		System.out.println("\nPUT "+url);
		System.out.println("Response Code : " + response.getStatus());
		System.out.println("Response Content : " + response.getEntity(String.class));
		Assert.assertEquals(response.getStatus(), 200);

	}

	//	@Test(dependsOnMethods= {"testCrearUsers"})
	//	public void testDeleteUser() {
	//
	//		String url = BASE_URL + "/users/2";
	//		WebResource webResource = client.resource(url);
	//		ClientResponse response = webResource.delete(ClientResponse.class);
	//
	//		System.out.println("\nDELETE "+url);
	//		System.out.println("Response Code : " + response.getStatus());
	//		if(response.hasEntity())
	//			System.out.println("Response Content : " + response.getEntity(String.class));
	//		else
	//			System.out.println("Response Content : NO CONTENT");
	//		Assert.assertTrue(response.getStatus() == 204 || response.getStatus() == 404);
	//
	//	}

	//	@Test(dependsOnMethods= {"testCrearUsers"})
	//	private void testGetMeetingsByUserAndDay() {
	//		
	//		String token = authorization("pepito","pepito123");
	//		
	//		String url = BASE_URL + "/users/getMeetingsByUserAndDay?userid={userid}&date={dd-MM-yyyy,HH,mm}";
	//		WebResource webResource = client.resource(url);
	//		ClientResponse response = webResource.header("Authorization", token).accept("application/json").get(ClientResponse.class);
	//
	//		System.out.println("\nGET "+url);
	//		System.out.println("Response Code : " + response.getStatus());
	//		System.out.println("Response Content : " + response.getEntity(String.class));
	//		Assert.assertEquals(response.getStatus(), 200);
	//
	//	}
	//	
	//	@Test(dependsOnMethods= {"testCrearUsers"})
	//	private void testGetMeetingsByUserBetweenDates() {
	//		
	//		String url = BASE_URL + "/users/getMeetingsByUserBetweenDates?userid={userid}&date1={dd-MM-yyyy,HH,mm}&date2={dd-MM-yyyy,HH,mm}";
	//		WebResource webResource = client.resource(url);
	//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	//
	//		System.out.println("\nGET "+url);
	//		System.out.println("Response Code : " + response.getStatus());
	//		System.out.println("Response Content : " + response.getEntity(String.class));
	//		Assert.assertEquals(response.getStatus(), 200);
	//
	//	}
}
