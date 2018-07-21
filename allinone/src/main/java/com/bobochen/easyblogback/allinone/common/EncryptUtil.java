package com.bobochen.easyblogback.allinone.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptUtil {

    public static String MD532(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    public static String MD516(String encryptStr) {
        return MD532(encryptStr).substring(8, 24);
    }


    private static String deskey = "bobochen";

    public static String encryptDES(String data) throws Exception {
        byte[] bt = encryptDES(data.getBytes(), deskey.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }


    public static String decryptDES(String data) throws IOException, Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decryptDES(buf, deskey.getBytes());
        return new String(bt);
    }

    private static byte[] encryptDES(byte[] data, byte[] key) throws Exception {

        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }


    private static byte[] decryptDES(byte[] data, byte[] key) throws Exception {

        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DES");

        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }



    // 加密长度
    private final static int AESLENGTH = 128;
    // 编码方式
    private final static String AESENCODE = "UTF-8";
    // 秘钥
    private final static String AESdefaultKey = "bobochen";
    // 前缀
    private final static String AESdefaultPrefix = "";


    /**
     * AES加密后再使用BASE64加密
     * 增加前缀
     * 1.辨识正常数据，使其不进行解密
     * 2.提高安全度
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content) {
        String value = "";
        try {
            if(!isEmpty(content)){
                value = AESdefaultPrefix + base64Encode(aesEncryptToBytes(content));
            }
        } catch (Exception e) {
            System.out.println("EncryptAndDecrypt（加密错误）");
            e.printStackTrace();
        }
        return value;
    }

    /**
     * AES解密后再使用BASE64解密
     * 增加前缀
     * 1.辨识正常数据，使其不进行解密
     * 2.提高安全度
     * @param encryptStr
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr) {
        String value = "";
        try {
            int length = AESdefaultPrefix.length();
            if (encryptStr.length() > length) {
                String val = encryptStr.substring(0, length);
                if (val.equals(AESdefaultPrefix)) {
                    value = aesDecryptByBytes(base64Decode(encryptStr.substring(length)));
                } else {
                    value = encryptStr;
                }
            } else {
                value = encryptStr;
            }
        } catch (Exception e) {
            System.out.println("EncryptAndDecrypt（解密错误）");
            e.printStackTrace();
        }
        return value;
    }

    /**
     * AES加密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(AESLENGTH, new SecureRandom(AESdefaultKey.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec sks = new SecretKeySpec(kgen.generateKey().getEncoded(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        return cipher.doFinal(content.getBytes(AESENCODE));
    }

    /**
     * AES解密
     *
     * @param encryptBytes
     * @return
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(AESLENGTH, new SecureRandom(AESdefaultKey.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec sks = new SecretKeySpec(kgen.generateKey().getEncoded(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, sks);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * BASE64 加密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * BASE64 解密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }

}

