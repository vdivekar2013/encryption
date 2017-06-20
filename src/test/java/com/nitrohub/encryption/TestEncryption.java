package com.nitrohub.encryption;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEncryption {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Encryptor encryptor = (Encryptor) context.getBean("encryption");
		String encryptedString = encryptor.encrypt("Hello, World", "c4e", "c4e_encrypt");
		System.out.println("Encrypted String is " + encryptedString);
	}

}
