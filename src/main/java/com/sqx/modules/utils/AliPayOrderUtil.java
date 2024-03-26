package com.sqx.modules.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

public class AliPayOrderUtil {

    /**
     * 将request中的参数转换成Map
     *
     * @param request
     * @return
     */
    public static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<>();
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;
            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }
        return retMap;
    }



    /**
     * 计算两个经纬度之间的距离
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */


    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }


    /**
     * 生成随机订单编号
     * @return
     */
    public static String createOrderId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId+String.format("%015d", hashCodeV);
    }



    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDates(Date date) {
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 获取最近一周的时间
     *
     * @param date
     * @return 返回起始时间
     */
    public static String getStartTime(Date date) {
        String weekOfDate = getWeekOfDate(date);
        Integer day=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (weekOfDate){
            case "日": day=6;break;
            case "一": day=0;break;
            case "二": day=1;break;
            case "三": day=2;break;
            case "四": day=3;break;
            case "五": day=4;break;
            case "六": day=5;break;
        }
        Calendar cal=Calendar.getInstance();
        if(!day.equals(0)){
            cal.add(Calendar.DATE,-day);
        }
        return sdf.format(cal.getTime());
    }

}
