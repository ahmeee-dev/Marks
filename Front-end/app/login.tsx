
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
		if (!username || !password) return;
	
		try {
			const response = await fetch('http://192.168.1.11:8080/login', {
				method: 'POST',
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify({ username, password, device_id: "iphone" }),
			});
	
			const data = await response.json();
	
			await setStoredValue("username", username);
			await setStoredValue("device_id", String(data.data.device_id));
			await setStoredValue("token", String(data.data.token));
			await setStoredValue("secret", String(data.data.secret));
	
			console.log(JSON.stringify(data));
			router.replace('/interrogation');
		} catch (error) {
			console.error(error);
		}
	}
	

	return (
		<SafeAreaView>
			<TextInput value={username} onChangeText={setUsername} placeholder="Username"/>
			<TextInput value={password} onChangeText={setPassword} placeholder="Password" secureTextEntry/>
			<Button title="login" onPress={()=> login(username, password)}/>
			<Button title="register" onPress={()=> router.replace('/register')}></Button>
		</SafeAreaView>
	)
}