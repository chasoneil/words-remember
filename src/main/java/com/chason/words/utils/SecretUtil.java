package com.chason.words.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class SecretUtil {

    private static final String KEY_PUB = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALbdSGnvxFAjKWfkCtyUKjvf+L4XZZ7B60jj7nOWb9ljtqy3B8PDkYCM3U3Mhv09ShY0qLM9XBl/MEi4jrYAFWMCAwEAAQ==";

    private static final String KEY_PRI = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAtt1Iae/EUCMpZ+QK3JQqO9/4vhdlnsHrSOPuc5Zv2WO2rLcHw8ORgIzdTcyG/T1KFjSosz1cGX8wSLiOtgAVYwIDAQABAkBxe2v4Ckr7LIcCqTuwXebD15/HYD+5QUDhsEoHw4FQF4og2D8hSIsOlbJ/q5uxjYzEpztEh4eydbngnOs+sj25AiEA8zMKgL1dL6o0zGAIhFkiIbb6KAmYcuz6eHKo+aQ9Pz8CIQDAfUQD4+PFYFTmRoj6oi9MPHu+ofXBPGWFWLi2ynyE3QIgH4FbOPZPHh+km11NfSe23jtACPPFRg4hHH1uGnkGXi0CIEFZxf+0HR5jqC9N/y9oZLpzRmfgQLlMMgNlrcCRWlkZAiEA1GivQ3RH3x5DI0yZWJ6rVGTnhoBuIiouUqTB+74isCs=";

    private static final int MAX_ENCRYPT_BLOCK = 117;

    private static final int MAX_DECRYPT_BLOCK = 128;

    public static String encrypt(String no, String password) {
        try {

            String enStr = no + ":" + password;
            byte[] decoded = Base64.decodeBase64(KEY_PUB);

            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] strBytes = enStr.getBytes("UTF-8");
            int inputLength = strBytes.length;

            int offSet = 0;
            byte[] resultBytes = {};
            byte[] cache;
            while (inputLength - offSet > 0) {
                if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(strBytes, offSet, MAX_ENCRYPT_BLOCK);
                    offSet += MAX_ENCRYPT_BLOCK;
                } else {
                    cache = cipher.doFinal(strBytes, offSet, inputLength - offSet);
                    offSet = inputLength;
                }
                resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
                System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
            }
            return Base64.encodeBase64String(resultBytes);
        }catch (Exception e){
            System.out.println("rsaEncrypt error:" + e.getMessage());
        }
        return "";
    }


    public static String decrypt(String no, String password) throws Exception {
        byte[] inputByte = Base64.decodeBase64(password.getBytes("UTF-8"));
        byte[] decoded = Base64.decodeBase64(KEY_PRI);

        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int inputLen = inputByte.length;
        int offset = 0;
        byte[] cache;
        int i = 0;

        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(inputByte, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(inputByte, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    public static String password (String no, String password) {

        String pwd = null;
        try {
            pwd = decrypt(no, password);

            if (!StringUtil.isEmpty(pwd)) {
                pwd = pwd.split(":")[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwd;
    }

    /**
     * create rsa public and private key
     * not using for standard code
     *
     * @throws Exception
     */
    public static void getRandomKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(512,new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));

        System.out.println(publicKeyString);
        System.out.println("-------------");
        System.out.println(privateKeyString);
    }

}
