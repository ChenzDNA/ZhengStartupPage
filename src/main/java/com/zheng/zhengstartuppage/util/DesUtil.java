package com.zheng.zhengstartuppage.util;

import com.zheng.zhengstartuppage.config.DesConfig;
import com.zheng.zhengstartuppage.config.WebApplicationContextConfig;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author : 陈征
 * @date : 2021-10-10 19:39
 */

public class DesUtil {
    public static final byte[] keys = WebApplicationContextConfig.getBean(DesConfig.class).key;

    private static final SecureRandom secureRandom = new SecureRandom();

    private static Cipher encryptCipher;

    private static Cipher decryptCipher;

    static {
        try {
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(keys));
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encode(String source) throws IllegalBlockSizeException, BadPaddingException {
        return Base64.getEncoder().encodeToString(encryptCipher.doFinal(source.getBytes()));
    }

    public static String decode(String source) throws IllegalBlockSizeException, BadPaddingException {
        return new String(decryptCipher.doFinal(Base64.getDecoder().decode(source)));
    }
}
