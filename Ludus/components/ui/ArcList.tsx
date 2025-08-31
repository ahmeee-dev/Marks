import React from 'react';
import { FlatList, Text, View, Button } from 'react-native';
import { StyleSheet } from 'react-native';

const DATA = [{ 
		id: "4",
		name: "sufficiente",
		range: "6-7"},
	{
		id: "3",
		name: "buono",
		range: "7-8"},
	{
		id: "2",
		name: "ottimo",
		range: "8-9"},
	{
		id: "1",
		name: "perfetto",
		range: "9-10"}];

export default function ArcList() {
	return (
		<View style={styleSheet.ButtonList}>
			{DATA.map((item) => ( <Button title={item.name} key={item.id}/>))}
		</View>
	)
}

const styleSheet = StyleSheet.create({
	ButtonList:{
		display: "flex",
		flexDirection: "row",
		alignItems: "center",
		alignSelf: "center"
	}
}) 