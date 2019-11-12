package com.example.lin9080.yantu_lin;

import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    /*
    产生一把AES密钥

    @param keySize
    密钥大小，只能是128位（16字节）、192位（24字节）、256位（32字节）

    @return 字节数组形式的AES密钥
 */
    public static byte[] generateAESKey(int keySize){

        //保存AES密钥的字节数组
        byte[] keyBytes = null;

        try {
            //获取密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //设置密钥长度，如果不调用该方法，默认生成256位的密钥
            keyGenerator.init(keySize);
            //获得密钥对象
            SecretKey secretKey = keyGenerator.generateKey();
            //转成字节数组
            keyBytes = secretKey.getEncoded();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return keyBytes;

    }
    public static String encryptECB(String in){
        //待加密数据
        String clearText = in;
        String finalResult="";
//产生密钥
        byte[] keyBytes = generateAESKey(256);
//构建SecretKeySpec，初始化Cipher对象时需要该参数
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        try {
            //构建Cipher对象，需要传入一个字符串，格式必须为"algorithm/mode/padding"或者"algorithm/",意为"算法/加密模式/填充方式"
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            //加密数据
            byte[] resultBytes = cipher.doFinal(clearText.getBytes());

            //结果用Base64转码
            finalResult = Base64.encodeToString(resultBytes,Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return finalResult;
    }

    //data为待解密的数据，keyBytes为加密时所使用的密钥，transform为加密时所采取的加密模式和填充模式
    static public byte[] decryptData(String data,byte[] keyBytes,String transform){
        byte[] clearData = null;

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes,"AES");

        try {

            //根据格式获取Cipher实例，需与加密时一致
            Cipher cipher = Cipher.getInstance(transform);
            //初始化Cipher，注意这里变为解密模式
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            //先Base64解码
            byte[] temp = Base64.decode(data,Base64.DEFAULT);

            //解密数据
            clearData = cipher.doFinal(temp);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return clearData;

    }
}
