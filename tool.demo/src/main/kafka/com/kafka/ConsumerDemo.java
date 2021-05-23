package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);
    

    public static void main(String[] args) {

		Properties props = new Properties();
        props.put("bootstrap.servers", "10.3.11.4:9092,10.3.11.5:9092,10.3.11.6:9092,10.3.11.7:9092");
        props.put("group.id", "admin_consumer_group_real ");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("10.7.6.11_gateway_to_admin_real","10.7.6.20_gateway_to_admin_real","10.7.6.73_gateway_to_admin_real","10.7.6.75_gateway_to_admin_real","10.7.6.77_gateway_to_admin_real","10.7.6.78_gateway_to_admin_real"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records) {
               System.out.println( record.topic());
            	  
            }
        }
    }
}