package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long rentalId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "rental_date")
    private Date rentalDate;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date")
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "last_update")
    private Date lastUpdate;

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", rentalDate=" + rentalDate +
//                ", inventory=" + inventory.getFilm().getFilmId() +
//                ", customer=" + customer.getCustomerId() +
                ", returnDate=" + returnDate +
//                ", staff=" + staff.getUsername() +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
