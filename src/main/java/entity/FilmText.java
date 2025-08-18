package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "hibernate_project2", name = "film_text")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Long filmId;
    private String title;
    private String description;
}
