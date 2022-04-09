package com.example.codefellowship.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreProperties({"album"})
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private int length;

    private int trackerNumber;
    @ManyToOne
    Album album;

}
