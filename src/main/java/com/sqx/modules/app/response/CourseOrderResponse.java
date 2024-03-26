package com.sqx.modules.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseOrderResponse implements Serializable {
    /**
     * 课程名称
     */
    private  String  coursename;
    /**
     * 售卖笔数
     */
    private  int  coursenum;
    /**
     * 售卖金额
     */
    private  Double coursemoney;
}
