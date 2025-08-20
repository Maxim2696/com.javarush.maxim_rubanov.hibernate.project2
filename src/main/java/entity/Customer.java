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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store stores;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(length = 50)
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private Integer active;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_date")
    private Date createUpdate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "last_update")
    private Date lastUpdate;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", active=" + active +
                ", createUpdate=" + createUpdate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
