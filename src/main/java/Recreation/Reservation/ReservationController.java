package Recreation.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

 @RestController
 public class ReservationController {

  @Autowired
   ReservationRepository reservationRepository;


  @RequestMapping(method=RequestMethod.POST, path="/reservations")
  public void reservationCancellation(@RequestBody Reservation reservation) {

   Reservation reservationCancel = reservationRepository.findByOrderId(reservation.getOrderId());
   reservationCancel.setStatus("RESERVATIONCANCELED");
   reservationRepository.save(reservationCancel);

  }

  @RequestMapping(method=RequestMethod.PATCH, path="/reservations")
  public void reservationCompletion(@RequestParam (value="orderId", required=false, defaultValue="0") Long orderId) {

   Reservation reservationCompl = reservationRepository.findByOrderId(orderId);
   reservationCompl.setStatus("RESERVATIONCOMPLETED");
   SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
   String today = defaultSimpleDateFormat.format(new Date());
   reservationCompl.setReservationDate(today);
   reservationRepository.save(reservationCompl);

  }

 }
