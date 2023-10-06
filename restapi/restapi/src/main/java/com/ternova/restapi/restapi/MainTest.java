package com.ternova.restapi.restapi;

import com.ternova.restapi.restapi.utils.EncryptClass;

public class MainTest {

    public static void main(String[] args) throws Exception {

        EncryptClass ec = new EncryptClass();

        System.out.println("---url");
        String enc = ec.encrypt("jdbc:mysql://localhost:3306/db_usuarios_1");
        System.out.println(enc);

        String dec = ec.decrypt(enc);
        System.out.println(dec);

        System.out.println("---usuario");
        String enc1 = ec.encrypt("root");
        System.out.println(enc1);

        String dec1 = ec.decrypt(enc1);
        System.out.println(dec1);

        System.out.println("-----clave");
        String enc2 = ec.encrypt("");
        System.out.println(enc2);

        String dec2 = ec.decrypt(enc2);
        System.out.println(dec2);

        System.out.println("----drive");
        String enc3 = ec.encrypt("com.mysql.cj.jdbc.Driver");
        System.out.println(enc3);

        String dec3 = ec.decrypt(enc3);
        System.out.println(dec3);
    }
}
