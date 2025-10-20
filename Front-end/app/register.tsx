import { setStoredValue, getStoredValue } from "@/utils/secureStore";


export default function registerPage() {

//genera un device ID
	function registerUser(email: string, username: string, password: string, name: string) {
		fetch('http://localhost:8080/login', {
			method: 'POST',
			headers: {
				'Content-type': 'application/json',
			},
			body: JSON.stringify({email, username, password, name}),
		})
		.then((response) => response.json())
		.then((data) => { setStoredValue("username", data.username)
						setStoredValue("device_id", data.device_id)
						setStoredValue("token", data.token)
						setStoredValue("secret", data.secret)
		})
		.catch((error) => console.error(error));
	}

	//implementa UX/UI


}