package Recreation.Reservation;

import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table(name="Reservation_table")
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long agentId;
    private String agentName;
    private String reservationDate;
    private Long orderId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        ReservationAccepted reservationAccepted = new ReservationAccepted();
        BeanUtils.copyProperties(this, reservationAccepted);
        reservationAccepted.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){

        if(this.getStatus().equals("RESERVATIONCOMPLETED")) {
            ReservationCompleted reservationCompleted = new ReservationCompleted();
            BeanUtils.copyProperties(this, reservationCompleted);
            reservationCompleted.publishAfterCommit();
        }

        if(this.getStatus().equals("RESERVATIONCANCELED")) {
            ReservationCanceled reservationCanceled = new ReservationCanceled();
            BeanUtils.copyProperties(this, reservationCanceled);
            reservationCanceled.publishAfterCommit();
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
