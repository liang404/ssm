package com.liang.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author liang
 * @create 2020/2/27 15:50
 */
public class BCryptPasswordEncoderUtils {
    //每次加密都说不一样的
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
     return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println(encodePassword("123"));
        System.out.println(encodePassword("123").toString().length());

    }
}
