package com.alibaba.csp.sentinel.dashboard.nacos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Author: Li Huiming
 * @Date: 2021/2/21
 */
@Primary
@Component("nacosConfigProp")
@ConfigurationProperties(prefix = "sentinel.nacos")
public class NacosConfigProp {

    private String serverAddr;

    private String username;

    private String password;

    private String namespace;

    private String groupId = "DEFAULT_GROUP";


    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
