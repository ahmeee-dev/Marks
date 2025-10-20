import hmac
import hashlib
import base64
import json
import time
import requests

# === Parametri fissi della richiesta ===
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

# === JSON compatto come Java ===
body = json.dumps(body_json, separators=(',', ':'))

# === Timestamp in secondi ===
timestamp = str(int(time.time()))

# === Canonical string identica al server Java ===
canonical_string = f"POST\n{path}\n{username}\n{device_id}\n{timestamp}\n{body}"

print("canonical: " + canonical_string)

# === Calcolo HMAC-SHA256 + Base64 (come Java) ===
raw_hmac = hmac.new(secret.encode("utf-8"), canonical_string.encode("utf-8"), hashlib.sha256).digest()
signature = base64.b64encode(raw_hmac).decode()

# === Headers della richiesta ===
headers = {
    "X-Signature": signature,
    "X-DeviceId": device_id,
    "X-Username": username,
    "X-Timestamp": timestamp,
    "Content-Type": "application/json"
}

# === URL e invio richiesta POST ===
url = f"http://localhost:8080{path}"
print("Invio della richiesta a:", url)
print("Headers:", json.dumps(headers, indent=4))
print("Body:", body)
print(f"\n\n\nSecret: '{secret}'")
print(f"Secret length: {len(secret)}")
print(f"Secret bytes: {secret.encode('utf-8')}")
print(f"Secret hex: {secret.encode('utf-8').hex()}")
try:
    response = requests.post(url, headers=headers, data=body)
    print("\n=== Risposta del server ===")
    print("Status:", response.status_code)
    print("Body:", response.text)
except requests.exceptions.RequestException as e:
    print("❌ Errore nella richiesta:", e)
