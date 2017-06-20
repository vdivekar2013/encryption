package com.nitrohub.encryption;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {
	public static String encrypt(String inputString, String keyAlias, String keystorePass) throws Exception {
		InputStream keystoreStream = new FileInputStream("D:\\workspace\\nitrohub-security-encryption\\src\\main\\resources\\aes-keystore.jck"); 
		KeyStore keystore = KeyStore.getInstance("JCEKS"); 
		keystore.load(keystoreStream, keystorePass.toCharArray()); 
		if (!keystore.containsAlias(keyAlias)) { 
		 throw new RuntimeException("Alias for key not found"); 
		} 
		Key key = keystore.getKey(keyAlias, keystorePass.toCharArray());
		SecretKeySpec secretKeySpecification = new SecretKeySpec(key.getEncoded(), "AES"); 
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpecification,new IvParameterSpec("C4E".getBytes("UTF-8"))); 
		byte[] encryptedMessageInBytes = cipher.doFinal(inputString.getBytes("UTF-8"));
		String base64EncodedEncryptedMsg = Base64.getEncoder().encodeToString(encryptedMessageInBytes);
		return base64EncodedEncryptedMsg;
	}
}
