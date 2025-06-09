package com.wl.springboot_pe_2211.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;

/**
 * Knife4j配置类
 */

@Configuration
public class Knife4jConfig {

    /**
     * 1.定义参数
     * 2.创建并配置 Docket 对象，用于 Swagger 文档的生成和展示
     * 3.创建并配置ApiInfo 对象，用于设置 Swagger 文档的基本信息
     */
    /**
     * 【重要】指定Controller包路径
     */
    private String basePackage = "com.wl.springboot_pe_2211.controller";
    /**
     * 主机名
     */
    private String host = "localhost";
    /**
     * 标题
     */
    private String title = "医疗管理平台在线API文档";
    /**
     * 简介
     */
    private String description = "医疗管理管理平台在线API文档（内部专用）";
    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 联系人
     */
    private String contactName = "小坤";
    /**
     * 联系网址
     */
    private String contactUrl = "http://www.baidu.com";
    /**
     * 联系邮箱
     */
    private String contactEmail = "xx@yy.cn";
    /**
     * 版本号
     */
    private String version = "1.0";



    @Bean
    public Docket docket() {
        String groupName = "1.0.0";

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host(host) // 设置主机名
                .apiInfo(apiInfo()) // 设置 API 文档信息
                .groupName(groupName) // 设置分组名称
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage)) // 设置扫描的 Controller 包路径
                .paths(PathSelectors.any()) // 设置 API 路径匹配规则
                .build();


        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl) // 设置服务条款 URL
                .contact(new Contact(contactName, contactUrl, contactEmail)) // 设置联系信息
                .version(version)
                .build();
    }
}
