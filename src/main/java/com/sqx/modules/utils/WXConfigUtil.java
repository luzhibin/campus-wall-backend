package com.sqx.modules.utils;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author fang
 * @date 2020/2/26
 */
@Data
public class WXConfigUtil implements WXPayConfig {
    private  byte[] certData;
    private  String appId = "";
    private  String key = "";
    private  String mchId = "";


    //初始化加载证书
    public WXConfigUtil() throws Exception {
        /*int byteread = 0;
        int bytesum = 0;
        URL url = new URL("windine.blogdriver.com/logo.gif");
        *//*String certPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/weixin/apiclient_cert.p12";//从微信商户平台下载的安全证书存放的路径*//*
        URLConnection conn = url.openConnection();
        InputStream inStream = conn.getInputStream();
        FileOutputStream fs = new FileOutputStream(" /www/wwwroot/"+profileHttpUrl+"/file/uploadPath");
        byte[] buffer = new byte[1204];
        int length;
        while ((byteread = inStream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
        }*/
        ClassPathResource classPathResource = new ClassPathResource("weixin/apiclient_cert.p12");
        InputStream certStream = classPathResource.getInputStream();
        this.certData = IOUtils.toByteArray(certStream);
        certStream.read(this.certData);
        certStream.close();
//        File file = new File(" ");
        /*InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();*/
    }


    @Override
    public String getAppID() {
        return this.appId;
    }

    @Override
    public String getMchID() {
        return this.mchId;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }


}
