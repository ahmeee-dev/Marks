import { View, Text } from "react-native";
import { getStoredValue } from "@/utils/secureStore";
import { useEffect, useState } from "react";
import { SafeAreaView } from "react-native-safe-area-context";


export default function Interrogation() {
	const [token, setToken] = useState<string | null>(null);
	const [username, setUsername] = useState<string | null>(null);
	const [device_id, setDevice_id] = useState<string | null>(null);
	const [secret, setSecret] = useState<string | null>(null);

	useEffect(() => {
		async function loadValues() {
			setToken(await getStoredValue("token"));
			setUsername(await getStoredValue("username"));
			setDevice_id(await getStoredValue("device_id"));
			setSecret(await getStoredValue("secret"));
		}
		loadValues();
		// TODO: ADD:  || login unsuccessful
		if (token == null || username == null || device_id == null || secret == null) {
			// login || register
		}
	}, []);
	return (
		<SafeAreaView>
			<Text>{token}</Text>
			<Text>{username}</Text>
			<Text>{device_id}</Text>
			<Text>{secret}</Text>
		</SafeAreaView>
	);
}




/*
path = "/start_interrogation"
username = "ahmeee"
device_id = "iphone"
secret = "50fd29a7-4ba7-45f4-af5f-a3d9cab3acffebd0fbc9-628e-4a05-883c-9172ab9d3b8c"

body_json = {
    "text_attributes": {
 		"argument": "Il Romanticismo europeo e italiano",
        "synthesis": "Lo studente ha spiegato l'origine del Romanticismo in Germania, citando Goethe e Schiller, e ha accennato alla diffusione del movimento in Italia.",
        "trimmed": "…dove gli autori cercarono di conciliare l'idealismo romantico con la tradizione classica italiana, mostrando un interesse crescente per il sentimento e la soggettività individuale",
        "content": "In Italia il Romanticismo si sviluppò in un contesto politico e culturale particolare, segnato dal desiderio di indipendenza nazionale e dal confronto con l'eredità del Neoclassicismo. Manzoni, ad esempio, rappresenta una figura centrale: nelle sue opere, come 'I Promessi Sposi', combina l'attenzione al vero storico con un profondo senso morale e religioso. Leopardi, invece, pur condividendo alcuni temi romantici come l'infinito e la tensione verso l'assoluto, ne offre una visione più pessimistica, riflettendo sulla condizione umana e sull'inevitabile infelicità dell'uomo moderno. Anche Foscolo può essere letto come autore di transizione, poiché nelle sue opere emergono tanto elementi neoclassici quanto anticipazioni romantiche, soprattutto nel modo in cui esprime il legame tra memoria, patria e sentimento individuale. Successivamente il duca"
    },
    "grade_attributes": {
        "grade": 7,
        "quantity": 3
    },
    "settings": {
        "difficulty": 2
    }
}
	*/