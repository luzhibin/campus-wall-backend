package com.sqx.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用拦截器配置跨域
 */
/*@Component
@Configuration
@WebFilter(urlPatterns = {"/*"}, filterName = "CorssOriginFilter")
public class CorssOriginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("===========================================================================");
        System.out.println("CorssOriginFilter() ————>>  拦 截 器 已 启 动 ...");
        System.out.println("===========================================================================");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
        System.out.println("拦 截 请 求：" + req.getServletContext().getContextPath());
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}*/
