package com.alibaba.csp.sentinel.dashboard.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Li Huiming
 * @Date: 2021/2/21
 */

@Component("flowRuleNacosPublisher")
public class FlowRuleNacosPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {

    private static Logger logger = LoggerFactory.getLogger(FlowRuleNacosPublisher.class);

    @Qualifier("nacosConfigProp")
    @Autowired
    private NacosConfigProp configProp;

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<List<FlowRuleEntity>, String> converter;

    @Override
    public void publish(String app, List<FlowRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        logger.info("推送限流规则信息到Nacos:{}", JSON.toJSONString(rules));
        configService.publishConfig(app + NacosConfigUtil.FLOW_DATA_ID_POSTFIX, configProp.getGroupId(), converter.convert(rules));
    }

}
