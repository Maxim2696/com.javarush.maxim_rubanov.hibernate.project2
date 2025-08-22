package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "address", length = 50)
    private String addressName;

    @Column(name = "address2", length = 50)
    private String addressNameSecond;

    @Column(name = "district", length = 20)
    private String districtName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(name = "phone", length = 20)
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "last_update")
    private Date lastUpdate;

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", addressName='" + addressName + '\'' +
                ", addressNameSecond='" + addressNameSecond + '\'' +
                ", districtName='" + districtName + '\'' +
                ", city=" + city.getCityName() +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
