package com.sqx.modules.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageResponse implements Serializable {
    /**
     * 总人数
     */
    private int totalNumber;
    /**
     * 普通用户人数
     */
    private int userNumber;
    /**
     * 会员人数
     */
    private int vipUserNumber;
}
