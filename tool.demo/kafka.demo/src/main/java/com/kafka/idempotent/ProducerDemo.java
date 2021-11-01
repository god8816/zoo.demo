package com.kafka.idempotent;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 生产者幂等配置
 * */
public class ProducerDemo {

    // Topic
    private static final String topic = "10.213.3.199_gateway_to_admin_test";

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.213.3.11:9092,10.213.3.12:9092,10.213.3.13:9092,10.213.3.14:9092");
        props.put("group.id", "admin_consumer_group_test");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        //开启幂等
        props.put("enable.idempotence", "true");  
        //副本全部确认
        props.put("acks", "all");  
        //消息发送重试次数
        props.put("retries", "3");
        //未确认响应发送消息数
        props.put("max.in.flight.requests.per.connection", 1);
        //leader只能在OSR中选举
        props.put("unclean.leader.election.enable", false);
        //写最少副本数
        props.put("min.insync.replicas", 1);
        

        //生产者实例
        KafkaProducer producer = new KafkaProducer(props);

       

        // 发送业务消息
        // 读取文件 读取内存数据库 读socket端口
        
        String msg = "1";
        
        while ( true ) {
            	producer.send(new ProducerRecord<String, String>(topic, msg));
            	Thread.sleep(200);
		}

    }
}