package smartpick;

import smartpick.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportViewHandler {


    @Autowired
    private ReportRepository reportRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                Report report = new Report();
                // view 객체에 이벤트의 Value 를 set 함
                report.setOrderId(ordered.getId());
                report.setCustomerId(ordered.getCustomerId());
                report.setProductName(ordered.getProductName());
                report.setQty(ordered.getQty());
                report.setStatus(ordered.getStatus());
                // view 레파지 토리에 save
                reportRepository.save(report);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceled_then_UPDATE_1(@Payload Canceled canceled) {
        try {
            if (canceled.isMe()) {
                // view 객체 조회
                List<Report> reportList = reportRepository.findByOrderId(canceled.getId());
                for(Report report : reportList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    report.setStatus(canceled.getStatus());
                    // view 레파지 토리에 save
                    reportRepository.save(report);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDelivered_then_UPDATE_2(@Payload Delivered delivered) {
        try {
            if (delivered.isMe()) {
                // view 객체 조회
                List<Report> reportList = reportRepository.findByOrderId(delivered.getOrderId());
                for(Report report : reportList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    report.setDeliveryId(delivered.getId());
                    report.setStatus(delivered.getStatus());
                    // view 레파지 토리에 save
                    reportRepository.save(report);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenConfirmed_then_UPDATE_3(@Payload Confirmed confirmed) {
        try {
            if (confirmed.isMe()) {
                // view 객체 조회
                List<Report> reportList = reportRepository.findByOrderId(confirmed.getOrderId());
                for(Report report : reportList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    report.setPickId(confirmed.getId());
                    report.setStatus(confirmed.getStatus());
                    // view 레파지 토리에 save
                    reportRepository.save(report);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPicked_then_UPDATE_4(@Payload Picked picked) {
        try {
            if (picked.isMe()) {
                // view 객체 조회
                List<Report> reportList = reportRepository.findByOrderId(picked.getOrderId());
                for(Report report : reportList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    report.setStatus(picked.getStatus());
                    // view 레파지 토리에 save
                    reportRepository.save(report);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}