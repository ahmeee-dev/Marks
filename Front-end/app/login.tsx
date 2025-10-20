
import { Button } from "react-native"
import { View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { getStoredValue, setStoredValue } from "@/utils/secureStore";
import { useEffect } from "react";
import { useRouter } from "expo-router";

export default function loginPage() {

	const router = useRouter();

	function login() {
		//invia i dati del form, se positivo fai replace con la schermata interrogation, altrimenti errore e si riscrivono le credenziali
	}


	function sendToRegistrationPage() {
		router.replace('/register');
	}


	return (
		//implementa tutto
		<SafeAreaView>
			<Button title="register" onPress={sendToRegistrationPage}></Button>
		</SafeAreaView>
	)
}