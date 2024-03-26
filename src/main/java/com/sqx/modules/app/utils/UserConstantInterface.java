package com.sqx.modules.app.utils;


import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.sqx.common.utils.Result;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 参数配置
 */
public interface UserConstantInterface {


    /**
     * 请求的网址
     */
    String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 固定参数
     */
    String WX_LOGIN_GRANT_TYPE = "authorization_code";

    /**
     * 解密手机号
     * @param decryptData 加密手机号（微信返回）
     * @param key  session_key
     * @param iv iv（微信返回）
     * @return
     */
    static Result decryptS5(String decryptData, String key, String iv)  {
        try {
            byte[] encData = Base64.decode(decryptData);
            byte[] ivs = Base64.decode(iv);
            byte[] keys = Base64.decode(key);
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivs);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(keys, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return Result.success("获取手机号成功").put("data", JSON.parseObject(new String(cipher.doFinal(encData), "UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(-1,"获取手机号失败");
        }
    }

}

