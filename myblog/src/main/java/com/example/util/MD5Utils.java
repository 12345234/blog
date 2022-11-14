package com.example.util;

import ch.qos.logback.classic.pattern.MessageConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author admin 密码加密
 */
public class MD5Utils {
    public static  String code(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] bydigest = md5.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            int i;
            for (int offset =0 ;offset<bydigest.length;offset++) {
                i = bydigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    stringBuffer.append("0"); }
                    stringBuffer.append(Integer.toHexString(i));
            }
                return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {

        System.out.println(code("123"));
    }
}
