package HackerRankerTests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.DataFootballCompetitions;

public class TC002_GET_FootballCompetitions {
	
	@Test
	private void tc02_GET_footballCompetitions()
	{
		Response response = RestAssured.given()
							.when()
							.get("https://jsonmock.hackerrank.com/api/football_competitions");
    	response.prettyPrint();
    	System.out.println("\n\n");
	}
	
	@Test
	private void tc02_StatusCodeValidation()
	{
		Response response =  RestAssured.given()
							.expect()
							.statusCode(200)
							.when()
							.get("https://jsonmock.hackerrank.com/api/football_competitions");
    	response.prettyPrint();
	}
	
	@Test
	private void tc03_validateHeaders()
	{
		Response response =  RestAssured.given()
							.expect()
							.contentType("application/json; charset=utf-8")
							.when()
							.get("https://jsonmock.hackerrank.com/api/football_matches");
    	response.prettyPrint();
	}
	
	@Test
	private void tc04_validatetopdata()
	{
		Response response = RestAssured.given()
							.when()
							.get("https://jsonmock.hackerrank.com/api/football_competitions");
		
    	JsonPath jsonPathEvaluator = response.jsonPath();
    	
    	int page = jsonPathEvaluator.get("page");
    	System.out.println("page number received from Response " + page);
    	Assert.assertEquals(page, 1, "Correct Name received in the Response");
    	
    	int itemsperpage = jsonPathEvaluator.get("per_page");
    	System.out.println("page number received from Response " + itemsperpage);
    	Assert.assertEquals(itemsperpage, 10, "Correct page count received in the Response");
    	
    	int totalcount = jsonPathEvaluator.get("total");
    	System.out.println("page number received from Response " + totalcount);
    	Assert.assertEquals(totalcount, 35, "Correct total count received in the Response");
    	
    	int totalpagescount = jsonPathEvaluator.get("total_pages");
    	System.out.println("page pages count received from Response " + totalpagescount);
    	Assert.assertEquals(totalpagescount, 4, "Correct total pages count received in the Response");
	}
	
	@Test
	private void tc05_validateFirstRecordOfDataArray()
	{
		Response response = RestAssured.given()
							.when()
							.get("https://jsonmock.hackerrank.com/api/football_competitions");
		
    	JsonPath jsonPathEvaluator = response.jsonPath();
    	List<DataFootballCompetitions>  listOfData = jsonPathEvaluator.getList("data", DataFootballCompetitions.class);
    	Assert.assertNotNull(listOfData);
    	DataFootballCompetitions data = listOfData.get(0);
    	
    	System.out.println(data.getName());
    	System.out.println(data.getCountry());
    	System.out.println(data.getYear());
    	System.out.println(data.getWinner());
    	System.out.println(data.getRunnerup());
    	
    	Assert.assertEquals(data.getName(), "UEFA Champions League");
    	Assert.assertEquals(data.getCountry(), "");
    	Assert.assertEquals(data.getYear(), 2011);
    	Assert.assertEquals(data.getWinner(), "Chelsea");
    	Assert.assertEquals(data.getRunnerup(), "Bayern Munich");
	}
	
}