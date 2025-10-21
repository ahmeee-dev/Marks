
import { Button } from "react-native"
import { View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { getStoredValue, setStoredValue } from "@/utils/secureStore";
import { useEffect, useState } from "react";
import { useRouter } from "expo-router";
import { TextInput } from "react-native";

export default function loginPage() {

	const router = useRouter();
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");

	async function login(username: string, password: string) {
		if (username == "" || password == "")
			return;
		const response = await fetch('http://192.168.1.11:8080/login', {
			method: 'POST',
			headers: {
				"Content-type": "application/json"
			},
			body: JSON.stringify({username, password, device_id: "iphone"}),
		})
		//this part will work as soon as the server answer is formatted to have  device_id, token and secret
		//.then((response) => response.json())
		//.then((data) => { setStoredValue("username", username)
		//				setStoredValue("device_id", data.device_id)
		//				setStoredValue("token", data.token)
		//				setStoredValue("secret", data.secret)
		//})
		//.catch((error) => console.error(error));
		const text = await response.text()
		console.log(text);
		router.replace('/interrogation');
	}

	return (
		//implementa tutto
		<SafeAreaView>
			<TextInput value={username} onChangeText={setUsername} placeholder="Username"/>
			<TextInput value={password} onChangeText={setPassword} placeholder="Password" secureTextEntry/>
			<Button title="login" onPress={()=> login(username, password)}/>
			<Button title="register" onPress={()=> router.replace('/register')}></Button>
		</SafeAreaView>
	)
}