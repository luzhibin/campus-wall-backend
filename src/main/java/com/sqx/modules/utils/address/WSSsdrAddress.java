package com.sqx.modules.utils.address;


import com.thoughtworks.xstream.core.BaseException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class WSSsdrAddress implements Serializable {

    private String province;

    private String city;

    private String county;

    private String address;

    public WSSsdrAddress(String province, String city, String county, String address) {
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(county) ||
                StringUtils.isBlank(address)) {
        }
        this.province = province;
        this.city = city;
        this.county = county;
        this.address = address;
    }
}
