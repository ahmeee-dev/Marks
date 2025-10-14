package ahmeee.serverside.utils;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Signature {
	
	public static String generateSignature(String secret, String canonicalString) {
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKey keySpec = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
			mac.init(keySpec);

			byte[] rawHmac = mac.doFinal(canonicalString.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(rawHmac);
		} catch (Exception err) {throw new RuntimeException();  }
	}
}
