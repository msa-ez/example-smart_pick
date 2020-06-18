package smartpick;

import smartpick.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDelivered_Receive(@Payload Delivered delivered){

        if(delivered.isMe()){
            System.out.println("##### listener Receive : " + delivered.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCanceled_Cancel(@Payload Canceled canceled){

        if(canceled.isMe()){
            System.out.println("##### listener Cancel : " + canceled.toJson());
        }
    }

}
