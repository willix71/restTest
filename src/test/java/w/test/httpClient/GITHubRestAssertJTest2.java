package w.test.httpClient;


import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.assertj.core.api.Condition;
import org.junit.Test;

public class GITHubRestAssertJTest2 {

	@Test
	public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
	      throws ClientProtocolException, IOException{
	   // Given
	   String name = "adsfasdffasd";
	   HttpUriRequest request = new HttpGet( "https://api.github.com/users/" + name );
	 
	   // When
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	 
	   // Then
	   assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
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
	   
	   assertThat(mimeType).isEqualTo("application/json");
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
	    assertThat( resource.getLogin()).isEqualTo("eugenp") ;
	    assertThat( resource.getName()).isEqualTo("Eugen") ;
	    
	    Condition<GitHubUser> expecting = new Condition<GitHubUser>(){
	    	  @Override
	    	  public boolean matches(GitHubUser value) {
	    	    return "eugenp".equals(resource.getLogin());
	    	  }
	    };
	    assertThat( resource ).is(expecting);
	    
	    assertThat(Arrays.asList(resource)).extracting("login","name").contains(tuple("eugenp","Eugen"));
	}
}
