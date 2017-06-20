package com.nitrohub.encryption;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Decryptor {
	public static String decrypt(String inputBase64String, String keyAlias, String keystorePass) throws Exception {
		InputStream keystoreStream = new FileInputStream("aes-keystore,jck"); 
		KeyStore keystore = KeyStore.getInstance("JCEKS"); 
		keystore.load(keystoreStream, keystorePass.toCharArray()); 
		if (!keystore.containsAlias(keyAlias)) { 
		 throw new RuntimeException("Alias for key not found"); 
		} 
		Key key = keystore.getKey(keyAlias, keystorePass.toCharArray());
		SecretKeySpec secretKeySpecification = new SecretKeySpec(key.getEncoded(), "AES"); 
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpecification); 
		byte[] encryptedTextBytes = Base64.getDecoder().decode(inputBase64String);
		byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes); 
		String outputString = new String(decryptedTextBytes);
		return outputString;
	}
}
