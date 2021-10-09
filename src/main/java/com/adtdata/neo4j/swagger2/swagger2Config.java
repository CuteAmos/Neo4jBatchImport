package com.adtdata.neo4j.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class swagger2Config {

    //访问路径 /swagger-ui.html
    @Bean
    public Docket createRestApi() {
        //添加head参数start
       /* ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder tokenPar1 = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        tokenPar1.name("userId").description("用户id").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        pars.add(tokenPar1.build());*/

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API-TEST")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.adt.neo4j.api"))//根据自己项目修改
                .paths(PathSelectors.any())
                .build()
                /*   .host("39.96.211.58/services")*/
                .pathMapping("/")
                .useDefaultResponseMessages(false)
//                .globalOperationParameters(pars)
                .alternateTypeRules()
                .forCodeGeneration(false);
    }

    private ApiInfo apiInfo() {
        //访问地址 /swagger-ui.html
        return new ApiInfoBuilder()
                .title("数据导入文档 APIs ")
                .description("neo4j 数据导入文档")
                .version("1.0")
                .build();
    }
}
