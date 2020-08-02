package Recreation.Reservation;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long>{
    Reservation findByOrderId(Long orderId);

}