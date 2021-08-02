package com.jwt.study.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * 암호화 / 복호화 모듈
 */
@Slf4j
public class AES256 {

    private String iv;
    private Key keySpec;
    private static String CypherKey = "j!jin@#Sso$#Jang";
    private static AES256 instance;

    private AES256(String key) {
        this.iv = key.substring(0, 16);

        byte[] keyBytes = new byte[16];
        byte[] barr = key.getBytes(StandardCharsets.UTF_8);
        int len = barr.length;
        if(len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(barr,0, keyBytes, 0, len);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        this.keySpec = keySpec;
    }

    public static AES256 getInstance() {
        if(instance == null){
            synchronized (AES256.class) {
                if(instance == null) {
                    instance = new AES256(CypherKey);
                }
            }
        }

        return instance;
    }

    // 암호화
    public String aesEncode(String str) {
        String enStr = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            enStr = new String(Base64.encodeBase64(encrypted));
        } catch (Exception e) {
            log.error("암호화 에러");
        }
        return enStr;
    }

    // 복호화
    public String aesDecode(String str) {
        String deStr = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));

            byte[] byteStr = Base64.decodeBase64(str.getBytes());
            deStr = new String(cipher.doFinal(byteStr), "UTF-8");
        } catch(Exception e) {
            log.error("복호화 에러");
        }
        return deStr;
    }
}
