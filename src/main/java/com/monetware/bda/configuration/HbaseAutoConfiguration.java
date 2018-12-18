package com.monetware.bda.configuration;

import com.monetware.bda.hbase.HbaseTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class HbaseAutoConfiguration {

    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";
    //private static final String HBASE_ROOTDIR = "hbase.rootdir";
    //private static final String HBASE_ZNODE_PARENT = "zookeeper.znode.parent";
    private static final String HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT = "hbase.zookeeper.property.clientPort";


    @Autowired
    private HbaseProperties hbaseProperties;

    @Bean
    @ConditionalOnMissingBean(HbaseTemplate.class)
    public HbaseTemplate hbaseTemplate() {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(HBASE_QUORUM, this.hbaseProperties.getQuorum());
        //configuration.set(HBASE_ROOTDIR, hbaseProperties.getRootDir());
        //configuration.set(HBASE_ZNODE_PARENT, hbaseProperties.getNodeParent());
        configuration.set(HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT, hbaseProperties.getClientPort());
        HbaseTemplate hbaseTemplate = new HbaseTemplate(configuration);

        return hbaseTemplate;
    }

}
