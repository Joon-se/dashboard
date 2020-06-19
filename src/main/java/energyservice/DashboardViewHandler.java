package energyservice;

import energyservice.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardViewHandler {


    @Autowired
    private DashboardRepository dashboardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserved_then_CREATE_1 (@Payload Reserved reserved) {
        try {
            if (reserved.isMe()) {
                // view 객체 생성
                Dashboard dashboard = new Dashboard();
                // view 객체에 이벤트의 Value 를 set 함
                dashboard.setReservationid(reserved.getId());
                dashboard.setCenterid(reserved.getCenterid());
                dashboard.setMdate(reserved.getSdate());
                dashboard.setMtime(reserved.getStime());
                dashboard.setStatus(reserved.getStatus());
                // view 레파지 토리에 save
                dashboardRepository.save(dashboard);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenChanged_then_UPDATE_1(@Payload Changed changed) {
        try {
            if (changed.isMe()) {
                // view 객체 조회
                List<Dashboard> dashboardList = dashboardRepository.findByReservationid(changed.getId());
                for(Dashboard dashboard : dashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    dashboard.setMdate(changed.getMdate());
                    dashboard.setMtime(changed.getMtime());
                    // view 레파지 토리에 save
                    dashboardRepository.save(dashboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenAssigned_then_UPDATE_2(@Payload Assigned assigned) {
        try {
            if (assigned.isMe()) {
                // view 객체 조회
                List<Dashboard> dashboardList = dashboardRepository.findByReservationid(assigned.getReservationid());
                for(Dashboard dashboard : dashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    dashboard.setAgentid(assigned.getAgentid());
                    dashboard.setStatus(assigned.getStatus());
                    // view 레파지 토리에 save
                    dashboardRepository.save(dashboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenProcessed_then_UPDATE_3(@Payload Processed processed) {
        try {
            if (processed.isMe()) {
                // view 객체 조회
                List<Dashboard> dashboardList = dashboardRepository.findByReservationid(processed.getReservationId());
                for(Dashboard dashboard : dashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    dashboard.setEdate(processed.getEDate());
                    dashboard.setEtime(processed.getETime());
                    dashboard.setStatus(processed.getStatus());
                    // view 레파지 토리에 save
                    dashboardRepository.save(dashboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCancelled_then_DELETE_1(@Payload Cancelled cancelled) {
        try {
            if (cancelled.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                dashboardRepository.deleteByReservationid(cancelled.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}