package entity;

import entity.enumirate.Rating;
import entity.enumirate.RatingConverterDb;
import entity.enumirate.SpecialFeatures;
import entity.enumirate.SpecialFeaturesConverterDb;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "film")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    private String title;
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "rental_duration")
    private Integer rentalDuration;

    @Column(name = "rental_rate")
    private Double rentalRate;

    private Integer length;

    @Column(name = "replacement_cost")
    private Double replacementCost;

    @Convert(converter = RatingConverterDb.class)
    private Rating rating;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "last_update")
    private Date lastUpdate;

    @Column(name = "special_features")
    @Convert(converter = SpecialFeaturesConverterDb.class)
    private EnumSet<SpecialFeatures> specialFeatures;
//    private String specialFeatures;

    @ManyToOne
    @JoinColumn(name = "language_id")
    public Language languageId;

    @Column(name = "original_language_id")
    private Integer originalLanguageId;

    @OneToOne
    @JoinColumn(name = "film_id")
    private FilmText filmText;

    @ManyToOne
    @JoinTable(name = "film_category",
    joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor",
        joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;

    public void setSpecialFeatures(SpecialFeatures... specialFeatures) {
        EnumSet<SpecialFeatures> set = EnumSet.noneOf(SpecialFeatures.class);
        if (specialFeatures != null) {
            set.addAll(Arrays.asList(specialFeatures));
        }
        this.specialFeatures = set;
    }

}
