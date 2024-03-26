package com.sqx.modules.utils.address;

import com.thoughtworks.xstream.core.BaseException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressUtil {

    private AddressUtil() {}

    /**
     * 从地址串中解析提取出省市区等信息
     * @param address   地址信息
     * @return          解析后的地址Map
     */
    private static Map<String,String> addressResolution(String address){
        //1.地址的正则表达式
        String regex = "(?<province>[^省]+省|.+自治区|[^澳门]+澳门|[^香港]+香港|[^市]+市)?(?<city>[^自治州]+自治州|[^特别行政区]+特别行政区|[^市]+市|.*?地区|.*?行政单位|.+盟|市辖区|[^县]+县)(?<county>[^县]+县|[^市]+市|[^镇]+镇|[^区]+区|[^乡]+乡|.+场|.+旗|.+海域|.+岛)?(?<address>.*)";
        //2、创建匹配规则
        Matcher m = Pattern.compile(regex).matcher(address);
        String province;
        String city;
        String county;
        String detailAddress;
        Map<String,String> map = new HashMap<>(16);

        while (m.find()){
            //加入省
            province = m.group("province");
            map.put("province", province == null ? "" : province.trim());
            //加入市
            city = m.group("city");
            map.put("city", city == null ? "" : city.trim());
            //加入区
            county = m.group("county");
            map.put("county", county == null ? "" : county.trim());
            //详细地址
            detailAddress = m.group("address");
            map.put("address", detailAddress == null ? "" : detailAddress.trim());
        }
        return map;
    }

    /**
     * 根据地址获取解析后的地址对象
     * @param address   解析前地址Str
     * @return          解析后地址对象
     */
    public static WSSsdrAddress resolveAddress(String address) {
        Map<String, String> addressMap = addressResolution(address);
        return new WSSsdrAddress(addressMap.get("province"), addressMap.get("city"), addressMap.get("county"), addressMap.get("address"));
    }

}
