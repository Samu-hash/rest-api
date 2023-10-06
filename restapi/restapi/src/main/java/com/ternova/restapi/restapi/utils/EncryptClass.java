package com.ternova.restapi.restapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class EncryptClass {

    @Value("${security.encrypt.key}")
    private String ENCRYPT_KEY="tu_clave_secreta";

    @Value("${security.encrypt.encrypt-mode}")
    private String ENCRYPT_MODE="AES";

    public String encrypt(String text) throws Exception {
        Key key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), ENCRYPT_MODE);

        Cipher cipher = Cipher.getInstance(ENCRYPT_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encrypted){
        try{
            byte[] encryptedBytes = Base64.getDecoder().decode(encrypted.replace("\n", ""));
            Key key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), ENCRYPT_MODE);

            Cipher cipher = Cipher.getInstance(ENCRYPT_MODE);
            cipher.init(Cipher.DECRYPT_MODE, key);

            return new String(cipher.doFinal(encryptedBytes));
        }catch (Exception e){
            return "";
        }
    }
}
