package w.test.restAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class GitHubSimpleTest {

	@Test
	public void testGetSingleUser() {
		RestAssured.expect().
	    	statusCode(200).
	    	contentType("application/json").
	    	body("login", equalTo("eugenp"), "name", equalTo("Eugen")).
	    when().
	    get("https://api.github.com/users/eugenp");
	}
	
	@Test
	public void testGetSingleUserWithJsonPath() {
	  Response res = RestAssured.get("https://api.github.com/users/eugenp");
	  assertThat(res.getStatusCode(), is(200));
	  
	  JsonPath jp = new JsonPath(res.asString());
	  assertThat(jp.getString("login"), equalTo("eugenp"));
	  assertThat(jp.getString("name"), equalTo("Eugen"));	  
	}
}
