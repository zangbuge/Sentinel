package com.alibaba.csp.sentinel.dashboard.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

/**
 * 参考源码中官方提供了test示例下关于Nacos等持久化示例 （即test目录） test\java\com\alibaba\csp\sentinel\dashboard\rule\nacos\
 * 但是具体的实现还需要一些细节
 * 官方说明：从 Sentinel 1.4.0 开始，我们抽取出了接口用于向远程配置中心推送规则以及拉取规则
 * DynamicRuleProvider<T>: 拉取规则, DynamicRulePublisher<T>: 推送规则
 *
 * 1. 在pom.xml文件中sentinel-datasource-nacos依赖去掉test scope注释
 * 2. app/scripts/directives/sidebar/sidebar.html ,修改flowV1为flow 让其调用 FlowControllerV2 接口
 * 3. 修改 FlowControllerV2 重写向远程配置中心 推送规则 以及拉取规则 FlowRuleNacosProvider 与 FlowRuleNacosPublisher 类
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
