package com.sqx.common.validator;

import com.sqx.common.exception.SqxException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 */
 public class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new SqxException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new SqxException(message);
        }
    }
}
