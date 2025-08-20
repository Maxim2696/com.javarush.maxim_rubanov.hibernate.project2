package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "hibernate_project2", name = "film_text")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Long filmId;
    private String title;
    private String description;
}
