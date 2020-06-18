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
    public void wheneverConfirmed_ProductReady(@Payload Confirmed confirmed){

        if(confirmed.isMe()){
            System.out.println("##### listener ProductReady : " + confirmed.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPicked_End(@Payload Picked picked){

        if(picked.isMe()){
            System.out.println("##### listener End : " + picked.toJson());
        }
    }

}
