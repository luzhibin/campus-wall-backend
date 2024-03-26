package com.sqx.modules.utils.excel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fang
 * @date 2020/9/24
 */

@Data
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4454016249210520899L;

    /**
     * 表头
     */
    private List<String> titles;

    /**
     * 数据
     */
    private List<List<Object>> rows;

    /**
     * 页签名称
     */
    private String name;


}
