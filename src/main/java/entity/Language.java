package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Entity
@Table(name = "language", schema = "hibernate_project2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @Column(name = "language_id")
    private Long languageId;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "last_update")
    private Date lastUpdate;

}
