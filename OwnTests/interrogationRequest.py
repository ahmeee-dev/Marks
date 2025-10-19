import hmac
import hashlib
import json
import time
import requests

def main():
    # === Input dei parametri ===
    method = "POST"  # fisso, come richiesto
    path = input("Inserisci il path (es. /api/v1/test): ").strip()
    username = input("Inserisci lo username: ").strip()
    device_id = input("Inserisci il device ID: ").strip()
    secret = input("Inserisci il secret: ").strip()
    body_input = input("Inserisci il body in formato JSON (oppure lascia vuoto): ").strip()

    # === Preparazione del body ===
    if body_input:
        try:
            body_json = json.loads(body_input)
            body = json.dumps(body_json, separators=(',', ':'))  # JSON compatto
        except json.JSONDecodeError:
            print("⚠️  Il body non è un JSON valido. Verrà usato come stringa grezza.")
            body = body_input
    else:
        body = ""

    # === Generazione del timestamp ===
    timestamp = str(int(time.time()))

    # === Costruzione della canonical string ===
    canonical_string = f"{method}\n{path}\n{username}\n{device_id}\n{timestamp}\n{body}"

    # === Calcolo della firma HMAC-SHA256 ===
    signature = hmac.new(
        secret.encode("utf-8"),
        canonical_string.encode("utf-8"),
        hashlib.sha256
    ).hexdigest()

    # === Costruzione degli header ===
    headers = {
        "X-Signature": signature,
        "X-DeviceId": device_id,
        "X-Username": username,
        "X-Timestamp": timestamp,
        "Content-Type": "application/json"
    }

    # === Invio della richiesta POST ===
    url = f"http://localhost:8080{path}"
    print("\nInvio della richiesta a:", url)
    print("Headers:", json.dumps(headers, indent=4))
    print("Body:", body)

    try:
        response = requests.post(url, headers=headers, data=body)
        print("\n=== Risposta del server ===")
        print("Status:", response.status_code)
        print("Body:", response.text)
    except requests.exceptions.RequestException as e:
        print("❌ Errore nella richiesta:", e)

if __name__ == "__main__":
    main()
