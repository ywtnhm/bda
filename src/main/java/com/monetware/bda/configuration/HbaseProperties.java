package com.monetware.bda.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zengcd 2018/12/15
 * @since 1.0.0
 */

@Component
@ConfigurationProperties(prefix = "spring.data.hbase")
public class HbaseProperties {

    private String quorum;


    private String clientPort;

    public String getQuorum() {
        return quorum;
    }

    public void setQuorum(String quorum) {
        this.quorum = quorum;
    }

    public String getClientPort() {

        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }
}
