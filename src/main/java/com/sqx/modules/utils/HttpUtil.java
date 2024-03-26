package com.sqx.modules.utils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 进行http访问的基本类
 */
public class HttpUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String METHOD_POST = "POST";

    private static final String METHOD_GET = "GET";

    private static final int CONNECTTIMEOUT = 5000;

    private static final int READTIMEOUT = 5000;

    private static class DefaultTrustManager implements X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] cert, String oauthType)
                throws java.security.cert.CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] cert, String oauthType)
                throws java.security.cert.CertificateException {
        }
    }

    private static HttpURLConnection getConnection(URL url, String method)
            throws IOException {

        HttpURLConnection conn;
        if ("https".equals(url.getProtocol())) {
            SSLContext ctx;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() },
                        new SecureRandom());
            } catch (Exception e) {
                throw new IOException(e);
            }
            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;// 默认都认证通过
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        return conn;

    }

    /**
     * 通过get方法访问
     * 
     * @param url 访问的url地址
     * @param urlParams 请求需要的参数
     * @return 返回请求响应的数据
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> urlParams)
            throws IOException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("The parameter 'url' can not be null or blank.");
        }
        url += buildQuery(urlParams, DEFAULT_CHARSET);
        HttpURLConnection conn = getConnection(new URL(url), METHOD_GET);
        String s = getResponseAsString(conn);
        return s;
    }

    /**
     * 
     * @param url api请求的权路径url地址
     * @param urlParams 请求的参数
     * @param requestJson 请求报文
     * @return 请求响应
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> urlParams, String requestJson) throws IOException {
        return doPost(url, urlParams, requestJson, CONNECTTIMEOUT, READTIMEOUT);
    }

    /**
     *
     * 通过post方法请求数据
     *
     * @param url 请求的url地址
     * @param urlParams 请求的参数
     * @param requestJson 请求报文
     * @param connectTimeOut 请求连接过期时间
     * @param readTimeOut 请求读取过期时间
     * @return 请求响应
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> urlParams, String requestJson,
            int connectTimeOut, int readTimeOut) throws IOException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("The parameter 'url' can not be null or blank.");
        }
        url += buildQuery(urlParams, DEFAULT_CHARSET);
        HttpURLConnection conn = getConnection(new URL(url), METHOD_POST);
        conn.setConnectTimeout(connectTimeOut);
        conn.setReadTimeout(readTimeOut);
        conn.getOutputStream().write(requestJson.getBytes(DEFAULT_CHARSET));
        String s = getResponseAsString(conn);
        return s;
    }

    /**
     * 
     * @param params 请求参数
     * @return 构建query
     */
    public static String buildQuery(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Entry<String, String> entry : params.entrySet()) {
            if (first) {
                sb.append("?");
                first = false;
            } else {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            if (areNotEmpty(key, value)) {
                sb.append(key).append("=").append(URLEncoder.encode(value, charset));
            }
        }
        return sb.toString();

    }

    private static String getResponseAsString(HttpURLConnection conn) throws IOException {
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), DEFAULT_CHARSET);
        } else {
            String msg = getStreamAsString(es, DEFAULT_CHARSET);
            if (isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + " : " + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }

    }

    private static String getStreamAsString(InputStream input, String charset) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(input, charset));
            String str;
            while ((str = bf.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } finally {
            if (bf != null) {
                bf.close();
            }
        }

    }

    /**
     * 判断字符串为空
     *
     * @param str 字符串信息
     * @return true or false
     */
    private static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符数组，不为空
     *
     * @param values 字符数组
     * @return true or false
     */
    public static boolean areNotEmpty(String... values) {
        if (values == null || values.length == 0) {
            return false;
        }

        for (String value : values) {
            if (isEmpty(value)) {
                return false;
            }
        }
        return true;
    }
}
