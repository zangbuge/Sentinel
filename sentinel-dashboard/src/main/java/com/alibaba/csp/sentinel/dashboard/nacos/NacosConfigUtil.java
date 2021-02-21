package com.alibaba.csp.sentinel.dashboard.nacos;

/**
 * @Author: Li Huiming
 * @Date: 2021/2/21
 */
public class NacosConfigUtil {

    /**
     * 流控规则配置文件默认在SENTINEL_GROUP组、DATA_ID以-flow-rules结尾
     */
    public static final String FLOW_DATA_ID_POSTFIX = "-flow-rules";

    public static final String GROUP_ID = "SENTINEL_GROUP";
}
