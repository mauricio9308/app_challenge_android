import org.apache.commons.codec.binary.Base64;

import retrofit.RequestInterceptor;

public class ApiRequestInterceptor implements RequestInterceptor {

		private String username;
		private String password;

		public ApiRequestInterceptor(String username, String password) {
			this.username = username;
			this.password = password;
		}

		@Override
		public void intercept(RequestFacade requestFacade) {
			final String authorizationValue = encodeCredentialsForBasicAuthorization();
			requestFacade.addHeader("Authorization", authorizationValue);
		}

		private String encodeCredentialsForBasicAuthorization() {
			final String userAndPassword = username + ":" + password;
			byte[] byteArray = Base64.encodeBase64(userAndPassword.getBytes());
			return "Basic " + new String(byteArray);
		}

	}