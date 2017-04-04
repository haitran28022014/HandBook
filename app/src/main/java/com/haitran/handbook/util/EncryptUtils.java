package com.haitran.handbook.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Hai Tran on 3/13/2017.
 */

public class EncryptUtils {
    public static byte[] IV = new byte[16];
    static String KEY = "9a3e04920fb865d7d008c0089cd22842";

    public static String Decrypt(String paramString) {
        byte[] arrayOfByte = Base64.decode(paramString, 0);
        Cipher localCipher;
        try {
            IvParameterSpec localIvParameterSpec = new IvParameterSpec(IV);
            SecretKeySpec localSecretKeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
            localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            localCipher.init(2, localSecretKeySpec, localIvParameterSpec);
            return new String(localCipher.doFinal(arrayOfByte), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
