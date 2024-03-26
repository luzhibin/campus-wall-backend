package com.sqx.modules.app.utils;

import lombok.Data;

@Data
public class WxPhone {

    private String decryptData;

    private String key;

    private String iv;

}
