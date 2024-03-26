package com.sqx.modules.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);
    static MessageDigest messageDigest = null;

    /**
     * 判断新密码和旧密码是否正确  返回true 和 false
     *
     * @param newStr
     * @param oldMD5Str
     * @return
     */
    public final static boolean checkMD5(String newStr, String oldMD5Str) {
        String temp = encoderByMd5(newStr);
        return (temp != null && temp.equals(oldMD5Str)) ? true : false;
    }

    /**
     * 对给定的字符串进行加密
     *
     * @param source
     * @return 加密后的16进制的字符串
     */
    public final static String encoderByMd5(String source) {
        String tmp = source.substring(0, 1)
                + source.subSequence(source.length() - 1, source.length());
        tmp = md5(tmp);
        return md5(source + tmp);
    }

    private static String md5(String source) {

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {

            byte[] strTemp = source.getBytes();
            // 使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte b : md) {
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }

            if (logger.isDebugEnabled()) {
                logger.debug("加密后的字符串：" + new String(str));
            }
            return new String(str);
        } catch (Exception e) {
            logger.error("md5加密出错：" + source, e);
            return null;
        }

    }


    public static String encodeByMD5(String str) {
        try {
            if (messageDigest == null) {
                messageDigest = MessageDigest.getInstance("MD5");
            }
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException caught!", e);

        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException error!", e);
        }
        if (messageDigest == null) {
            return "";
        }
        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    /**
     * MD5加密字符串（32位大写）
     *
     * @param string 需要进行MD5加密的字符串
     * @return 加密后的字符串（大写）
     */
    public static String md5Encrypt32Upper(String string) {
        byte[] hash;
        try {
            //创建一个MD5算法对象，并获得MD5字节数组,16*8=128位
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        //转换为十六进制字符串
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toUpperCase();
    }


    public static String encryption(String plain) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plain.getBytes("utf-8"));
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return re_md5;
    }



}
