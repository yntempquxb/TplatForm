package com.taikang;

import com.taikang.datasource.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;


@EnableCaching
@EnableAspectJAutoProxy
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@MapperScan(basePackages={"com.taikang.policyproposal.*.dao"})
@Import(DynamicDataSourceRegister.class)
public class TplatformProposalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TplatformProposalApplication.class, args);
    }

}