package HackerRankerTests;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC003_CountNoOfMatchesWhichAreDrawnIn2011 {
	
	/*
	 * The following test is to count the total drawn matches in year 2011
	 */
	
	@Test
	private void tc01_ValidateTheCountOfDrawnMatchesIn2011()
	{
		int count=0;
		System.out.println("\n\n");
		for (int i=0; i<=10; i++)
		{
			Response response = RestAssured.given()
					.when()
					.queryParam("year", 2011)
					.queryParam("team1goals", i)
					.queryParam("team2goals", i)
					.get("https://jsonmock.hackerrank.com/api/football_matches");
			
			JsonPath jsonPathEvaluator = response.jsonPath();
			int totalMatches = jsonPathEvaluator.get("total");
			System.out.println("Total matches drawn with " + i + " goals in Year " + 2011 + " are : " + totalMatches);
			count = count + totalMatches;
		}
		System.out.println("\n");
		System.out.println("Total Matches drawn are: " + count);
		System.out.println("\n");
	}	 

}
