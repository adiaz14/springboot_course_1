package com.studentgrades.studentgrades.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topics")
    private int idTopic;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "fk_curse")
    @JsonIgnore
    private Course course;

}
