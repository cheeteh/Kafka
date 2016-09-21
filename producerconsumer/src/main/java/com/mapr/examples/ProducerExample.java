package com.mapr.examples;

//import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.Producer;

import java.io.IOException;
//import java.io.InputStream;
import java.util.Properties;

/**
 * This producer will send a bunch of messages to topic "fast-messages". Every so often,
 * it will send a message to "slow-messages". This shows how messages can be sent to
 * multiple topics. On the receiving end, we will see both kinds of messages but will
 * also see how the two topics aren't really synchronized.
 */
public class ProducerExample {
    public static void main(String[] args) throws IOException {
        // set up the producer
        
        //KafkaProducer<String, String> producer;
        
        Properties props = new Properties();
        
        try {
            
            props.put("bootstrap.servers", "localhost:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        }
        catch(Exception e){
            throw e;
        }
        
        //ProducerConfig config = new ProducerConfig(props);
        
        Producer<String,String> producer = new KafkaProducer<>(props);
        
        for(int i = 0; i < 100; i++)
        {
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
            System.out.println("Sent msg number " + i);
        }
        producer.close();

    }
}
