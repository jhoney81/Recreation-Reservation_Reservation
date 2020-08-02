package Recreation.Reservation;

import Recreation.Reservation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderAccepted_ReservationRequest(@Payload OrderAccepted orderAccepted){

        if(orderAccepted.isMe()){
            Reservation reservationAccept = new Reservation();
            reservationAccept.setStatus("RESERVATIONACCEPTED");
            SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
            String today = defaultSimpleDateFormat.format(new Date());
            reservationAccept.setReservationDate(today);
            reservationAccept.setAgentId(orderAccepted.getAgentId());
            reservationAccept.setAgentName(orderAccepted.getAgentName());
            reservationAccept.setOrderId(orderAccepted.getOrderId());

            reservationRepository.save(reservationAccept);
            System.out.println("##### listener ReservationRequest : " + orderAccepted.toJson());
        }

    }

}
