package com.demo.myshiro.web;//package com.demo.myshiro.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class CustomWebMVCConfig implements WebMvcConfigurer{
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//
//                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//
//        registry.addResourceHandler("/webjars/**")
//
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

//}
////@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private AuthInterceptor authorizationInterceptor;
//authorizationInterceptor
//
//    /**
//     * 如果重写了这个方法，yml 里面的 static-locations 将不生效
//     */
//    // @Override
//    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    //     // 允许访问static文件
//    //     registry.addResourceHandler("/**")
//    //             .addResourceLocations("classpath:/resources/")
//    //             .addResourceLocations("classpath:/static/")
//    //             .addResourceLocations("classpath:/public/");
//    //     //文件磁盘图片url映射
//    //     //        registry.addResourceHandler("/file/**")
//    //     //                .addResourceLocations("file:D://upload/");
//    // }
//
//
//    /**
//     * 添加拦截器 -- 如果不加 static-locations 将不能被访问
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //拦截路径可自行配置多个 可用 ，分隔开
//        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
//    }
//
//    /**
//     * 跨域支持
//     *
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
//                .maxAge(3600 * 24);
//    }
//
//}
