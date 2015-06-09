package com.asiatech.spring.ultil;

import org.apache.commons.codec.digest.DigestUtils;

public class ApplicationUtil {
    public static String hashPassword(String pw) {
        if (pw == null)
            return null;
        else
            return DigestUtils.sha1Hex(pw);
    }
}
