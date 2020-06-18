package smartpick;

import org.springframework.transaction.annotation.Transactional;
import smartpick.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    private SmartDeliveryRepository smartDeliveryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    @Transactional
    public void wheneverOrdered_ProductReady(@Payload Ordered ordered){

        if(ordered.isMe()){
            System.out.println("##### listener ProductReady : " + ordered.toJson());
            SmartDelivery newSmartDelivery = new SmartDelivery(ordered);
            smartDeliveryRepository.save(newSmartDelivery);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    @Transactional
    public void wheneverCanceled_Cancel(@Payload Canceled canceled){

        if(canceled.isMe()){
            System.out.println("##### listener Cancel : " + canceled.toJson());
            SmartDelivery smartDelivery = smartDeliveryRepository.findByOrderId(canceled.getId());

            // 이미 픽업센터에 배송했을 때는, 픽업센터 통해서 취소 처리한다.
            if (!"DELIVERED".equals(smartDelivery.getStatus())){
                smartDelivery.setStatus("CANCELED");
            }
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    @Transactional
    public void wheneverPickCanceled_Cancel(@Payload PickCanceled pickCanceled){

        if(pickCanceled.isMe()){
            System.out.println("##### listener Cancel : " + pickCanceled.toJson());
            SmartDelivery smartDelivery = smartDeliveryRepository.findByOrderId(pickCanceled.getOrderId());
            smartDelivery.setStatus("CANCELED");
        }
    }
}
