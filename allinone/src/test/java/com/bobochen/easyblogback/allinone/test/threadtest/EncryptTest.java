package com.bobochen.easyblogback.allinone.test.threadtest;

import com.bobochen.easyblogback.allinone.common.EncryptUtil;
import org.junit.Test;

public class EncryptTest {
    @Test
    public void md5Test() {
        try {
            String encode = EncryptUtil.MD532("1234");
            System.out.println(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void desTest() {
        try {
            String encode = EncryptUtil.encryptDES("12349abc");
            System.out.println(encode);
            String decode = EncryptUtil.decryptDES(encode);
            System.out.println(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void aesTest() {
        try {
            String encode = EncryptUtil.aesEncrypt("12349abc");
            System.out.println(encode);
            String decode = EncryptUtil.aesDecrypt(encode);
            System.out.println(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
