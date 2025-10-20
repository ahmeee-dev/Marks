import * as SecureStore from "expo-secure-store";

export async function getStoredValue(key: string) {
	try {
	  const value = await SecureStore.getItemAsync(key);
	  if (value !== null) {
		console.log("Dato trovato:", value);
		return value;
	  } else {
		console.log("Nessun dato salvato per questa chiave.");
		return null;
	  }
	} catch (error) {
	  console.error("Errore durante la lettura dal SecureStore:", error);
	  return null;
	}
  }

export async function setStoredValue(key: string, value: string) {
	try {
		await SecureStore.setItemAsync(key, value);
	} catch (error) {
		console.error("Errore durante la scrittura nel SecureStore:", error);
	}
}