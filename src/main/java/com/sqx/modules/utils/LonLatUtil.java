package com.sqx.modules.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * @description: 经纬度计算工具类
 */
public class LonLatUtil {

    /**
     * 创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
     * @param gpsFrom 当前位置
     * @param gpsTo 目标位置
     * @param ellipsoid 坐标系
     * @return
     */
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo,
                                          Ellipsoid ellipsoid){
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }
}
