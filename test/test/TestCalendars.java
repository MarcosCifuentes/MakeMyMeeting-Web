//package test;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//
//public class TestCalendars {
//
//	public final String BASE_URL="http://localhost:8081/MakeMyMeeting-Web/api";
//
//	public Client client = Client.create();
//
//
//	@Test
//	public void testCrearCalendars() {
//
//		String url = BASE_URL + "/calendars";
//
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode jsonObject = mapper.createObjectNode();
//		jsonObject.put("userName","pepito");
//		jsonObject.put("name","pablo");
//		jsonObject.put("lastname","perez");
//		jsonObject.put("email","pablo2017@gmail.com");
//		jsonObject.put("password","pepito123");
//		String jsonString = jsonObject.toString();
//
//		WebResource webResource = client.resource(url);
//		ClientResponse response = webResource.type("application/json")
//				.post(ClientResponse.class, jsonString);
//
//		System.out.println("\nPOST "+url);
//		System.out.println("Response Code : " + response.getStatus());
//		System.out.println("Response Content : " + response.getEntity(String.class));
//		Assert.assertEquals(response.getStatus(), 201);
//
//	}
//
//	@Test(dependsOnMethods= {"testCrearCalendars"})
//	public void testGetCalendar() {
//
//		String url = BASE_URL + "/calendars/1";
//		WebResource webResource = client.resource(url);
//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//
//		System.out.println("\nGET "+url);
//		System.out.println("Response Code : " + response.getStatus());
//		System.out.println("Response Content : " + response.getEntity(String.class));
//		Assert.assertEquals(response.getStatus(), 200);
//
//	}
//
//	@Test(dependsOnMethods= {"testCrearCalendars"})
//	public void testGetCalendars() {
//
//		String url = BASE_URL + "/calendars";
//		WebResource webResource = client.resource(url);
//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//
//		System.out.println("\nGET "+url);
//		System.out.println("Response Code : " + response.getStatus());
//		System.out.println("Response Content : " + response.getEntity(String.class));
//		Assert.assertEquals(response.getStatus(), 200);
//
//	}
//
//	@Test(dependsOnMethods= {"testCrearCalendars"})
//	public void testUpdateCalendar() {
//
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode jsonObject = mapper.createObjectNode();
//		jsonObject.put("userName","potter");
//		jsonObject.put("name","peter");
//		jsonObject.put("lastname","perez");
//		jsonObject.put("email","pablo2017@gmail.com");
//		jsonObject.put("password","pepito123");
//		String jsonString = jsonObject.toString();
//
//		String url = BASE_URL + "/calendars/1";
//		WebResource webResource = client.resource(url);
//		ClientResponse response = webResource.type("application/json").put(ClientResponse.class,jsonString);
//
//		System.out.println("\nPUT "+url);
//		System.out.println("Response Code : " + response.getStatus());
//		System.out.println("Response Content : " + response.getEntity(String.class));
//		Assert.assertEquals(response.getStatus(), 200);
//
//	}
//
//	@Test(dependsOnMethods= {"testCrearCalendars"})
//	public void testDeleteCalendar() {
//
//		String url = BASE_URL + "/calendars/2";
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
//}
