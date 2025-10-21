import { setStoredValue, getStoredValue } from "@/utils/secureStore";
import { Button } from "react-native";
import { useState } from "react";
import { TextInput } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";


export default function registerPage() {

//genera un device ID

	const [email, setEmail] = useState("");
	const [useranem, setUsername] = useState("");
	const [password, setPassword] = useState("");
	const [name, setName] = useState("");
	function registerUser(email: string, username: string, password: string, name: string) {
		if (email == "" || username == "" || password == "" || name == "")
			return;
		fetch('http://192.168.1.11:8080/register', {
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

	return (
		<SafeAreaView>
			<TextInput placeholder="usename" onChangeText={setUsername}/>
			<TextInput placeholder="email" onChangeText={setEmail} keyboardType="email-address"/>
			<TextInput placeholder="password" onChangeText={setPassword} secureTextEntry/>
			<Button title="register" onPress={()=> registerUser}/>
		</SafeAreaView>
	)


}