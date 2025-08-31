import { useState } from "react";
import { View, Text, TextInput, StyleSheet, Button, TouchableWithoutFeedback, Keyboard } from "react-native";
import React from 'react';
import ScrollElements from "../components/ui/ArcList";
import ArcList from "../components/ui/ArcList";

const name = "Ludovice";

const defaultExamples = [ "La caduta di Roma",
					"Scoperta dell'America",
					"La borghesia nella Rivoluzione francese",
					"Invenzione della stampa",
					"La vita di Galileo Galilei",
					"Rinascimento italiano",
					"Prima guerra mondiale",
					"Seconda guerra mondiale",
					"La Guerra fredda",
					"Grande Depressione e New Deal"];

const texts = [ `Ciao ${name}, da dove iniziamo oggi?`,
	 		`Buongiorno ${name}, cosa vogliamo ripassare?`,
			`Ciao ${name}, quale argomento affrontiamo?`,
			`Ehi ${name}, cosa rivediamo insieme?`,
			`Buongiorno ${name}, quale lezione tocchiamo oggi?`,
			`Ciao ${name}, cosa ti va di studiare ora?`,
			`Buongiorno ${name}, quale tema apriamo?`,
			`Ehi ${name}, cosa mi spieghi oggi?`,
			`Ciao ${name}, quale parte riprendiamo?`,
			`Buongiorno ${name}, da cosa partiamo oggi?`];

export default function Index() {
	const [argument, setArgument] = useState("");
	return (
		<TouchableWithoutFeedback onPress={Keyboard.dismiss}>
		<View style={styleSheet.mainView}>
			<View>
				<Button title="Support"/>
				<Button title="Profile"/>
			</View>
			<View>
				<Text>{texts[Math.floor(Math.random() * texts.length)]}</Text>
				<TextInput onEndEditing={(e) => setArgument(e.nativeEvent.text)} 
				placeholder={`Es. + ${defaultExamples[Math.floor(Math.random() * defaultExamples.length)]}`}
				placeholderTextColor="red"
				/>
			</View>
			<View>
				<ArcList/>
				<Button title="Start"/>
			</View>
		</View>
		</TouchableWithoutFeedback>
	)}

const styleSheet = StyleSheet.create({
	mainView: {
		height: "100%",
		justifyContent: "space-evenly",
	},
})