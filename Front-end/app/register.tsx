import { setStoredValue, getStoredValue } from "@/utils/secureStore";
import { Button } from "react-native";
import { useState } from "react";
import { TextInput } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { useRouter } from "expo-router";


export default function registerPage() {

//genera un device ID

	const router = useRouter();
	const [email, setEmail] = useState("");
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");
	const [name, setName] = useState("");
	function registerUser() {
		console.log("email: " + email, "\nusername: " + username, "\npassword: " + password, "\nname: " + name);
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
						setStoredValue("device_id", JSON.stringify(data.device_id))
						setStoredValue("token", JSON.stringify(data.token))
						setStoredValue("secret", JSON.stringify(data.secret))
		})
		.then(() => router.replace('/interrogation'))
		.catch((error) => console.error(error));
	}

	return (
		<SafeAreaView>
			<TextInput placeholder="usename" onChangeText={setUsername}/>
			<TextInput placeholder="name" onChangeText={setName}/>
			<TextInput placeholder="email" onChangeText={setEmail} keyboardType="email-address"/>
			<TextInput placeholder="password" onChangeText={setPassword} secureTextEntry/>
			<Button title="register" onPress={registerUser}/>
		</SafeAreaView>
	)


}