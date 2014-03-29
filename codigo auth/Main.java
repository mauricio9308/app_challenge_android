import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.POST;

import com.google.gson.annotations.SerializedName;

public class Main {

	private static final String API_URL = "http://0.0.0.0:9000";

	public interface LogginService {
		@POST("/user/login")
		void login(Callback<Message> cb);
	}

	public static void main(String[] args) {

		ApiRequestInterceptor requestInterceptor = new ApiRequestInterceptor(
				"04002800", "passwor");

		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(API_URL).setRequestInterceptor(requestInterceptor)
				.build();

		// Create an instance of our GitHub API interface.
		LogginService github = restAdapter.create(LogginService.class);

		// Fetch and print a list of the contributors to this library.
		github.login(new Callback<Message>() {
			
			@Override
			public void success(Message message, Response arg1) {
				System.out.println(message);
			}
			
			@Override
			public void failure(RetrofitError error) {
				if (error.getResponse() != null) {
			        RestError body = (RestError) error.getBodyAs(RestError.class);
			        System.out.println(body);
			    }
			}
		});

	}

	class RestError {
		
	    @SerializedName("error")
	    public String errorDetails;
	    
	    public String getErrorDetails() {
			return errorDetails;
		}
	    
	    public void setErrorDetails(String errorDetails) {
			this.errorDetails = errorDetails;
		}
	    
	    @Override
	    public String toString() {
	    	return errorDetails;
	    }
	    
	    
	}
	

}
