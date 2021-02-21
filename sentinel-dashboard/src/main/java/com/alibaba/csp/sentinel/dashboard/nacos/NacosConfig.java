package com.alibaba.csp.sentinel.dashboard.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

/**
 * @Author: Li Huiming
 * @Date: 2021/2/21
 */
@Configuration
public class NacosConfig {

    @Qualifier("nacosConfigProp")
    @Autowired
    private NacosConfigProp configProperties;

    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean
    public ConfigService configService() throws Exception {
        System.out.println("配置信息: " + JSON.toJSONString(configProperties));
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, configProperties.getServerAddr());
        properties.put(PropertyKeyConst.NAMESPACE, configProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, configProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, configProperties.getPassword());
        return ConfigFactory.createConfigService(properties);
//        return ConfigFactory.createConfigService("localhost");
    }

}
