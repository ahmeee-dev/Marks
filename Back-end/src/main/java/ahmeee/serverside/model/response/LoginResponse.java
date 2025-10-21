package ahmeee.serverside.model.response;

public class LoginResponse {
		String device_id;
		String token;
		String secret;

		public LoginResponse() {}

		public LoginResponse(String device_id, String token, String secret) {
			this.device_id = device_id;
			this.token = token;
			this.secret = secret;
		}

		public String getDevice_id() {
			return device_id;
		}
		public void setDevice_id(String device_id) {
			this.device_id = device_id;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
}
