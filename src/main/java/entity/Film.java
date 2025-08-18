package entity;

import entity.enumirate.Rating;
import entity.enumirate.SpecialFeatures;
import entity.enumirate.SpecialFeaturesConverterDb;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "film", schema = "hibernate_project2")
@Data
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

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "special_features")
    @Convert(converter = SpecialFeaturesConverterDb.class)
    private EnumSet<SpecialFeatures> specialFeatures;

    @ManyToOne
    @JoinColumn(name = "language_id")
    public Language languageId;

    @Column(name = "original_language_id")
    private int originalLanguageId;

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
    private Set<Actor> categories;

    public void setSpecialFeatures(SpecialFeatures... specialFeatures) {
        EnumSet<SpecialFeatures> set = EnumSet.noneOf(SpecialFeatures.class);
        if (specialFeatures != null) {
            set.addAll(Arrays.asList(specialFeatures));
        }
        this.specialFeatures = set;
    }
}
