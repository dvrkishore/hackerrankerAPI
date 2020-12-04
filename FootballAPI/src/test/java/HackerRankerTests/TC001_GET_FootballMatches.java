package HackerRankerTests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.DataFootballMatches;

public class TC001_GET_FootballMatches {
	
	@Test
	private void tc01_GET_footballMatches()
	{
		Response response = RestAssured.given()
							.when()
							.get("https://jsonmock.hackerrank.com/api/football_matches");
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
							.get("https://jsonmock.hackerrank.com/api/football_matches");
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
							.get("https://jsonmock.hackerrank.com/api/football_matches");
		
    	JsonPath jsonPathEvaluator = response.jsonPath();
    	
    	int page = jsonPathEvaluator.get("page");
    	System.out.println("page number received from Response " + page);
    	Assert.assertEquals(page, 1, "Correct Name received in the Response");
    	
    	int itemsperpage = jsonPathEvaluator.get("per_page");
    	System.out.println("page number received from Response " + itemsperpage);
    	Assert.assertEquals(itemsperpage, 10, "Correct page count received in the Response");
    	
    	int totalcount = jsonPathEvaluator.get("total");
    	System.out.println("page number received from Response " + totalcount);
    	Assert.assertEquals(totalcount, 11581, "Correct total count received in the Response");
    	
    	int totalpagescount = jsonPathEvaluator.get("total_pages");
    	System.out.println("page pages count received from Response " + totalpagescount);
    	Assert.assertEquals(totalpagescount, 1159, "Correct total pages count received in the Response");
	}
	
	@Test
	private void tc05_validateFirstRecordOfDataArray()
	{
		Response response = RestAssured.given()
							.when()
							.get("https://jsonmock.hackerrank.com/api/football_matches");
		
    	JsonPath jsonPathEvaluator = response.jsonPath();
    	List<DataFootballMatches>  listOfData = jsonPathEvaluator.getList("data", DataFootballMatches.class);
    	Assert.assertNotNull(listOfData);
    	DataFootballMatches data = listOfData.get(0);
    	
    	System.out.println(data.getCompetition());
    	System.out.println(data.getYear());
    	System.out.println(data.getRound());
    	System.out.println(data.getTeam1());
    	System.out.println(data.getTeam2());
    	System.out.println(data.getTeam1goals());
    	System.out.println(data.getTeam2goals());
    	
    	Assert.assertEquals(data.getCompetition(), "UEFA Champions League");
    	Assert.assertEquals(data.getYear(), 2011);
    	Assert.assertEquals(data.getRound(), "GroupH");
    	Assert.assertEquals(data.getTeam1(), "Barcelona");
    	Assert.assertEquals(data.getTeam2(), "AC Milan");
    	Assert.assertEquals(data.getTeam1goals(), 2);
    	Assert.assertEquals(data.getTeam2goals(), 2);
	}
	
}