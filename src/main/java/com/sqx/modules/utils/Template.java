package com.sqx.modules.utils;

import lombok.Data;

import java.util.List;

@Data
public class Template {

    private String template_id;

    private String touser;

    private String page;

    private String data;

    private List<TemplateParam> templateParamList;

}
