package w.test.httpClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class GITHubRestTest {

	@Test
	public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
	      throws ClientProtocolException, IOException{
	   // Given
	   String name = "adsfasdffasd";
	   HttpUriRequest request = new HttpGet( "https://api.github.com/users/" + name );
	 
	   // When
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	 
	   // Then
	   assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
	}
	
	@Test
	public void
	 givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
	 throws ClientProtocolException, IOException{
	   // Given
	   HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );
	 
	   // When
	   HttpResponse response = HttpClientBuilder.create().build().execute( request );
	 
	   // Then
	   String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
	   
	   assertThat(mimeType, equalTo("application/json"));
	}
	

	
	@Test
	public void
	  givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect()
	  throws ClientProtocolException, IOException{
	    // Given
	    HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );
	 
	    // When
	    HttpResponse response = HttpClientBuilder.create().build().execute( request );
	 
	    // Then
	    GitHubUser resource = RetrieveUtil.retrieveResourceFromResponse(response, GitHubUser.class);
	    assertThat( resource.getLogin() , equalTo("eugenp") );
	    assertThat( resource.getName() , equalTo("Eugen") );
	}
}
