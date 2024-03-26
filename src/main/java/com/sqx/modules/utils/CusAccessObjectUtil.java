package com.sqx.modules.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取对象的IP地址等信息
 * @author fang
 * @date 2020/9/23
 */
public class CusAccessObjectUtil {

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddrs( HttpServletRequest request)
            throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        String[] arr = ipString.split(",");
        for (String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }

        return ipString;
    }

    public static String getAddress(String ip) {
        String url = "http://ip.ws.126.net/ipquery?ip=" + ip;
        String str = HttpClientUtil.doGet(url);
        if(!StrUtil.hasBlank(str)){
            String substring = str.substring(str.indexOf("{"), str.indexOf("}")+1);
            System.out.println(substring);
            JSONObject jsonObject = JSONUtil.parseObj(substring);
            String province = jsonObject.getStr("province");
            String city = jsonObject.getStr("city");
            return province+city;
        }
        return "未知";
    }




//    // 测试
    public static void main(String[] args) {
        String ip = "111.121.72.101";
        String address = getAddress(ip);
        System.out.println(address);
    }






}