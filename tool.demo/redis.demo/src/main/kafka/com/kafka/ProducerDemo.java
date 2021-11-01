package com.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

// kafka 启动： ./kafka-server-start.sh ../config/server.properties &
// ps -ef | grep kafka
// ./kafka-manager -Dconfig.file=../conf/application.conf -Dhttp.port=8888 &
// rm RUNNING_PID
// ./kafka-manager -Dconfig.file=conf/application.conf  &
public class ProducerDemo {

    // Topic
    private static final String topic = "10.213.3.199_gateway_to_admin_test";

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.213.6.79:9092,10.213.8.109:9092,10.213.8.110:9092,10.213.8.111:9092");
        props.put("acks", "0");
        props.put("group.id", "admin_consumer_group_test");
        props.put("retries", "0");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

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